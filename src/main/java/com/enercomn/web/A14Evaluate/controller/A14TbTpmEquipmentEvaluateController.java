package com.enercomn.web.A14Evaluate.controller;

import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.utils.ResultMsg;
import com.enercomn.web.A14Evaluate.bean.A14TbTpmEquipmentEvaluate;
import com.enercomn.web.A14Evaluate.service.A14TbTpmEquipmentEvaluateService;
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
 * @Date: 2021/8/16 15:01<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("tpm_evaluate")
@Api(value = "设备TPM管理综合评价表控制器", tags = "a-14-tpm-equipment-evaluate-controller-rest")
public class A14TbTpmEquipmentEvaluateController {

    private final A14TbTpmEquipmentEvaluateService a14TbTpmEquipmentEvaluateService;

    public A14TbTpmEquipmentEvaluateController(A14TbTpmEquipmentEvaluateService a14TbTpmEquipmentEvaluateService) {
        this.a14TbTpmEquipmentEvaluateService = a14TbTpmEquipmentEvaluateService;
    }


    @PostMapping("addData")
    @ApiOperation(value = "新增设备TPM管理综合评价表数据")
    @PublicProperties(saveSet = true)
    public ResultMsg addData(@RequestBody A14TbTpmEquipmentEvaluate evaluate) {
        try {
            return new ResultMsg(a14TbTpmEquipmentEvaluateService.addData(evaluate));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),evaluate);
            return new ResultMsg(ResultStatusCode.ADD_DATA_FAIL.getResultCode(), ResultStatusCode.ADD_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("delData")
    @ApiOperation(value = "删除设备TPM管理综合评价表数据")
    public ResultMsg delData(@RequestBody A14TbTpmEquipmentEvaluate evaluate) {
        try {
            return new ResultMsg(a14TbTpmEquipmentEvaluateService.deleteData(evaluate));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),evaluate);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }

    @PostMapping("updateData")
    @ApiOperation(value = "更新设备TPM管理综合评价表数据")
    @PublicProperties(updateSet = true)
    public ResultMsg updateData(@RequestBody A14TbTpmEquipmentEvaluate evaluate) {
        try {
            return new ResultMsg(a14TbTpmEquipmentEvaluateService.updateData(evaluate));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),evaluate);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("findDataList")
    @ApiOperation(value = "查询设备TPM管理综合评价表数据[分页]")
    public ResultMsg findDataList(@RequestBody PageObject<A14TbTpmEquipmentEvaluate> pageObject) {
        try {
            return new ResultMsg(a14TbTpmEquipmentEvaluateService.findDataList(pageObject));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),pageObject);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(), ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }
}
