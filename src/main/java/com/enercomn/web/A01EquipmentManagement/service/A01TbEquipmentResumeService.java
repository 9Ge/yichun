package com.enercomn.web.A01EquipmentManagement.service;

import com.enercomn.Enum.ResumeSourceEnum;
import com.enercomn.utils.ListPageUtil;
import com.enercomn.utils.PageBean;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A01EquipmentManagement.bean.A01TbEquipmentResume;
import com.enercomn.web.A01EquipmentManagement.mapper.A01TbEquipmentResumeMapper;
import com.enercomn.web.A11Failure.bean.A11TbEquipmentFailureDetail;
import com.enercomn.web.A12Repair.bean.A12TbMaintenanceRecordLedger;
import com.enercomn.web.baseManager.model.PageObject;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @Date: 2021/8/31 11:00<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Service
public class A01TbEquipmentResumeService {
    private final A01TbEquipmentResumeMapper a01TbEquipmentResumeMapper;

    public A01TbEquipmentResumeService(A01TbEquipmentResumeMapper a01TbEquipmentResumeMapper) {
        this.a01TbEquipmentResumeMapper = a01TbEquipmentResumeMapper;
    }

    /**
     * 当设备故障当场解决后，生成设备履历表
     * 根据设备故障清单生成设备履历表
     *
     * @param a11TbEquipmentFailureDetail
     */
    public void generateResumeByAddFailure(A11TbEquipmentFailureDetail a11TbEquipmentFailureDetail) {
        A01TbEquipmentResume a01TbEquipmentResume = this.setResumeByFailure(a11TbEquipmentFailureDetail, new A01TbEquipmentResume());
        this.addData(a01TbEquipmentResume);
    };

    public A01TbEquipmentResume setResumeByFailure(A11TbEquipmentFailureDetail a11TbEquipmentFailureDetail, A01TbEquipmentResume a01TbEquipmentResume) {
        a01TbEquipmentResume.setDate(a11TbEquipmentFailureDetail.getCreateDate());
        a01TbEquipmentResume.setEquipmentId(a11TbEquipmentFailureDetail.getEquipmentId());
        a01TbEquipmentResume.setFaultReason(a11TbEquipmentFailureDetail.getReason());
        a01TbEquipmentResume.setFaultPhenomenon(a11TbEquipmentFailureDetail.getFailureDetail());
        a01TbEquipmentResume.setHours(a11TbEquipmentFailureDetail.getHourConsume());
        a01TbEquipmentResume.setResumeSource(ResumeSourceEnum.EquipmentFailure.getName());
        a01TbEquipmentResume.setResumeSourceId(a11TbEquipmentFailureDetail.getTefdId());
        a01TbEquipmentResume.setResumeSourceCode(a11TbEquipmentFailureDetail.getFailureCode());
        return a01TbEquipmentResume;
    }

    public void generateResumeByUpdateFailure(A11TbEquipmentFailureDetail a11TbEquipmentFailureDetail) {
        A01TbEquipmentResume resumeBySource = this.getResumeBySource(ResumeSourceEnum.EquipmentFailure.getName(), a11TbEquipmentFailureDetail.getTefdId());
        if (resumeBySource != null) {
            this.setResumeByFailure(a11TbEquipmentFailureDetail, resumeBySource);
            this.updateData(resumeBySource);
        }
    };
    /**
     * 源数据删除时 履历信息也删除
     *
     * @param sourceName
     * @param sourceId
     */
    public void generateResumeByDelete(String sourceName, String sourceId) {
        A01TbEquipmentResume resumeBySource = this.getResumeBySource(sourceName, sourceId);
        if (resumeBySource != null) {
            this.delData(resumeBySource);
        }
    }

    public A01TbEquipmentResume getResumeBySource(String sourceName, String sourceId) {
        Example example = new Example(A01TbEquipmentResume.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("resumeSource", sourceName);
        criteria.andEqualTo("resumeSourceId", sourceId);
        criteria.andEqualTo("isDel", 0);
        List<A01TbEquipmentResume> a01TbEquipmentResumes = a01TbEquipmentResumeMapper.selectByExample(example);
        if (a01TbEquipmentResumes != null && a01TbEquipmentResumes.size() > 0) {
            return a01TbEquipmentResumes.get(0);
        }
        return null;
    }

    /**
     * 根据设备维修清单生成设备履历表
     *
     * @param a12TbMaintenanceRecordLedger
     */
    public void generateResumeByAddRecord(A12TbMaintenanceRecordLedger a12TbMaintenanceRecordLedger) {
        A01TbEquipmentResume a01TbEquipmentResume = this.setResumeByRecord(a12TbMaintenanceRecordLedger, new A01TbEquipmentResume());
        this.addData(a01TbEquipmentResume);
    }

    public A01TbEquipmentResume setResumeByRecord(A12TbMaintenanceRecordLedger a12TbMaintenanceRecordLedger, A01TbEquipmentResume a01TbEquipmentResume) {
        a01TbEquipmentResume.setDate(a12TbMaintenanceRecordLedger.getDate());
        a01TbEquipmentResume.setEquipmentId(a12TbMaintenanceRecordLedger.getEquipmentId());
        a01TbEquipmentResume.setDateBegin(a12TbMaintenanceRecordLedger.getDateBegin());
        a01TbEquipmentResume.setDateEnd(a12TbMaintenanceRecordLedger.getDateEnd());
        a01TbEquipmentResume.setHours(a12TbMaintenanceRecordLedger.getHours());
        a01TbEquipmentResume.setFaultPhenomenon(a12TbMaintenanceRecordLedger.getContent());
        a01TbEquipmentResume.setFaultReason(a12TbMaintenanceRecordLedger.getRemarks());
        a01TbEquipmentResume.setTeiId(a12TbMaintenanceRecordLedger.getRepairPersonnel());
        a01TbEquipmentResume.setTepId(a12TbMaintenanceRecordLedger.getTepId());
        a01TbEquipmentResume.setResumeSource(ResumeSourceEnum.EquipmentRecordLedger.getName());
        a01TbEquipmentResume.setResumeSourceId(a12TbMaintenanceRecordLedger.getTmrlId());
        a01TbEquipmentResume.setResumeSourceCode(a12TbMaintenanceRecordLedger.getRecordLedgerCode());
        if(a12TbMaintenanceRecordLedger.getMaterialNum()!=null){
            a01TbEquipmentResume.setModelNum(a12TbMaintenanceRecordLedger.getMaterialNum().intValue());
        }
        return a01TbEquipmentResume;
    }

    public void generateResumeByUpdateRecord(A12TbMaintenanceRecordLedger a12TbMaintenanceRecordLedger) {
        A01TbEquipmentResume resumeBySource = this.getResumeBySource(ResumeSourceEnum.EquipmentRecordLedger.getName(), a12TbMaintenanceRecordLedger.getTmrlId());
        if (resumeBySource != null) {
            this.setResumeByRecord(a12TbMaintenanceRecordLedger,resumeBySource);
            this.updateData(resumeBySource);
        }
    }

    ;

    public boolean addData(A01TbEquipmentResume a01TbEquipmentResume) {
        a01TbEquipmentResume.setTerId(StringUtils.getUUID());
        a01TbEquipmentResumeMapper.insert(a01TbEquipmentResume);
        return true;
    }

    public boolean updateData(A01TbEquipmentResume a01TbEquipmentResume) {
        A01TbEquipmentResume equipmentResume = a01TbEquipmentResumeMapper.selectByPrimaryKey(a01TbEquipmentResume.getTerId());
        equipmentResume.setResumeSourceId(equipmentResume.getResumeSourceId());
        a01TbEquipmentResumeMapper.updateByPrimaryKeySelective(a01TbEquipmentResume);
        return true;
    }

    public boolean delData(A01TbEquipmentResume a01TbEquipmentResume) {
        a01TbEquipmentResume.setIsDel(1);
        a01TbEquipmentResumeMapper.updateByPrimaryKeySelective(a01TbEquipmentResume);
        return true;
    }

    public PageBean findDataList(PageObject<A01TbEquipmentResume> pageObject) {
        A01TbEquipmentResume bean = pageObject.getData();
        if (bean != null){
            String equipmentCode = bean.getEquipmentCode();
            String equipmentName = bean.getEquipmentName();
            if(StringUtils.isNotEmpty(equipmentCode)){
                bean.setEquipmentCode("%"+equipmentCode+"%");
            }
            if(StringUtils.isNotEmpty(equipmentName)){
                bean.setEquipmentName("%"+equipmentName+"%");
            }
        }

        List<A01TbEquipmentResume> resumeList = a01TbEquipmentResumeMapper.queryResume(bean);
        return ListPageUtil.pageInfo(pageObject, resumeList);
    }


}
