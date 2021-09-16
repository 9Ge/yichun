package com.enercomn.web.A08EquipmentParam.controller;

import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.utils.ResultMsg;
import com.enercomn.web.A08EquipmentParam.bean.A08TbEquipmentParam;
import com.enercomn.web.A08EquipmentParam.service.A08TbEquipmentParamService;
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
 * @Date: 2021/8/12 17:32<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Api(value = "设备基础参数统计控制器", tags = "a-08-param-controller-rest")
@RestController
@RequestMapping("param")
public class A08TbEquipmentParamController {

    private final A08TbEquipmentParamService a08TbEquipmentParamService;

    public A08TbEquipmentParamController(A08TbEquipmentParamService a08TbEquipmentParamService) {
        this.a08TbEquipmentParamService = a08TbEquipmentParamService;
    }


    @PostMapping("addData")
    @ApiOperation(value = "新增设备基础参数统计表")
    @PublicProperties(saveSet = true)
    public ResultMsg addData(@RequestBody A08TbEquipmentParam param) {
        try {
            return new ResultMsg(a08TbEquipmentParamService.addData(param));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),param);
            return new ResultMsg(ResultStatusCode.ADD_DATA_FAIL.getResultCode(), ResultStatusCode.ADD_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("delData")
    @ApiOperation(value = "删除设备基础参数统计表")
    public ResultMsg delData(@RequestBody A08TbEquipmentParam param) {
        try {
            return new ResultMsg(a08TbEquipmentParamService.deleteData(param));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),param);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }

    @PostMapping("updateData")
    @ApiOperation(value = "更新设备基础参数统计表")
    @PublicProperties(updateSet = true)
    public ResultMsg updateData(@RequestBody A08TbEquipmentParam param) {
        try {
            return new ResultMsg(a08TbEquipmentParamService.updateData(param));
        } catch (Exception e) {
            log.info("{}:异常"+e.getMessage(),param);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("findDataList")
    @ApiOperation(value = "查询设备基础参数统计表[分页]")
    public ResultMsg findDataList(@RequestBody PageObject<A08TbEquipmentParam> pageObject) {
        try {
            return new ResultMsg(a08TbEquipmentParamService.findDataList(pageObject));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),pageObject);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(), ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }

    @PostMapping("paramSelect")
    @ApiOperation(value = "设备基础参数统计表[下拉框]")
    public ResultMsg paramSelect(@RequestBody A08TbEquipmentParam param) {
        try {
            return new ResultMsg(a08TbEquipmentParamService.paramSelect(param));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),param);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(), ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }
}