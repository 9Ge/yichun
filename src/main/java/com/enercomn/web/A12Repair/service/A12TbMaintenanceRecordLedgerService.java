package com.enercomn.web.A12Repair.service;

import com.enercomn.Enum.CodePrefix;
import com.enercomn.Enum.RecordLedgerSourceEnum;
import com.enercomn.Enum.ResumeSourceEnum;
import com.enercomn.utils.CodeUtil;
import com.enercomn.utils.ListPageUtil;
import com.enercomn.utils.PageBean;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A01EquipmentManagement.service.A01TbEquipmentResumeService;
import com.enercomn.web.A06EquipmentMaterial.service.A06TbEquipmentMaterialUseService;
import com.enercomn.web.A10CheckPlan.bean.A10TbEquipmentCheckPlan;
import com.enercomn.web.A12Repair.bean.A12TbMaintenanceRecordLedger;
import com.enercomn.web.A12Repair.bean.A12TbMaintenanceRecordLedgerVo;
import com.enercomn.web.A12Repair.mapper.A12TbMaintenanceRecordLedgerMapper;
import com.enercomn.web.baseManager.model.PageObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Date: 2021/8/16 13:43<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Service
@SuppressWarnings({"all"})
public class A12TbMaintenanceRecordLedgerService {

    private final A12TbMaintenanceRecordLedgerMapper a12TbMaintenanceRecordLedgerMapper;
    private final A01TbEquipmentResumeService a01TbEquipmentResumeService;
    private final A06TbEquipmentMaterialUseService a06TbEquipmentMaterialUseService;

    public A12TbMaintenanceRecordLedgerService(A12TbMaintenanceRecordLedgerMapper a12TbMaintenanceRecordLedgerMapper, A01TbEquipmentResumeService a01TbEquipmentResumeService, A06TbEquipmentMaterialUseService a06TbEquipmentMaterialUseService) {
        this.a12TbMaintenanceRecordLedgerMapper = a12TbMaintenanceRecordLedgerMapper;
        this.a01TbEquipmentResumeService = a01TbEquipmentResumeService;
        this.a06TbEquipmentMaterialUseService = a06TbEquipmentMaterialUseService;
    }

    /**
     * 设备周检计划完成时生成维修记录
     *
     * @param a10TbEquipmentCheckPlan
     */
    public void generateRecordByUpdateCheckPlan(A10TbEquipmentCheckPlan a10TbEquipmentCheckPlan) {

        //todo 设备维修记录 通过周检计划 找到预防维护增加 关联备件消耗
//        A10TbEquipmentCheckPlan plan = a10TbEquipmentCheckPlanService.getCheckPlanByIdAndForm(CheckPlanSourceEnum.EquipmentMaintenanceCode.getName(), a10TbEquipmentCheckPlan.getTecpId());
//        String planFromId = plan.getPlanFromId();
//        if(planFromId != null){
//            A07TbEquipmentMaintenance maintenance = a07TbEquipmentMaintenanceService.findDataById(planFromId);
//            maintenance.getM
//        }

        A12TbMaintenanceRecordLedger recordLedger = new A12TbMaintenanceRecordLedger();
        recordLedger.setEquipmentId(a10TbEquipmentCheckPlan.getEquipmentId());
        recordLedger.setDate(a10TbEquipmentCheckPlan.getPlanDate());
        recordLedger.setContent(a10TbEquipmentCheckPlan.getCheckContent());
        recordLedger.setMaintenanceSource(RecordLedgerSourceEnum.CheckPlanCode.getName());
        recordLedger.setMaintenanceSourceId(a10TbEquipmentCheckPlan.getTecpId());
        recordLedger.setSourceCode(a10TbEquipmentCheckPlan.getPlanCode());
        this.addData(recordLedger);
    }

    /**
     * 周检计划删除 同时删除维修记录
     *
     * @param a10TbEquipmentCheckPlan
     */
    public void generateRecordByDelete(A10TbEquipmentCheckPlan a10TbEquipmentCheckPlan) {
        A12TbMaintenanceRecordLedger recordLedger = this.getGenerateForm(RecordLedgerSourceEnum.CheckPlanCode.getName(), a10TbEquipmentCheckPlan.getTecpId());
        if (recordLedger != null) {
            this.deleteData(recordLedger);
        }
    }

    /**
     * 获取源头生成后的数据
     *
     * @param sourceName
     * @param sourceId
     * @return
     */
    public A12TbMaintenanceRecordLedger getGenerateForm(String sourceName, String sourceId) {
        Example example = new Example(A12TbMaintenanceRecordLedger.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("maintenanceSource", sourceName);
        criteria.andEqualTo("maintenanceSourceId", sourceId);
        criteria.andEqualTo("isDel", 0);
        List<A12TbMaintenanceRecordLedger> recordLedgers = a12TbMaintenanceRecordLedgerMapper.selectByExample(example);
        if (recordLedgers != null && recordLedgers.size() > 0) {
            return recordLedgers.get(0);
        }
        return null;
    }

    /**
     * 根据设备故障清单生成的维修记录
     *
     * @param a11TbEquipmentFailureDetail
     *//*
    public void generateRecordLedgerByFailure(A11TbEquipmentFailureDetail a11TbEquipmentFailureDetail) {
        A12TbMaintenanceRecordLedger recordLedger = new A12TbMaintenanceRecordLedger();
        recordLedger.setEquipmentId(a11TbEquipmentFailureDetail.getEquipmentId());
        recordLedger.setDate(a11TbEquipmentFailureDetail.getCreateDate());
        recordLedger.setContent(a11TbEquipmentFailureDetail.getFailureDetail());
        recordLedger.setHours(a11TbEquipmentFailureDetail.getHourConsume());
        recordLedger.setRepairPersonnel(a11TbEquipmentFailureDetail.getTefdId());
        recordLedger.setMaintenanceSource(RecordLedgerSourceEnum.EquipmentFailureCode.getName());
        recordLedger.setMaintenanceSourceId(a11TbEquipmentFailureDetail.getTefdId());
        this.addData(recordLedger);
    }*/

    /**
     * 新增维修记录台账数据
     *
     * @param recordLedger
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addData(A12TbMaintenanceRecordLedger recordLedger) {
        try {
            recordLedger.setRecordLedgerCode(CodeUtil.generateCode(CodePrefix.WXJL.getPrefix()));
            recordLedger.setTmrlId(StringUtils.getUUID());
            a12TbMaintenanceRecordLedgerMapper.insert(recordLedger);
            a01TbEquipmentResumeService.generateResumeByAddRecord(recordLedger);
            a06TbEquipmentMaterialUseService.generateUseByAddRepair(recordLedger);
            return true;
        } catch (Exception e) {
            log.info("{}", e);
            throw new RuntimeException("新增异常：" + e.getMessage());
        }
    }


    /**
     * 删除维修记录台账数据
     *
     * @param recordLedger
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteData(A12TbMaintenanceRecordLedger recordLedger) {
        try {
            A12TbMaintenanceRecordLedger delParam = new A12TbMaintenanceRecordLedger();
            delParam.setTmrlId(recordLedger.getTmrlId());
            delParam.setIsDel(1);
            a12TbMaintenanceRecordLedgerMapper.updateByPrimaryKey(delParam);
            a01TbEquipmentResumeService.generateResumeByDelete(ResumeSourceEnum.EquipmentRecordLedger.getName(),recordLedger.getTmrlId());
            a06TbEquipmentMaterialUseService.deleteBySourceId(recordLedger);
            return true;
        } catch (Exception e) {
            log.info("{}", e);
            throw new RuntimeException("删除异常：" + e.getMessage());
        }
    }


    /**
     * 更新维修记录台账数据
     *
     * @param recordLedger
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updateData(A12TbMaintenanceRecordLedger recordLedger) {
        try {
            A12TbMaintenanceRecordLedger recordLedger2 = a12TbMaintenanceRecordLedgerMapper.selectByPrimaryKey(recordLedger.getTmrlId());
            recordLedger2.setMaintenanceSourceId(recordLedger2.getMaintenanceSourceId());
            a06TbEquipmentMaterialUseService.generateUseByUpdateRepair(recordLedger);
            a01TbEquipmentResumeService.generateResumeByUpdateRecord(recordLedger);
            a12TbMaintenanceRecordLedgerMapper.updateByPrimaryKey(recordLedger);
            return true;
        } catch (Exception e) {
            log.info("{}", e);
            throw new RuntimeException("修改异常：" + e.getMessage());
        }
    }


    /**
     * 查询维修记录台账数据列表
     * 分页
     *
     * @param pageObject
     * @return
     */
    public PageBean findDataList(PageObject<A12TbMaintenanceRecordLedger> pageObject) {
        A12TbMaintenanceRecordLedger ledger = pageObject.getData();
        if (ledger != null) {
            String equipmentCode = ledger.getEquipmentCode();
            String equipmentName = ledger.getEquipmentName();
            if(ledger!=null && StringUtils.isNotEmpty(equipmentCode)){
                ledger.setEquipmentCode("%"+equipmentCode+"%");
            }
            if(ledger!=null && StringUtils.isNotEmpty(equipmentName)){
                ledger.setEquipmentName("%"+equipmentName+"%");
            }
        }
        List<A12TbMaintenanceRecordLedgerVo> a10TbSpotCheckList = a12TbMaintenanceRecordLedgerMapper.queryLedger(pageObject.getData());
        return ListPageUtil.pageInfo(pageObject, a10TbSpotCheckList);
    }


}
