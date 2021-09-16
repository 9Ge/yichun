package com.enercomn.web.A10CheckPlan.service;

import com.enercomn.Enum.CheckPlanSourceEnum;
import com.enercomn.Enum.CheckPlanStatus;
import com.enercomn.Enum.CodePrefix;
import com.enercomn.utils.CodeUtil;
import com.enercomn.utils.ListPageUtil;
import com.enercomn.utils.PageBean;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A01EquipmentManagement.bean.A01EquipmentAssetsBean;
import com.enercomn.web.A01EquipmentManagement.service.A01EquipmentAsstsService;
import com.enercomn.web.A07EquipmentMaintenance.bean.A07TbEquipmentMaintenance;
import com.enercomn.web.A07EquipmentMaintenance.service.A07TbEquipmentMaintenanceService;
import com.enercomn.web.A08EquipmentParam.bean.A08TbEquipmentParam;
import com.enercomn.web.A08EquipmentParam.service.A08TbEquipmentParamService;
import com.enercomn.web.A09SpotCheck.bean.A09TbSpotCheck;
import com.enercomn.web.A09SpotCheck.bean.A09TbSpotCheckDetail;
import com.enercomn.web.A09SpotCheck.service.A09TbSpotCheckService;
import com.enercomn.web.A10CheckPlan.bean.A10TbEquipmentCheckPlan;
import com.enercomn.web.A10CheckPlan.bean.A10TbEquipmentCheckPlanVo;
import com.enercomn.web.A10CheckPlan.mapper.A10TbEquipmentCheckPlanMapper;
import com.enercomn.web.A11Failure.bean.A11TbEquipmentFailureDetail;
import com.enercomn.web.A12Repair.bean.A12TbRepairApplication;
import com.enercomn.web.A12Repair.mapper.A12TbRepairApplicationMapper;
import com.enercomn.web.A12Repair.service.A12TbMaintenanceRecordLedgerService;
import com.enercomn.web.baseManager.model.PageObject;
import com.github.pagehelper.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2021/8/16 11:03<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Service
@SuppressWarnings({"all"})
public class A10TbEquipmentCheckPlanService {

    private final A01EquipmentAsstsService a01EquipmentAsstsService;
    private final A07TbEquipmentMaintenanceService a07TbEquipmentMaintenanceService;
    private final A08TbEquipmentParamService a08TbEquipmentParamService;
    private final A09TbSpotCheckService a09TbSpotCheckService;
    private final A10TbEquipmentCheckPlanMapper a10TbEquipmentCheckPlanMapper;
    private final A12TbMaintenanceRecordLedgerService a12TbMaintenanceRecordLedgerService;
    private final A12TbRepairApplicationMapper a12TbRepairApplicationMapper;

    public A10TbEquipmentCheckPlanService(A10TbEquipmentCheckPlanMapper a10TbEquipmentCheckPlanMapper,
                                          A01EquipmentAsstsService a01EquipmentAsstsService,
                                          A07TbEquipmentMaintenanceService a07TbEquipmentMaintenanceService, A08TbEquipmentParamService a08TbEquipmentParamService,
                                          A09TbSpotCheckService a09TbSpotCheckService,
                                          A12TbMaintenanceRecordLedgerService a12TbMaintenanceRecordLedgerService,
                                          A12TbRepairApplicationMapper a12TbRepairApplicationMapper) {
        this.a10TbEquipmentCheckPlanMapper = a10TbEquipmentCheckPlanMapper;
        this.a01EquipmentAsstsService = a01EquipmentAsstsService;
        this.a07TbEquipmentMaintenanceService = a07TbEquipmentMaintenanceService;
        this.a08TbEquipmentParamService = a08TbEquipmentParamService;
        this.a09TbSpotCheckService = a09TbSpotCheckService;
        this.a12TbMaintenanceRecordLedgerService = a12TbMaintenanceRecordLedgerService;
        this.a12TbRepairApplicationMapper = a12TbRepairApplicationMapper;
    }


    public A10TbEquipmentCheckPlan getGenerateCheckPlan(A10TbEquipmentCheckPlan checkPlan,String fromCode,String fromId,String from){
        if(checkPlan.getTecpId()!=null){
            return checkPlan;
        }
        checkPlan.setPlanCode(CodeUtil.generateCode(CodePrefix.ZJJH.getPrefix()));
        checkPlan.setPlanFromCode(fromCode);
        checkPlan.setPlanFromId(fromId);
        checkPlan.setPlanFrom(from);
        checkPlan.setStatus(CheckPlanStatus.UNFINISHED.getStatus());
        checkPlan.setTecpId(StringUtils.getUUID());
        return checkPlan;
    }

    /**
     * 添加点检异常生成周检计划
     * @param a09TbSpotCheckDetail
     */
    public void generateCheckPlanByAddCheck(A09TbSpotCheckDetail a09TbSpotCheckDetail) {
        A10TbEquipmentCheckPlan checkPlan= this.setCheckPlanByCheck(a09TbSpotCheckDetail,new A10TbEquipmentCheckPlan());
        this.addData(checkPlan);
    }

    /**
     * 修改点检异常 同时修改周计划
     * @param a09TbSpotCheckDetail
     */
    public void generateCheckPlanByUpdateCheck(A09TbSpotCheckDetail a09TbSpotCheckDetail) {
        A10TbEquipmentCheckPlan plan = this.getGeneratePlan(CheckPlanSourceEnum.CheckExceptionCode.getName(), a09TbSpotCheckDetail.getTscdId());
        if(plan != null){
            A10TbEquipmentCheckPlan checkPlan= setCheckPlanByCheck(a09TbSpotCheckDetail,plan);
            this.updateData(checkPlan);
        }else{
            this.generateCheckPlanByAddCheck(a09TbSpotCheckDetail);
        }
    }

    /**
     * 根据点检异常表生成周检计划
     * 新增 修改 共用方法
     * @param a09TbSpotCheckDetail
     * @return
     */
    public A10TbEquipmentCheckPlan setCheckPlanByCheck(A09TbSpotCheckDetail a09TbSpotCheckDetail,A10TbEquipmentCheckPlan checkPlan){

        A09TbSpotCheck spotCheck = a09TbSpotCheckService.findDataById(a09TbSpotCheckDetail.getTscId());
        getGenerateCheckPlan(checkPlan,spotCheck.getCheckCode(),spotCheck.getTscId(),CheckPlanSourceEnum.CheckExceptionCode.getName());

        A07TbEquipmentMaintenance maintenance = a07TbEquipmentMaintenanceService.findDataById(spotCheck.getSourceId());
        this.generateCheckPlanByMaintenance(maintenance,checkPlan);

        checkPlan.setPosition(spotCheck.getPosition());
        checkPlan.setCheckContent(spotCheck.getCheckRequirements());
        checkPlan.setPlanDate(spotCheck.getDate());

        return checkPlan;
    }

    /**
     * 根据预防周期计划 生成周检计划
     * @param a07TbEquipmentMaintenances
     */
    public void generateCheckPlanByMaintenance(List<A07TbEquipmentMaintenance> a07TbEquipmentMaintenances) {
        List<A10TbEquipmentCheckPlan> planList = new ArrayList<>();
        a07TbEquipmentMaintenances.forEach(m -> {
            A10TbEquipmentCheckPlan plan = new A10TbEquipmentCheckPlan();
            getGenerateCheckPlan(plan,m.getMaintenanceCode(),m.getMaintenanceId(),CheckPlanSourceEnum.EquipmentMaintenanceCode.getName());
            generateCheckPlanByMaintenance(m,plan);
            planList.add(plan);
        });
        a10TbEquipmentCheckPlanMapper.batchSaveCheckPlan(planList);
    }

    public void setPlanByMaintenance(A07TbEquipmentMaintenance maintenance,A10TbEquipmentCheckPlan checkPlan){
        checkPlan.setCheckContent(maintenance.getProject());
        checkPlan.setDemandTool(maintenance.getTool());
        checkPlan.setDemandKineticEnergy(maintenance.getDemand());
        checkPlan.setPlanDate(maintenance.getStartTime());
        if(maintenance.getIsDown() != null){
            checkPlan.setDemandDown(String.valueOf(maintenance.getIsDown()));
        }
        if(maintenance.getIsMaker() != null){
            checkPlan.setDolSupport(String.valueOf(maintenance.getIsMaker()));
        }
    }

    public void setPlanByEquipmentParam(A07TbEquipmentMaintenance maintenance,A10TbEquipmentCheckPlan checkPlan){
        String tbEquipmentParamId = maintenance.getTbEquipmentParamId();
        if(tbEquipmentParamId == null){
            return ;
        }
        A08TbEquipmentParam equipmentParam = a08TbEquipmentParamService.findDataById(tbEquipmentParamId);
        if(equipmentParam != null){
            checkPlan.setPosition(equipmentParam.getMaintenanceParts());
            checkPlan.setTbEquipmentParamId(equipmentParam.getTepsId());
        }
    }

    /**
     * 周检计划设置设备信息
     * @param assetsId
     * @param plan
     */
    public void setPlanByEquipmentAsset(String assetsId, A10TbEquipmentCheckPlan plan){
        if(assetsId == null){
            return;
        }
        A01EquipmentAssetsBean asset = a01EquipmentAsstsService.findBeanById(assetsId);
        if(asset !=null){
            plan.setEquipmentId(asset.getTeaId());
            plan.setType(asset.getEquipmentType());
        }
    }

    /**
     * 根据预期防护生成周检计划
     *
     * @param a07TbEquipmentMaintenance
     * @return
     */
    private A10TbEquipmentCheckPlan generateCheckPlanByMaintenance(A07TbEquipmentMaintenance a07TbEquipmentMaintenance, A10TbEquipmentCheckPlan plan) {
        this.setPlanByMaintenance(a07TbEquipmentMaintenance,plan);
        this.setPlanByEquipmentParam(a07TbEquipmentMaintenance,plan);
        this.setPlanByEquipmentAsset(a07TbEquipmentMaintenance.getTbEquipmentAssetsId(),plan);
        return plan;
    }

    /**
     * 通过设备故障清单生成周检计划
     *
     * @param failureDetail
     */
    public void generateCheckPlanByAddFailure(A11TbEquipmentFailureDetail failureDetail) {
        A10TbEquipmentCheckPlan checkPlan = this.setCheckPlanByFailure(failureDetail, new A10TbEquipmentCheckPlan());
        this.addData(checkPlan);
    }
    public A10TbEquipmentCheckPlan setCheckPlanByFailure(A11TbEquipmentFailureDetail failureDetail,A10TbEquipmentCheckPlan checkPlan){
        getGenerateCheckPlan(checkPlan,failureDetail.getFailureCode(),failureDetail.getTefdId(),CheckPlanSourceEnum.EquipmentFailureCode.getName());
        checkPlan.setCheckContent(failureDetail.getFailureDetail());
        setPlanByEquipmentAsset(failureDetail.getEquipmentId(),checkPlan);
        return checkPlan;
    }

    public void generateCheckPlanByUpdateFailure(A11TbEquipmentFailureDetail failureDetail) {
        A10TbEquipmentCheckPlan plan = this.getGeneratePlan(CheckPlanSourceEnum.EquipmentFailureCode.getName(), failureDetail.getTefdId());
        if(plan != null){
            this.setCheckPlanByFailure(failureDetail,plan);
            this.updateData(plan);
        }
    }

    /**
     * 通过维修申请生成周计划
     *
     */
    public void generateCheckPlanByAddRepair(A12TbRepairApplication a12TbRepairApplication){
        A10TbEquipmentCheckPlan checkPlan = this.setCheckPlanByRepair(a12TbRepairApplication, new A10TbEquipmentCheckPlan());
        this.addData(checkPlan);
    }

    public A10TbEquipmentCheckPlan setCheckPlanByRepair(A12TbRepairApplication a12TbRepairApplication,A10TbEquipmentCheckPlan checkPlan){
        getGenerateCheckPlan(checkPlan,a12TbRepairApplication.getRepairCode(),a12TbRepairApplication.getTraId(),CheckPlanSourceEnum.EquipmentRepairCode.getName());
        checkPlan.setTraId(a12TbRepairApplication.getTraId());
        setPlanByEquipmentAsset(a12TbRepairApplication.getEquipmentId(),checkPlan);
        checkPlan.setCheckContent(a12TbRepairApplication.getRepairObjective());
        return checkPlan;
    }

    public void generateCheckPlanByUpdateRepair(A12TbRepairApplication a12TbRepairApplication){
        A10TbEquipmentCheckPlan plan = this.getGeneratePlan(CheckPlanSourceEnum.EquipmentRepairCode.getName(), a12TbRepairApplication.getTraId());
        if(plan != null){
            A10TbEquipmentCheckPlan checkPlan = this.setCheckPlanByRepair(a12TbRepairApplication, new A10TbEquipmentCheckPlan());
            this.addData(checkPlan);
        }
    }


    /**
     * 当源头数据删除时，删除周计划
     * @param fromName
     * @param planFromId
     */
    public void deletePlanByFrom(String fromName,String planFromId) {
        A10TbEquipmentCheckPlan plan = this.getGeneratePlan(fromName, planFromId);
        if(plan != null){
            this.deleteData(plan);
        }
    }


    /**
     * 新增设备周检计划数据
     *
     * @param checkPlan
     * @return
     */
    public boolean addData(A10TbEquipmentCheckPlan checkPlan) {
        try {
            checkPlan.setPlanCode(CodeUtil.generateCode(CodePrefix.ZJJH.getPrefix()));
            checkPlan.setTecpId(StringUtils.getUUID());
            return a10TbEquipmentCheckPlanMapper.insert(checkPlan) >= 0;
        } catch (Exception e) {
            log.info("{}", e);
            throw new RuntimeException("新增异常：" + e.getMessage());
        }
    }


    /**
     * 删除设备周检计划数据
     * 后续的维修记录
     * 后续的设备履历信息也要删除
     *
     * @param checkPlan
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public boolean deleteData(A10TbEquipmentCheckPlan checkPlan) {
        try {
            A10TbEquipmentCheckPlan delParam = new A10TbEquipmentCheckPlan();
            delParam.setTecpId(checkPlan.getTecpId());
            delParam.setIsDel(1);
            a10TbEquipmentCheckPlanMapper.updateByPrimaryKey(delParam);
            a12TbMaintenanceRecordLedgerService.generateRecordByDelete(checkPlan);
            return true;
        } catch (Exception e) {
            log.info("{}", e);
            throw new RuntimeException("删除异常：" + e.getMessage());
        }
    }


    public boolean completeData(A10TbEquipmentCheckPlan checkPlan) {
        try {
            A10TbEquipmentCheckPlan plan1 = a10TbEquipmentCheckPlanMapper.selectByPrimaryKey(checkPlan.getTecpId());
            checkPlan.setPlanFromId(plan1.getPlanFromId());
            checkPlan.setStatus(CheckPlanStatus.FINISHED.getStatus());
            a10TbEquipmentCheckPlanMapper.updateByPrimaryKey(checkPlan);
            a12TbMaintenanceRecordLedgerService.generateRecordByUpdateCheckPlan(checkPlan);
            if(StringUtil.isNotEmpty(checkPlan.getTraId())){
                A12TbRepairApplication a12TbRepairApplication = a12TbRepairApplicationMapper.selectByPrimaryKey(checkPlan.getTraId());
                a12TbRepairApplication.setStatus("已申请已完成");
                a12TbRepairApplicationMapper.updateByPrimaryKey(a12TbRepairApplication);
            }
            if(checkPlan.getPlanFromId()!=null && checkPlan.getPlanFrom().equals(CheckPlanSourceEnum.CheckExceptionCode.getName())){
                A10TbEquipmentCheckPlan plan = this.getGeneratePlan(CheckPlanSourceEnum.CheckExceptionCode.getName(), checkPlan.getPlanFromId());
                if(plan != null){
                    a09TbSpotCheckService.updateCheckStatusById(plan.getPlanFromId(),2);
                }
            }
            return  true;
        } catch (Exception e) {
            log.info("{}", e);
            throw new RuntimeException("修改异常：" + e.getMessage());
        }
    }

    /**
     * 更新设备周检计划数据
     *
     * @param checkPlan
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public boolean updateData(A10TbEquipmentCheckPlan checkPlan) {
        try {
            A10TbEquipmentCheckPlan plan1 = a10TbEquipmentCheckPlanMapper.selectByPrimaryKey(checkPlan.getTecpId());
            checkPlan.setPlanFromId(plan1.getPlanFromId());
            a10TbEquipmentCheckPlanMapper.updateByPrimaryKey(checkPlan);
            return true;
        } catch (Exception e) {
            log.info("{}", e);
            throw new RuntimeException("修改异常：" + e.getMessage());
        }
    }

    /**
     * 获取生成周计划的源头数据
     * @param planFrom 计划来源
     * @param planFormId 计划来源Id
     * @return
     */
    public A10TbEquipmentCheckPlan getGeneratePlan(String planFrom, String planFormId) {
        Example example = new Example(A10TbEquipmentCheckPlan.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("planFrom",planFrom);
        criteria.andEqualTo("planFromId",planFormId);
        criteria.andEqualTo("isDel",0);
        List<A10TbEquipmentCheckPlan> planList = a10TbEquipmentCheckPlanMapper.selectByExample(example);
        if(planList != null && planList.size()>0){
            return planList.get(0);
        }
        return null;
    }


    public A10TbEquipmentCheckPlan getCheckPlanByIdAndForm(String planFrom, String id) {
        Example example = new Example(A10TbEquipmentCheckPlan.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("planFrom",planFrom);
        criteria.andEqualTo("tecpId",id);
        criteria.andEqualTo("isDel",0);
        List<A10TbEquipmentCheckPlan> planList = a10TbEquipmentCheckPlanMapper.selectByExample(example);
        if(planList != null && planList.size()>0){
            return planList.get(0);
        }
        return null;
    }
    /**
     * 查询设备周检计划数据列表
     * 分页
     *
     * @param pageObject
     * @return
     */
    public PageBean<A10TbEquipmentCheckPlan> findDataList(PageObject<A10TbEquipmentCheckPlan> pageObject) {
        A10TbEquipmentCheckPlan checkPlan = pageObject.getData();
        if(checkPlan!=null ){
            String equipmentCode = checkPlan.getEquipmentCode();
            String equipmentName = checkPlan.getEquipmentName();
            if( StringUtils.isNotEmpty(equipmentCode)){
                checkPlan.setEquipmentCode("%"+equipmentCode+"%");
            }
            if(StringUtils.isNotEmpty(equipmentName)){
                checkPlan.setEquipmentName("%"+equipmentName+"%");
            }
        }
        List<A10TbEquipmentCheckPlanVo> a10TbSpotCheckList = a10TbEquipmentCheckPlanMapper.queryCheckPlan(checkPlan);
        return ListPageUtil.pageInfo(pageObject,a10TbSpotCheckList);
    }


}
