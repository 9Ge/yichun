package com.enercomn.web.A05OPLClass.service;

import com.enercomn.utils.ListPageUtil;
import com.enercomn.utils.PageBean;
import com.enercomn.utils.PageUtil;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A05OPLClass.bean.A05TbOplClass;
import com.enercomn.web.A05OPLClass.bean.A05TbOplTrainRecords;
import com.enercomn.web.A05OPLClass.mapper.A05TbOplClassMapper;
import com.enercomn.web.baseManager.model.PageObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;

/**
 * author:yangcheng
 */
@Slf4j
@Service
@SuppressWarnings({"all"})
public class A05OplClassService {

    public final A05TbOplClassMapper a05TbOplClassMapper;
    public final A05TbOplTrainRecordsService a05TbOplTrainRecordsService;

    public A05OplClassService(A05TbOplClassMapper a05TbOplClassMapper, A05TbOplTrainRecordsService a05TbOplTrainRecordsService) {
        this.a05TbOplClassMapper = a05TbOplClassMapper;
        this.a05TbOplTrainRecordsService = a05TbOplTrainRecordsService;
    }

    /**
     * 新增Opl
     * 批量新增学习人员
     */
    @Transactional(propagation=Propagation.REQUIRED)
    public boolean insert(A05TbOplClass oplClass){
        oplClass.setTocId(StringUtils.getUUID());
        a05TbOplClassMapper.saveBean(oplClass);
        if(!CollectionUtils.isEmpty(oplClass.getRecordsList())){
            a05TbOplTrainRecordsService.saveBatchRecords(oplClass);
        }
        return true;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    public boolean updateAndRemove(A05TbOplClass oplClass){
        if(StringUtil.isEmpty(oplClass.getTocId())){
            log.info("opl id is null:{}",oplClass);
            return false;
        }
        a05TbOplTrainRecordsService.removeBatchByTocId(oplClass.getTocId());
        a05TbOplTrainRecordsService.saveBatchRecords(oplClass);
        return a05TbOplClassMapper.updateById(oplClass)>=0;
    }

    /**
     * 更新OPl单点课
     * 批量新增或更新学习人员
     * @param oplClass
     */
    @Transactional(propagation=Propagation.REQUIRED)
    public boolean update(A05TbOplClass oplClass){
        if(StringUtil.isEmpty(oplClass.getTocId())){
            log.info("opl id is null:{}",oplClass);
            return false;
        }
        List<A05TbOplTrainRecords> a05TbOplTrainRecordsList = oplClass.getRecordsList();
        if(!CollectionUtils.isEmpty(oplClass.getRecordsList())){
            a05TbOplTrainRecordsService.saveOrUpdateBatchRecords(oplClass);
        }
        return a05TbOplClassMapper.updateById(oplClass)>=0;
    }

    /**
     * 删除Opl单点课
     * 删除单点课学习记录
     * @param oplClass
     */
    @Transactional(propagation=Propagation.REQUIRED)
    public boolean delete(A05TbOplClass oplClass){
        a05TbOplTrainRecordsService.delBatchByTocId(oplClass.getTocId());
        A05TbOplClass  delOplClass = new A05TbOplClass();
        oplClass.setTocId(oplClass.getTocId());
        oplClass.setIsDel(1);
        return a05TbOplClassMapper.deleteById(oplClass)>= 0;
    }

    /**
     * 单点课明细
     * @param tocId
     * @return
     */
    public A05TbOplClass detail(String tocId){
        return a05TbOplClassMapper.queryByPrimaryKey(tocId);
    }

    /**
     * 根据查询条件返回单点课集合
     * @param oplClass
     * @return
     */
    public PageBean query(A05TbOplClass oplClass, PageObject<A05TbOplClass> pageObject){
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap map = objectMapper.convertValue(oplClass, HashMap.class);
        List<A05TbOplClass> oplClasses = a05TbOplClassMapper.selectStudyRecordByParam(map);
        return ListPageUtil.pageInfo(pageObject,oplClasses);
    }

}
