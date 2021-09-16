package com.enercomn.web.baseManager.mapper;


import com.enercomn.web.baseManager.bean.*;

import java.util.List;

/**
 * 作者管理
 * 创建时间2020-02-8
 * cool
 */
public interface BaseMapper {

    /**
     * 新增信息
     * @param baseAddBean
     * @return
     */
    int add(BaseAddBean baseAddBean);

    /**
     * 新增明细信息
     * @param baseDetailBeanList
     * @return
     */
    int addDetail(List<BaseDetailBean> baseDetailBeanList);

    /**
     *
     * @param baseAddBean
     * @return
     */
    int addOrUpdate(BaseAddBean baseAddBean);

    /**
     * 查询List
     * @param baseSelectParamBean
     * @return
     */
    List<BaseSelectBean> getList(BaseSelectParamBean baseSelectParamBean);

    /**
     * 修改
     * @param baseUpdateBean
     * @return
     */
    int update(BaseUpdateBean baseUpdateBean);

    /**
     * 删除
     * @param baseUpdateParamBean
     * @return
     */
    int delete(BaseUpdateParamBean baseUpdateParamBean);

    /**
     * 删除明细
     * @param baseUpdateParamBean
     * @return
     */
    int deleteDetail(BaseUpdateParamBean baseUpdateParamBean);

    /**
     * 查询基本信息
     * @param baseSelectParamBean
     * @return
     */
    BaseSelectBean getInfo(BaseSelectParamBean baseSelectParamBean);


    int addOrUpdateInfoAndDetail(List baseDetailBeanList);

}
