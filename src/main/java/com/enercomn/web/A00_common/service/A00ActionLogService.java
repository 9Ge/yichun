package com.enercomn.web.A00_common.service;

import com.enercomn.utils.PageBean;
import com.enercomn.web.A00_common.bean.A00ActionLogBean;
import com.enercomn.web.A00_common.mapper.A00ActionLogMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = {Exception.class})
public class A00ActionLogService {

    @Autowired
    A00ActionLogMapper a00ActionLogMapper;

    /**
     * 获取操作日志记录
     *
     * @param cOperateModule 操作模块
     * @param cName   姓名
     * @param cOperateDateBegin   操作时间-起始
     * @param cOperateDateEnd   操作时间-截止
     * @param currPage       当前页
     * @param pageSize       每页记录数
     * @param orderColumn    排序列
     * @param orderType      排序类型
     *
     * @return 操作记录
     */
    public PageBean getActionLogInfo(String cOperateModule, String cName, String cOperateDateBegin, String cOperateDateEnd, int currPage, int pageSize, String orderColumn, String orderType, String areaId) {
        PageHelper.startPage(currPage, pageSize);
        if (cOperateDateBegin !="" && cOperateDateBegin !=null){
            cOperateDateBegin = cOperateDateBegin + " 00:00:00";
        }
        if (cOperateDateEnd !="" && cOperateDateEnd !=null){
            cOperateDateEnd = cOperateDateEnd + " 23:59:59";
        }
        List<A00ActionLogBean> list = a00ActionLogMapper.getActionLogInfo(cOperateModule,cName, cOperateDateBegin, cOperateDateEnd, orderColumn, orderType, areaId);
        PageInfo<A00ActionLogBean> pageInfo = new PageInfo<>(list);
        return new PageBean<>(pageInfo);
    }

    /**
     * 保存操作日志
     *
     * @param operateModule 操作模块
     * @param operateType   操作类型
     * @param operateUser   操作人id
     * @param areaId         所属区域id
     * @param operateDescribe        操作描述
     * @param operateIP      操作IP
     *
     * @return 操作结果
     */
    public boolean saveActionLogInfo(String operateModule, String operateType, String operateUser, String areaId, String operateDescribe, String operateIP) {
        int ret = a00ActionLogMapper.saveActionLogInfo(operateModule, operateType, operateUser, areaId, operateDescribe, operateIP);
        return ret > 0;
    }

    /**
     * 获取操作模块
     * @return
     */
    public List<String> getModules(){
        return a00ActionLogMapper.getModules();
    }

    /**
     * 获取姓名
     */
    public List<String> getAllNames(String areaId){
        return a00ActionLogMapper.getAllNames(areaId);
    }
}
