package com.enercomn.web.A00_dict.mapper;


import com.enercomn.web.A00_dict.bean.A00TbDict;
import com.enercomn.web.A00_dict.bean.A00TbDictResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface A00TbDictMapper extends Mapper<A00TbDict> {

    int saveDict(A00TbDict record);

    List<A00TbDictResult> queryByParam(@Param("baseCode") String baseCode);

    A00TbDictResult queryByKey(@Param("key") String baseCode);

    A00TbDictResult queryByValue(@Param("value") String value);

}