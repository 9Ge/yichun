package com.enercomn.web.A11Failure.service;

import com.enercomn.Enum.CheckPlanSourceEnum;
import com.enercomn.Enum.CodePrefix;
import com.enercomn.Enum.ResumeSourceEnum;
import com.enercomn.utils.CodeUtil;
import com.enercomn.utils.ListPageUtil;
import com.enercomn.utils.PageBean;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A01EquipmentManagement.service.A01TbEquipmentResumeService;
import com.enercomn.web.A10CheckPlan.service.A10TbEquipmentCheckPlanService;
import com.enercomn.web.A11Failure.bean.A11TbEquipmentFailureDetail;
import com.enercomn.web.A11Failure.mapper.A11TbEquipmentFailureDetailMapper;
import com.enercomn.web.A12Repair.service.A12TbMaintenanceRecordLedgerService;
import com.enercomn.web.baseManager.model.PageObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Date: 2021/8/16 11:45<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Service
public class A11TbEquipmentFailureDetailService {

    private final A11TbEquipmentFailureDetailMapper a11TbEquipmentFailureDetailMapper;
    private final A10TbEquipmentCheckPlanService a10TbEquipmentCheckPlanService;
    private final A12TbMaintenanceRecordLedgerService a12TbMaintenanceRecordLedgerService;
    private final A01TbEquipmentResumeService a01TbEquipmentResumeService;

    public A11TbEquipmentFailureDetailService(A11TbEquipmentFailureDetailMapper a11TbEquipmentFailureDetailMapper, A10TbEquipmentCheckPlanService a10TbEquipmentCheckPlanService, A12TbMaintenanceRecordLedgerService a12TbMaintenanceRecordLedgerService, A01TbEquipmentResumeService a01TbEquipmentResumeService) {
        this.a11TbEquipmentFailureDetailMapper = a11TbEquipmentFailureDetailMapper;
        this.a10TbEquipmentCheckPlanService = a10TbEquipmentCheckPlanService;
        this.a12TbMaintenanceRecordLedgerService = a12TbMaintenanceRecordLedgerService;
        this.a01TbEquipmentResumeService = a01TbEquipmentResumeService;
    }

    /**
     * 新增设备故障记录数据
     *
     * @param failureDetail
     * @return
     */
    @Transactional(rollbackOn = {Exception.class, RuntimeException.class})
    public boolean addData(A11TbEquipmentFailureDetail failureDetail) {
        try {
            failureDetail.setFailureCode(CodeUtil.generateCode(CodePrefix.GZBX.getPrefix()));
            failureDetail.setTefdId(StringUtils.getUUID());
            a11TbEquipmentFailureDetailMapper.insert(failureDetail);
            Integer isSolve = failureDetail.getIsSolve() == null ? 0 : failureDetail.getIsSolve();
            if (isSolve == 1) {
                a01TbEquipmentResumeService.generateResumeByAddFailure(failureDetail);
            } else {
                a10TbEquipmentCheckPlanService.generateCheckPlanByAddFailure(failureDetail);
            }
            return true;
        } catch (Exception e) {
            log.info("{}", e);
            throw new RuntimeException("新增异常：" + e.getMessage());
        }
    }


    /**
     * 删除设备故障记录数据
     *
     * @param failureDetail
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public boolean deleteData(A11TbEquipmentFailureDetail failureDetail) {
        try {
            A11TbEquipmentFailureDetail delParam = new A11TbEquipmentFailureDetail();
            delParam.setTefdId(failureDetail.getTefdId());
            delParam.setIsDel(1);
            Integer isSolve = failureDetail.getIsSolve() == null ? 0 : failureDetail.getIsSolve();
            a11TbEquipmentFailureDetailMapper.updateByPrimaryKey(delParam);
            if (isSolve == 1) {
                a01TbEquipmentResumeService.generateResumeByDelete(ResumeSourceEnum.EquipmentFailure.getName(),failureDetail.getTefdId());
            } else {
                a10TbEquipmentCheckPlanService.deletePlanByFrom(CheckPlanSourceEnum.EquipmentFailureCode.getName(),failureDetail.getTefdId());
            }
            return  true;
        } catch (Exception e) {
            log.info("{}", e);
            throw new RuntimeException("删除异常：" + e.getMessage());
        }
    }


    /**
     * 更新设备故障记录数据
     *
     * @param failureDetail
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public boolean updateData(A11TbEquipmentFailureDetail failureDetail) {
        try {
            A11TbEquipmentFailureDetail deleteFailure = a11TbEquipmentFailureDetailMapper.selectByPrimaryKey(failureDetail);
            if(deleteFailure != null){
                Integer delIsSolve = deleteFailure.getIsSolve() == null ? 0 : deleteFailure.getIsSolve();
                if (delIsSolve == 1) {
                    a01TbEquipmentResumeService.generateResumeByDelete(ResumeSourceEnum.EquipmentFailure.getName(),failureDetail.getTefdId());
                } else {
                    a10TbEquipmentCheckPlanService.deletePlanByFrom(CheckPlanSourceEnum.EquipmentFailureCode.getName(),failureDetail.getTefdId());
                }
            }

            a11TbEquipmentFailureDetailMapper.updateByPrimaryKey(failureDetail);
            Integer isSolve = failureDetail.getIsSolve() == null ? 0 : failureDetail.getIsSolve();
            if (isSolve == 1) {
                a01TbEquipmentResumeService.generateResumeByUpdateFailure(failureDetail);
            } else {
                a10TbEquipmentCheckPlanService.generateCheckPlanByUpdateFailure(failureDetail);
            }
            return true;
        } catch (Exception e) {
            log.info("{}", e);
            throw new RuntimeException("修改异常：" + e.getMessage());
        }
    }


    /**
     * 查询设备故障记录数据列表
     * 分页
     *
     * @param pageObject
     * @return
     */
    public PageBean findDataList(PageObject<A11TbEquipmentFailureDetail> pageObject) {
        A11TbEquipmentFailureDetail failureDetail = pageObject.getData();
        if(failureDetail != null){
            String equipmentCode = failureDetail.getEquipmentCode();
            String equipmentName = failureDetail.getEquipmentName();
            if(failureDetail!=null && StringUtils.isNotEmpty(equipmentCode)){
                failureDetail.setEquipmentCode("%"+equipmentCode+"%");
            }
            if(failureDetail!=null && StringUtils.isNotEmpty(equipmentName)){
                failureDetail.setEquipmentName("%"+equipmentName+"%");
            }
        }

        List<A11TbEquipmentFailureDetail> a10TbSpotCheckList = a11TbEquipmentFailureDetailMapper.queryFailureDetail(failureDetail);
        return ListPageUtil.pageInfo(pageObject,a10TbSpotCheckList);
    }


}
