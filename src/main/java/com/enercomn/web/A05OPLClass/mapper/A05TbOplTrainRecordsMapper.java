package com.enercomn.web.A05OPLClass.mapper;

import com.enercomn.web.A05OPLClass.bean.A05TbOplTrainRecords;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface A05TbOplTrainRecordsMapper {

    int saveBatch(List<A05TbOplTrainRecords> list);

    int saveOrUpdateBatch(List<A05TbOplTrainRecords> list);

    int updateByPrimaryKey(A05TbOplTrainRecords a05TbOplTrainRecords);

    int updateBatchByPrimaryKey(List<A05TbOplTrainRecords> a05TbOplTrainRecords);

    int deleteByPrimaryKey(A05TbOplTrainRecords a05TbOplTrainRecords);

    int deleteBatchByPrimaryKey(List<A05TbOplTrainRecords> a05TbOplTrainRecords);

    int removeBatchByPrimaryKey(List<A05TbOplTrainRecords> a05TbOplTrainRecords);

    A05TbOplTrainRecords selectByPrimaryKey(String totrId);

    List<A05TbOplTrainRecords> selectByParam(A05TbOplTrainRecords a05TbOplTrainRecords);
}