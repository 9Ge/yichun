package com.enercomn.web.A08EquipmentParam.service;

import com.enercomn.utils.ListPageUtil;
import com.enercomn.utils.PageBean;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A08EquipmentParam.bean.A08TbEquipmentParam;
import com.enercomn.web.A08EquipmentParam.mapper.A08TbEquipmentParamMapper;
import com.enercomn.web.baseManager.model.PageObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Date: 2021/8/12 16:46<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Service
@SuppressWarnings({"all"})
public class A08TbEquipmentParamService {

    private final A08TbEquipmentParamMapper a08TbEquipmentParamMapper;


    public A08TbEquipmentParamService(A08TbEquipmentParamMapper a08TbEquipmentParamMapper) {
        this.a08TbEquipmentParamMapper = a08TbEquipmentParamMapper;
    }

    /**
     * 新增设备基本信息参数
     * @param param
     * @return
     */
    public boolean addData(A08TbEquipmentParam param){
        try {
            param.setTepsId(StringUtils.getUUID());
            return a08TbEquipmentParamMapper.insert(param)>=0;
        } catch (Exception e) {
            log.info("{}",e);
            throw new RuntimeException("新增异常："+e.getMessage());
        }
    };

    /**
     * 删除设备基本信息
     * @param param
     * @return
     */
    public boolean deleteData(A08TbEquipmentParam param){
        try {
            A08TbEquipmentParam delParam = new A08TbEquipmentParam();
            delParam.setTepsId(param.getTepsId());
            delParam.setIsDel(1);
            return a08TbEquipmentParamMapper.updateByPrimaryKey(delParam)>=0;
        } catch (Exception e) {
            log.info("{}",e);
            throw new RuntimeException("删除异常："+e.getMessage());
        }
    };

    /**
     * 更新设备基本信息
     * @param param
     * @return
     */
    public boolean updateData(A08TbEquipmentParam param){
        try {
            return a08TbEquipmentParamMapper.updateByPrimaryKey(param)>=0;
        } catch (Exception e) {
            log.info("{}",e);
            throw new RuntimeException("修改异常："+e.getMessage());
        }
    };

    /**
     * 查询设备基本信息
     * @param param
     * @return
     */
    public PageBean findDataList(PageObject<A08TbEquipmentParam> pageObject){
        A08TbEquipmentParam data = pageObject.getData();
        if(data!=null ){
            String equipmentCode = data.getEquipmentCode();
            if(StringUtils.isNotEmpty(equipmentCode)){
                data.setEquipmentCode("%"+equipmentCode+"%");
            }

        }
        List<A08TbEquipmentParam> a08TbEquipmentParams = a08TbEquipmentParamMapper.queryEquipmentParam(data);
        return ListPageUtil.pageInfo(pageObject,a08TbEquipmentParams);
    };

    /**
     * 通过Id找到设备基础参数
     * @param tepsId
     * @return
     */
    public A08TbEquipmentParam findDataById(String tepsId){
        A08TbEquipmentParam a08TbEquipmentParams = a08TbEquipmentParamMapper.selectByPrimaryKey(tepsId);
        return  a08TbEquipmentParams;
    };

    /**
     * 设备基本信息下拉框
     * @param param
     * @return
     */
    public List<A08TbEquipmentParam> paramSelect( A08TbEquipmentParam equipmentParam ){
        List<A08TbEquipmentParam> a08TbEquipmentParams = a08TbEquipmentParamMapper.paramSelect(equipmentParam);
        return  a08TbEquipmentParams;
    };

}
