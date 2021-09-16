package com.enercomn.web.A05OPLClass.mapper;

import com.enercomn.web.A05OPLClass.bean.A05TbOplClass;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @author yangcheng
 */
@Repository
public interface A05TbOplClassMapper extends Mapper<A05TbOplClass> {

    int saveBean(A05TbOplClass record);

    int updateById(A05TbOplClass record);

    int deleteById(A05TbOplClass record);

    A05TbOplClass queryByPrimaryKey(String tocId);

    List<A05TbOplClass> selectByParam(A05TbOplClass record);

    List<A05TbOplClass> selectStudyRecordByParam(HashMap map);

}