package com.enercomn.web.A05OPLClass.service;

import com.enercomn.utils.StringUtils;
import com.enercomn.web.A05OPLClass.bean.A05TbOplClass;
import com.enercomn.web.A05OPLClass.bean.A05TbOplTrainRecords;
import com.enercomn.web.A05OPLClass.mapper.A05TbOplTrainRecordsMapper;
import com.github.pagehelper.StringUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @author yangcheng
 */
@Slf4j
@Service
@SuppressWarnings({"all"})
public class A05TbOplTrainRecordsService {

    public final A05TbOplTrainRecordsMapper a05TbOplTrainRecordsMapper;

    public A05TbOplTrainRecordsService(A05TbOplTrainRecordsMapper a05TbOplTrainRecordsMapper) {
        this.a05TbOplTrainRecordsMapper = a05TbOplTrainRecordsMapper;
    }
    @Transactional(propagation=Propagation.REQUIRED)
    public void saveBatchRecords(A05TbOplClass oplClass){
        if(!CollectionUtils.isEmpty( oplClass.getRecordsList())){
            oplClass.getRecordsList().forEach(record->{
                record.setTotrId(StringUtils.getUUID());
                //todo 设置人员
                record.setCreateUser("");
                record.setCreateDate(new Date());
                record.setTocId(oplClass.getTocId());
            });
            a05TbOplTrainRecordsMapper.saveBatch(oplClass.getRecordsList());
        }
    }

    /**
     * 批量更新OPL学习记录
     * @param oplClass
     */
    @Transactional(propagation=Propagation.REQUIRED)
    public void saveOrUpdateBatchRecords(A05TbOplClass oplClass){
        oplClass.getRecordsList().forEach(record->{
            if(StringUtil.isEmpty(record.getTotrId())){
                record.setTotrId(StringUtils.getUUID());
                //todo 设置人员
                record.setCreateUser("");
                record.setCreateDate(new Date());
            }else{
                //todo 设置人员
                record.setUpdateUser("");
                record.setUpdateDate(new Date());
            }
            record.setTocId(oplClass.getTocId());
        });
        a05TbOplTrainRecordsMapper.saveOrUpdateBatch(oplClass.getRecordsList());
    }


    /**
     * 查询单点课记录
     * @param a05TbOplTrainRecords
     * @return
     */
    public List<A05TbOplTrainRecords> query(A05TbOplTrainRecords a05TbOplTrainRecords){
        List<A05TbOplTrainRecords> oplClasses = a05TbOplTrainRecordsMapper.selectByParam(a05TbOplTrainRecords);
        return oplClasses;
    }

    /**
     * 批量删除单点课记录
     * @param tocId
     * @return
     */
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean delBatchByTocId(String tocId){
        A05TbOplTrainRecords queryRecords = new A05TbOplTrainRecords();
        queryRecords.setTocId(tocId);
        List<A05TbOplTrainRecords> records = this.query(queryRecords);
        if(CollectionUtils.isEmpty(records)){
            return true;
        }
        return  a05TbOplTrainRecordsMapper.deleteBatchByPrimaryKey(records)>=0;
    }

    /**
     * 物理删除 学习记录
     * @param tocId
     * @return
     */
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean removeBatchByTocId(@NonNull String tocId){
        A05TbOplTrainRecords queryRecords = new A05TbOplTrainRecords();
        queryRecords.setTocId(tocId);
        List<A05TbOplTrainRecords> records = this.query(queryRecords);
        if(CollectionUtils.isEmpty(records)){
            return true;
        }
        return  a05TbOplTrainRecordsMapper.removeBatchByPrimaryKey(records)>=0;
    }
    /**
     * 根据主键更新单点课记录
     * @param a05TbOplTrainRecords
     */
    @Transactional(propagation=Propagation.REQUIRED)
    public void update(A05TbOplTrainRecords a05TbOplTrainRecords){
        a05TbOplTrainRecordsMapper.updateByPrimaryKey(a05TbOplTrainRecords);
    }

    /**
     * 根据主键删除单点课记录
     * @param a05TbOplTrainRecords
     */
    @Transactional(propagation=Propagation.REQUIRED)
    public void delete(A05TbOplTrainRecords a05TbOplTrainRecords){
        A05TbOplTrainRecords  delRecords = new A05TbOplTrainRecords();
        delRecords.setTotrId(delRecords.getTotrId());
        delRecords.setIsDel(1);
        a05TbOplTrainRecordsMapper.deleteByPrimaryKey(delRecords);
    }

    /**
     * 查询单点课记录明细
     * @param totrId
     * @return
     */
    public A05TbOplTrainRecords detail(String totrId){
        return a05TbOplTrainRecordsMapper.selectByPrimaryKey(totrId);
    }


}
