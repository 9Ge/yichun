package com.enercomn.web.A11Failure.controller;

import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.utils.ResultMsg;
import com.enercomn.web.A11Failure.bean.A11TbEquipmentFailureDetail;
import com.enercomn.web.A11Failure.service.A11TbEquipmentFailureDetailService;
import com.enercomn.web.baseManager.model.PageObject;
import com.enercomn.web.handle.PublicProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Date: 2021/8/16 11:45<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("failure/detail")
@Api(value = "设备故障记录清单控制器", tags = "a-11-equipment-failure-detail-controller-rest")
@Validated
public class A11TbEquipmentFailureDetailController {

    private final A11TbEquipmentFailureDetailService a11TbEquipmentFailureDetailService;

    public A11TbEquipmentFailureDetailController(A11TbEquipmentFailureDetailService a11TbEquipmentFailureDetailService) {
        this.a11TbEquipmentFailureDetailService = a11TbEquipmentFailureDetailService;
    }


    @PostMapping("addData")
    @ApiOperation(value = "新增设备故障记录数据")
    @PublicProperties(saveSet = true)
    public ResultMsg addData(@Valid @RequestBody A11TbEquipmentFailureDetail failureDetail) {
        try {
            return new ResultMsg(a11TbEquipmentFailureDetailService.addData(failureDetail));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),failureDetail);
            return new ResultMsg(ResultStatusCode.ADD_DATA_FAIL.getResultCode(), ResultStatusCode.ADD_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("delData")
    @ApiOperation(value = "删除设备故障记录数据")
    public ResultMsg delData(@RequestBody A11TbEquipmentFailureDetail failureDetail) {
        try {
            return new ResultMsg(a11TbEquipmentFailureDetailService.deleteData(failureDetail));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),failureDetail);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }

    @PostMapping("updateData")
    @ApiOperation(value = "更新设备故障记录数据")
    @PublicProperties(updateSet = true)
    public ResultMsg updateData(@RequestBody A11TbEquipmentFailureDetail failureDetail) {
        try {
            return new ResultMsg(a11TbEquipmentFailureDetailService.updateData(failureDetail));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),failureDetail);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("findDataList")
    @ApiOperation(value = "查询设备故障记录数据[分页]")
    public ResultMsg findDataList(@RequestBody PageObject<A11TbEquipmentFailureDetail> pageObject) {
        try {
            return new ResultMsg(a11TbEquipmentFailureDetailService.findDataList(pageObject));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),pageObject);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(), ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }

}
