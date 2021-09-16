package com.enercomn.web.A00_common.mapper;


import com.enercomn.web.A00_common.bean.A00ActionLogBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface A00ActionLogMapper {

    /**
     * 获取操作日志记录
     *
     * @param cOperateModule    操作模块
     * @param cName             姓名
     * @param cOperateDateBegin 操作时间-起始
     * @param cOperateDateEnd   操作时间-截止
     * @param orderColumn    排序列
     * @param orderType      排序类型
     *
     * @return 操作记录
     */
    List<A00ActionLogBean> getActionLogInfo(@Param("cOperateModule") String cOperateModule, @Param("cName") String cName,
                                            @Param("cOperateDateBegin") String cOperateDateBegin,
                                            @Param("cOperateDateEnd") String cOperateDateEnd,
                                            @Param("orderColumn") String orderColumn,
                                            @Param("orderType") String orderType,
                                            @Param("areaId") String areaId);

    /**
     * 保存操作日志
     *
     * @param operateModule   操作模块
     * @param operateType     操作类型
     * @param operateUser     操作人id
     * @param areaId          所属区域id
     * @param operateDescribe 操作描述
     * @param operateIP       操作IP
     * @return 操作结果
     */
    int saveActionLogInfo(@Param("operateModule") String operateModule, @Param("operateType") String operateType, @Param("operateUser") String operateUser, @Param("areaId") String areaId, @Param("operateDescribe") String operateDescribe, @Param("operateIP") String operateIP);

    /**
     * 获取所有操作模块
     * @return
     */
    List<String> getModules();

    /**
     * 获取姓名
     * @return
     */
    List<String> getAllNames(@Param("areaId") String areaId);
}
