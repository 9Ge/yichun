package com.enercomn.web.A12Repair.controller;

import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.utils.ResultMsg;
import com.enercomn.web.A12Repair.bean.A12TbMaintenanceRecordLedger;
import com.enercomn.web.A12Repair.service.A12TbMaintenanceRecordLedgerService;
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
 * @Date: 2021/8/16 13:46<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("record/ledger")
@Api(value = "维修记录台账控制器", tags = "a-12-maintenance-record-ledger-controller-rest")
public class A12TbMaintenanceRecordLedgerController {

    private final A12TbMaintenanceRecordLedgerService a12TbMaintenanceRecordLedgerService;

    public A12TbMaintenanceRecordLedgerController(A12TbMaintenanceRecordLedgerService a12TbMaintenanceRecordLedgerService) {
        this.a12TbMaintenanceRecordLedgerService = a12TbMaintenanceRecordLedgerService;
    }


    @PostMapping("addData")
    @ApiOperation(value = "新增维修记录台账数据")
    @PublicProperties(saveSet = true)
    public ResultMsg addData(@RequestBody A12TbMaintenanceRecordLedger recordLedger) {
        try {
            return new ResultMsg(a12TbMaintenanceRecordLedgerService.addData(recordLedger));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),recordLedger);
            return new ResultMsg(ResultStatusCode.ADD_DATA_FAIL.getResultCode(), ResultStatusCode.ADD_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("delData")
    @ApiOperation(value = "删除维修记录台账数据")
    public ResultMsg delData(@RequestBody A12TbMaintenanceRecordLedger recordLedger) {
        try {
            return new ResultMsg(a12TbMaintenanceRecordLedgerService.deleteData(recordLedger));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),recordLedger);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }

    @PostMapping("updateData")
    @ApiOperation(value = "更新维修记录台账数据")
    @PublicProperties(updateSet = true)
    public ResultMsg updateData(@RequestBody A12TbMaintenanceRecordLedger recordLedger) {
        try {
            return new ResultMsg(a12TbMaintenanceRecordLedgerService.updateData(recordLedger));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),recordLedger);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("findDataList")
    @ApiOperation(value = "查询维修记录台账数据[分页]")
    public ResultMsg findDataList(@RequestBody PageObject<A12TbMaintenanceRecordLedger> pageObject) {
        try {
            return new ResultMsg(a12TbMaintenanceRecordLedgerService.findDataList(pageObject));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),pageObject);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(), ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }

}
