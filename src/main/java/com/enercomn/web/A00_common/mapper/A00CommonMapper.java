package com.enercomn.web.A00_common.mapper;

import com.enercomn.web.A00_common.bean.A00DictionariesSelectBean;
import com.enercomn.web.A00_common.bean.A00DictionariesSelectParamBean;

import java.util.List;

public interface A00CommonMapper {
    List<A00DictionariesSelectBean> findDictionariesInfo(A00DictionariesSelectParamBean dictionariesSelectParamBean);

}
