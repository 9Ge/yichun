package com.enercomn.web.A99TableExcelCommon.handle;

import com.enercomn.web.A99TableExcelCommon.service.BusinessDataService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2021/9/7 16:07<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Component
@Slf4j
public class SaveExcelDataHandle {
    @Autowired
    BusinessDataService businessDataService;
    /**
     * 获取session
     * @return
     */
    public SqlSession  getSqlSession(){
        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory)SpringContextUtil.getBean("sqlSessionFactory");
        return  sqlSessionFactory.openSession(ExecutorType.BATCH);
    }

    /**
     * 获取mapper代理
     * @param interfaceImpl
     * @param sqlSession
     * @return
     */
    public Object getMapper(Class interfaceImpl, SqlSession sqlSession){
        return Proxy.newProxyInstance(
                interfaceImpl.getClassLoader(),
                new Class[]{interfaceImpl},
                new MapperProxy(sqlSession.getMapper(interfaceImpl))
        );
    }


    /**
     * 保存Excel的数据
     * @param mapperClazz
     * @param needSaveList
     * @return
     */
    public String saveExcelData(@NonNull Class mapperClazz, List<Object> needSaveList) {
        SqlSession sqlSession = null;
        List saveList = new ArrayList();
        try {
            sqlSession = getSqlSession();
            Object  mapperObject = getMapper(mapperClazz,sqlSession);
            for (Object newSaveObject:needSaveList) {
                businessDataService.saveBusiness(newSaveObject,mapperObject,saveList);
            }
            Method method = mapperObject.getClass().getMethod("insert", Object.class);
            for (Object saveObject:saveList) {
                try {
                    method.invoke(mapperObject,saveObject);
                } catch (Exception e) {
                    continue;
                }
            }

            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            log.info("{}",e);
        } finally {
            if(sqlSession != null){
                sqlSession.close();
            }
        }
        return "导入成功，共导入"+saveList.size()+"条数据";
    }

    /**
     * 查询导出的Excel数据
     * @param mapperClazz
     * @param beanClazz
     * @return
     */
    public List queryExcelData(Class mapperClazz,Class beanClazz){
        SqlSession sqlSession = null;
        try {
            sqlSession = getSqlSession();
            Object  instance = getMapper(mapperClazz,sqlSession);
            Method method = instance.getClass().getMethod("selectByExample", Object.class);

            Example example = new Example(beanClazz);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("isDel",0);
            List<Object> objectList = (List<Object>) method.invoke(instance, example);
            for (Object object:objectList) {
                Class<?> clazz = object.getClass();
                businessDataService.queryBusiness(object,clazz.getDeclaredFields());
            }
            return  objectList;
        } catch (Exception e) {
            log.info("{}",e);
        }
        return new ArrayList();
    }

}
