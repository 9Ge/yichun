package com.enercomn.web.A99TableExcelCommon.service;

import com.enercomn.utils.StringUtils;
import com.enercomn.web.A00_dict.service.A00TbDictService;
import com.enercomn.web.A99TableExcelCommon.anno.*;
import com.enercomn.web.A99TableExcelCommon.mapper.A99ExcelMapper;
import com.enercomn.web.A99TableExcelCommon.vo.ConversionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 自定义数据功能
 *
 * @Date: 2021/9/8 16:34<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Service
@Slf4j
public class BusinessDataService {

    @Autowired
    A00TbDictService a00TbDictService;
    @Autowired
    A99ExcelMapper a99ExcelMapper;

    public void saveBusiness(Object saveObject, Object mapperObject, List saveList) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        log.info("{}", saveObject);
        Field[] declaredFields = saveObject.getClass().getDeclaredFields();
        boolean flag = true;
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            if (field.isAnnotationPresent(DefaultPrimaryKey.class)) {
                this.setPrimaryKey(saveObject, field);
            } else if (field.isAnnotationPresent(DictCodeProperties.class)) {
                this.setDictData(saveObject, field);
            } else if (field.isAnnotationPresent(UniqueProperties.class)) {
                field.setAccessible(true);
                String name = field.getName();
                String value = String.valueOf(field.get(saveObject));
                if (!excelAssert(saveList, name, value)) {
                    flag = false;
                    break;
                }
                if (!this.uniqueAssert(saveObject, mapperObject, name, value)) {
                    flag = false;
                    break;
                }
            } else if (field.isAnnotationPresent(Conversion.class)) {
                Conversion conversion = field.getDeclaredAnnotation(Conversion.class);
                boolean b = conversion.saveFlag();
                if(b){
                    SQLInfo sqlInfo = conversion.saveSql();
                    String sql = sqlInfo.sql();
                    String[] split = sql.split("\\?");
                    String execSql="";
                    String[] params = sqlInfo.param();
                    for (int sqlIndex= 0; sqlIndex < split.length; sqlIndex++) {
                        execSql+=split[sqlIndex]+"'"+ReflectionUtil.getStringByDeclaredField(saveObject,params[sqlIndex])+"'";
                    }
                    String result = a99ExcelMapper.execSql(execSql);
                    ReflectionUtil.setString2DeclaredField(saveObject,conversion.saveProperties(),result);
                }else{
                    String conditionsValue = ReflectionUtil.getStringByDeclaredField(saveObject,  conversion.showProperties());
                    ConversionVo conversionVo = new ConversionVo();
                    conversionVo.setColumn(conversion.saveColumn());
                    conversionVo.setByTable( conversion.byTable());
                    conversionVo.setConditionsColumn(conversion.showColumn());
                    conversionVo.setConditionsValue(conditionsValue);
                    String result = a99ExcelMapper.conversion(conversionVo);
                    ReflectionUtil.setString2DeclaredField(saveObject,conversion.saveProperties(),result);
                }

            }
        }
        if (flag) {
            saveList.add(saveObject);
        }
    }

    public static void main(String[] args) {
        String sql = "select * from tb_equipment_param where module_unit=? and tb_equipment_assets_id=?";
        String[] split = sql.split("\\?");
        System.out.println(Arrays.toString(split));
        String sql2="";
        for (int i = 0; i < split.length; i++) {
            sql2+=split[i]+"2";
        }
        System.out.println(sql2);
        System.out.println(split.length);
    }
    public void queryBusiness(Object findObject, Field[] declaredFields) throws IllegalAccessException, NoSuchFieldException {
        log.info("{}", findObject);
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            if (field.isAnnotationPresent(DictCodeProperties.class)) {
                this.getDictData(findObject, field);
            }else if (field.isAnnotationPresent(Conversion.class)) {
                Conversion conversion = field.getDeclaredAnnotation(Conversion.class);
                String savePropertiesValue = ReflectionUtil.getStringByDeclaredField(findObject, conversion.saveProperties());

                ConversionVo conversionVo = new ConversionVo();
                conversionVo.setColumn(conversion.showColumn());
                conversionVo.setByTable(conversion.byTable());
                conversionVo.setConditionsColumn(conversion.saveColumn());
                conversionVo.setConditionsValue(savePropertiesValue);
                String result = a99ExcelMapper.conversion(conversionVo);
                ReflectionUtil.setString2DeclaredField(findObject,conversion.showProperties(),result);
            }
        }
    }

    /**
     * 校验Excel中的数据是否有重复
     *
     * @param saveList
     * @param propertiesName
     * @param propertiesValue
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public boolean excelAssert(List saveList, String propertiesName, String propertiesValue) throws IllegalAccessException, NoSuchFieldException {
        boolean flag = true;
        //校验Excel列表中是否有重复的值
        for (int j = 0; j < saveList.size(); j++) {
            Object o = saveList.get(j);
            Class<?> aClass = o.getClass();
            Field uniqueField = aClass.getDeclaredField(propertiesName);
            uniqueField.setAccessible(true);
            String uniqueVal = String.valueOf(uniqueField.get(o));
            if (uniqueVal.equalsIgnoreCase(propertiesValue)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public boolean uniqueAssert(Object saveObject, Object mapperObject, String name, String value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = mapperObject.getClass().getMethod("selectByExample", Object.class);
        Example example = new Example(saveObject.getClass());
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(name, value);
        List<Object> objectList = (List<Object>) method.invoke(mapperObject, example);
        if (objectList != null && objectList.size() > 0) {
            return false;
        }
        return true;
    }

    /**
     * 设置主键的默认值
     *
     * @param object
     * @param field
     * @return
     * @throws IllegalAccessException
     */
    public void setPrimaryKey(Object object, Field field) throws IllegalAccessException {
        field.setAccessible(true);
        String primaryKey = (String) field.get(object);
        if (StringUtils.isEmpty(primaryKey)) {
            field.set(object, StringUtils.getUUID());
        }
    }


    /**
     * 设置数据字典的值
     *
     * @param object
     * @param field
     * @return
     * @throws IllegalAccessException
     */
    public void setDictData(Object object, Field field) throws IllegalAccessException {
        field.setAccessible(true);
        String dictKey = String.valueOf(field.get(object));
        if (StringUtils.isNotEmpty(dictKey)) {
            String selectorKey = a00TbDictService.getSelectorKey(dictKey);
            field.set(object, selectorKey);
        }
    }

    /**
     * 获取数据字典的值
     *
     * @param object
     * @param field
     * @return
     * @throws IllegalAccessException
     */
    public void getDictData(Object object, Field field) throws IllegalAccessException {
        field.setAccessible(true);
        String dictValue = String.valueOf(field.get(object));
        if (StringUtils.isNotEmpty(dictValue)) {
            String selectorVal = a00TbDictService.getSelectorVal(dictValue);
            if (StringUtils.isNotEmpty(selectorVal)) {
                field.set(object, selectorVal);
            }
        }
    }

}
