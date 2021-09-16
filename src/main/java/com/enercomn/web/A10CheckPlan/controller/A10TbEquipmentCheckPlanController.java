package com.enercomn.web.A10CheckPlan.controller;

import com.enercomn.Enum.CheckPlanStatus;
import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.utils.ResultMsg;
import com.enercomn.web.A10CheckPlan.bean.A10TbEquipmentCheckPlan;
import com.enercomn.web.A10CheckPlan.service.A10TbEquipmentCheckPlanService;
import com.enercomn.web.baseManager.model.PageObject;
import com.enercomn.web.handle.PublicProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date: 2021/8/16 11:03<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("check/plan")
@Api(value = "设备周检修计划控制器", tags = "a-10-equipment-check-plan-controller-rest")
public class A10TbEquipmentCheckPlanController {

    private final A10TbEquipmentCheckPlanService a10TbEquipmentCheckPlanService;

    public A10TbEquipmentCheckPlanController(A10TbEquipmentCheckPlanService a10TbEquipmentCheckPlanService) {
        this.a10TbEquipmentCheckPlanService = a10TbEquipmentCheckPlanService;
    }

    @PostMapping("addData")
    @ApiOperation(value = "新增设备周检计划数据")
    @PublicProperties(saveSet = true)
    public ResultMsg addData(@RequestBody A10TbEquipmentCheckPlan checkPlan) {
        try {
            return new ResultMsg(a10TbEquipmentCheckPlanService.addData(checkPlan));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),checkPlan);
            return new ResultMsg(ResultStatusCode.ADD_DATA_FAIL.getResultCode(), ResultStatusCode.ADD_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("delData")
    @ApiOperation(value = "删除设备周检计划数据")
    public ResultMsg delData(@RequestBody A10TbEquipmentCheckPlan checkPlan) {
        try {
            return new ResultMsg(a10TbEquipmentCheckPlanService.deleteData(checkPlan));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),checkPlan);
            return new ResultMsg(ResultStatusCode.REQUEST_FAIL.getResultCode(), ResultStatusCode.REQUEST_FAIL.getResultMessage());
        }
    }

    @PostMapping("updateData")
    @ApiOperation(value = "更新设备周检计划数据")
    @PublicProperties(updateSet = true)
    public ResultMsg updateData(@RequestBody A10TbEquipmentCheckPlan checkPlan) {
        try {
            if(CheckPlanStatus.FINISHED.getStatus().equals(checkPlan.getStatus())){
                return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage(),"当前周检计划已完成");
            }
            return new ResultMsg(a10TbEquipmentCheckPlanService.updateData(checkPlan));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),checkPlan);
            return new ResultMsg(ResultStatusCode.REQUEST_FAIL.getResultCode(), ResultStatusCode.REQUEST_FAIL.getResultMessage());
        }
    }

    @PostMapping("completeData")
    @ApiOperation(value = "更新设备周检计划状态为已完成")
    public ResultMsg completeData(@RequestBody A10TbEquipmentCheckPlan checkPlan) {
        try {
            if(CheckPlanStatus.FINISHED.getStatus().equals(checkPlan.getStatus())){
                return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage(),"当前周检计划已完成");
            }
            return new ResultMsg(a10TbEquipmentCheckPlanService.completeData(checkPlan));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),checkPlan);
            return new ResultMsg(ResultStatusCode.REQUEST_FAIL.getResultCode(), ResultStatusCode.REQUEST_FAIL.getResultMessage());
        }
    }

    @PostMapping("findDataList")
    @ApiOperation(value = "查询设备周检计划数据[分页]")
    public ResultMsg findDataList(@RequestBody PageObject<A10TbEquipmentCheckPlan> pageObject) {
        try {
            return new ResultMsg(a10TbEquipmentCheckPlanService.findDataList(pageObject));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),pageObject);
            return new ResultMsg(ResultStatusCode.REQUEST_FAIL.getResultCode(), ResultStatusCode.REQUEST_FAIL.getResultMessage());
        }
    }

}
