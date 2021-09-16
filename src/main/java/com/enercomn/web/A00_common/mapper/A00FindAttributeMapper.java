package com.enercomn.web.A00_common.mapper;

import com.enercomn.web.A00_common.bean.A00FindAttributeByTcIdBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 查询元件属性信息Mapper
 */
public interface A00FindAttributeMapper {
    /**
     * 查询元件属性
     * @param cTcId 元件类型ID
     * @return 元件属性集合
     */
    public String findComponent(@Param("cTcId") Integer cTcId);


    /**
     * 查询元件属性
     * @param idList 元件类型ID集
     * @return 元件属性数据集合
     */
    public List<A00FindAttributeByTcIdBean> findAttributeByTcId(@Param("idList") String[] idList);

}
