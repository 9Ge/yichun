package com.enercomn.web.A00_common.service;

import com.enercomn.web.A00_common.bean.A00FindAttributeByTcIdBean;
import com.enercomn.web.A00_common.mapper.A00FindAttributeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class A00FindAttributeService {

    @Autowired
    private A00FindAttributeMapper a00FindAttributeMapper;

    /**
     * 查询元件类型中元件属性字段
     * @param cTcId 元件类型ID
     * @return 元件属性集
     */
    public String findComponent(Integer cTcId){

        return a00FindAttributeMapper.findComponent(cTcId);
    }

    /**
     * 查询元件属性
     * @param cTcaId 元件类型ID
     * @return 元件数据属性集合
     */
    public List<A00FindAttributeByTcIdBean> findAttributeByTcId(String[] cTcaId){

        return a00FindAttributeMapper.findAttributeByTcId(cTcaId);
    }
}
