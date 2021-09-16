package com.enercomn.web.A12Repair.service;

import com.enercomn.Enum.CheckPlanSourceEnum;
import com.enercomn.Enum.CodePrefix;
import com.enercomn.utils.CodeUtil;
import com.enercomn.utils.ListPageUtil;
import com.enercomn.utils.PageBean;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A10CheckPlan.service.A10TbEquipmentCheckPlanService;
import com.enercomn.web.A12Repair.bean.A12TbRepairApplication;
import com.enercomn.web.A12Repair.bean.A12TbRepairApplicationVo;
import com.enercomn.web.A12Repair.mapper.A12TbRepairApplicationMapper;
import com.enercomn.web.baseManager.model.PageObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Date: 2021/8/16 13:25<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Service
public class A12TbRepairApplicationService {

    private final A12TbRepairApplicationMapper a12TbRepairApplicationMapper;
    private final A10TbEquipmentCheckPlanService a10TbEquipmentCheckPlanService;

    public A12TbRepairApplicationService(A12TbRepairApplicationMapper a12TbRepairApplicationMapper, A10TbEquipmentCheckPlanService a10TbEquipmentCheckPlanService) {
        this.a12TbRepairApplicationMapper = a12TbRepairApplicationMapper;
        this.a10TbEquipmentCheckPlanService = a10TbEquipmentCheckPlanService;
    }


    /**
     * 新增维修申请单数据
     *
     * @param repairApplication
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public boolean addData(A12TbRepairApplication repairApplication) {
        try {
            repairApplication.setRepairCode(CodeUtil.generateCode(CodePrefix.WXSQ.getPrefix()));
            repairApplication.setTraId(StringUtils.getUUID());
            a12TbRepairApplicationMapper.insert(repairApplication);
            a10TbEquipmentCheckPlanService.generateCheckPlanByAddRepair(repairApplication);
            this.updateStatus(repairApplication,"已申请未完成");
            return  true;
        } catch (Exception e) {
            log.info("{}", e);
            throw new RuntimeException("新增异常：" + e.getMessage());
        }
    }


    /**
     * 删除维修申请单数据
     *
     * @param repairApplication
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public boolean deleteData(A12TbRepairApplication repairApplication) {
        try {
            A12TbRepairApplication delParam = new A12TbRepairApplication();
            delParam.setTraId(repairApplication.getTraId());
            delParam.setIsDel(1);
            a12TbRepairApplicationMapper.updateByPrimaryKey(delParam);
            a10TbEquipmentCheckPlanService.deletePlanByFrom(CheckPlanSourceEnum.EquipmentRepairCode.getName(),repairApplication.getTraId());
            return true;
        } catch (Exception e) {
            log.info("{}", repairApplication);
            throw new RuntimeException("删除异常：" + e.getMessage());
        }
    }


    /**
     * 更新维修申请单数据
     *
     * @param repairApplication
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public boolean updateData(A12TbRepairApplication repairApplication) {
        try {
            a12TbRepairApplicationMapper.updateByPrimaryKey(repairApplication);
            a10TbEquipmentCheckPlanService.generateCheckPlanByUpdateRepair(repairApplication);
            return true;
        } catch (Exception e) {
            log.info("{}", repairApplication);
            throw new RuntimeException("修改异常：" + e.getMessage());
        }
    }

    /**
     * 设置维修申请状态
     * @param repairApplication
     * @param status
     * @return
     */
    public boolean updateStatus(A12TbRepairApplication repairApplication,String status){
        A12TbRepairApplication a12TbRepairApplication = a12TbRepairApplicationMapper.selectByPrimaryKey(repairApplication);
        a12TbRepairApplication.setStatus(status);
        return a12TbRepairApplicationMapper.updateByPrimaryKey(a12TbRepairApplication) >= 0;
    }


    /**
     * 查询维修申请单数据列表
     * 分页
     * @param pageObject
     * @return
     */
    public PageBean findDataList(PageObject<A12TbRepairApplication> pageObject) {
        List<A12TbRepairApplicationVo> a10TbSpotCheckList = a12TbRepairApplicationMapper.queryRepairApplication(pageObject.getData());
        return ListPageUtil.pageInfo(pageObject,a10TbSpotCheckList);
    }



}
