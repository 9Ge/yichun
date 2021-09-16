package com.enercomn.web.A12Repair.controller;

import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.utils.ResultMsg;
import com.enercomn.web.A12Repair.bean.A12TbRepairApplication;
import com.enercomn.web.A12Repair.service.A12TbRepairApplicationService;
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
 * @Date: 2021/8/16 13:26<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("repair/application")
@Api(value = "维修申请单控制器", tags = "a-12-maintenance-record-application-controller-rest")
public class A12TbRepairApplicationController {

    private final A12TbRepairApplicationService a12TbRepairApplicationService;

    public A12TbRepairApplicationController(A12TbRepairApplicationService a12TbRepairApplicationService) {
        this.a12TbRepairApplicationService = a12TbRepairApplicationService;
    }


    @PostMapping("addData")
    @ApiOperation(value = "新增维修申请单数据")
    @PublicProperties(saveSet = true)
    public ResultMsg addData(@RequestBody A12TbRepairApplication repairApplication) {
        try {
            return new ResultMsg(a12TbRepairApplicationService.addData(repairApplication));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),repairApplication);
            return new ResultMsg(ResultStatusCode.ADD_DATA_FAIL.getResultCode(), ResultStatusCode.ADD_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("delData")
    @ApiOperation(value = "删除维修申请单数据")
    public ResultMsg delData(@RequestBody A12TbRepairApplication repairApplication) {
        try {
            return new ResultMsg(a12TbRepairApplicationService.deleteData(repairApplication));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),repairApplication);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }

    @PostMapping("updateData")
    @ApiOperation(value = "更新维修申请单数据")
    @PublicProperties(updateSet = true)
    public ResultMsg updateData(@RequestBody A12TbRepairApplication repairApplication) {
        try {
            return new ResultMsg(a12TbRepairApplicationService.updateData(repairApplication));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),repairApplication);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("findDataList")
    @ApiOperation(value = "查询维修申请单数据[分页]")
    public ResultMsg findDataList(@RequestBody PageObject<A12TbRepairApplication> pageObject) {
        try {
            return new ResultMsg(a12TbRepairApplicationService.findDataList(pageObject));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),pageObject);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(), ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }

}
