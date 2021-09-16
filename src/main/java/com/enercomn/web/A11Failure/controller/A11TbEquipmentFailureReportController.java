package com.enercomn.web.A11Failure.controller;

import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.utils.ResultMsg;
import com.enercomn.web.A11Failure.bean.A11TbEquipmentFailureReport;
import com.enercomn.web.A11Failure.service.A11TbEquipmentFailureReportService;
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
 * @Date: 2021/8/16 11:45<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("a11_failure_report")
@Api(value = "设备故障报告单控制器", tags = "a-11-equipment-failure-report-controller-rest")
public class A11TbEquipmentFailureReportController {

    private final A11TbEquipmentFailureReportService a11TbEquipmentFailureReportService;

    public A11TbEquipmentFailureReportController(A11TbEquipmentFailureReportService a11TbEquipmentFailureReportService) {
        this.a11TbEquipmentFailureReportService = a11TbEquipmentFailureReportService;
    }


    @PostMapping("addData")
    @ApiOperation(value = "新增设备故障报告数据")
    @PublicProperties(saveSet = true)
    public ResultMsg addData(@RequestBody A11TbEquipmentFailureReport failureReport) {
        try {
            return new ResultMsg(a11TbEquipmentFailureReportService.addData(failureReport));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),failureReport);
            return new ResultMsg(ResultStatusCode.ADD_DATA_FAIL.getResultCode(), ResultStatusCode.ADD_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("delData")
    @ApiOperation(value = "删除设备故障报告数据")
    public ResultMsg delData(@RequestBody A11TbEquipmentFailureReport failureReport) {
        try {
            return new ResultMsg(a11TbEquipmentFailureReportService.deleteData(failureReport));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),failureReport);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }

    @PostMapping("updateData")
    @ApiOperation(value = "更新设备故障报告数据")
    @PublicProperties(updateSet = true)
    public ResultMsg updateData(@RequestBody A11TbEquipmentFailureReport failureReport) {
        try {
            return new ResultMsg(a11TbEquipmentFailureReportService.updateData(failureReport));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),failureReport);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("findDataList")
    @ApiOperation(value = "查询设备故障报告数据[分页]")
    public ResultMsg findDataList(@RequestBody PageObject<A11TbEquipmentFailureReport> pageObject) {
        try {
            return new ResultMsg(a11TbEquipmentFailureReportService.findDataList(pageObject));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),pageObject);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(), ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }

}
