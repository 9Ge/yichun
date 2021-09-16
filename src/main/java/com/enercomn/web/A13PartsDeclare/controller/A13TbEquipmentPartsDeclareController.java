package com.enercomn.web.A13PartsDeclare.controller;

import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.utils.ResultMsg;
import com.enercomn.web.A13PartsDeclare.bean.A13TbEquipmentPartsDeclare;
import com.enercomn.web.A13PartsDeclare.service.A13TbEquipmentPartsDeclareService;
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
 * @Date: 2021/8/16 14:00<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("parts/declare")
@Api(value = "备件申报表控制器", tags = "a-13-equipment_parts-declare-controller-rest")
public class A13TbEquipmentPartsDeclareController {

    private final A13TbEquipmentPartsDeclareService a13TbEquipmentPartsDeclareService;

    public A13TbEquipmentPartsDeclareController(A13TbEquipmentPartsDeclareService a13TbEquipmentPartsDeclareService) {
        this.a13TbEquipmentPartsDeclareService = a13TbEquipmentPartsDeclareService;
    }


    @PostMapping("addData")
    @ApiOperation(value = "新增备件申请单数据")
    @PublicProperties(saveSet = true)
    public ResultMsg addData(@RequestBody A13TbEquipmentPartsDeclare partsDeclare) {
        try {
            return new ResultMsg(a13TbEquipmentPartsDeclareService.addData(partsDeclare));
        } catch (RuntimeException  e) {
            log.info("{}:异常"+e.getMessage(),partsDeclare);
            return new ResultMsg(ResultStatusCode.ADD_DATA_FAIL.getResultCode(), ResultStatusCode.ADD_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("delData")
    @ApiOperation(value = "删除备件申请单数据")
    public ResultMsg delData(@RequestBody A13TbEquipmentPartsDeclare partsDeclare) {
        try {
            return new ResultMsg(a13TbEquipmentPartsDeclareService.deleteData(partsDeclare));
        } catch (RuntimeException  e) {
            log.info("{}:异常"+e.getMessage(),partsDeclare);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }

    @PostMapping("updateData")
    @ApiOperation(value = "更新备件申请单数据")
    @PublicProperties(updateSet = true)
    public ResultMsg updateData(@RequestBody A13TbEquipmentPartsDeclare partsDeclare) {
        try {
            return new ResultMsg(a13TbEquipmentPartsDeclareService.updateData(partsDeclare));
        } catch (RuntimeException  e) {
            log.info("{}:异常"+e.getMessage(),partsDeclare);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("findDataList")
    @ApiOperation(value = "查询备件申请单数据[分页]")
    public ResultMsg findDataList(@RequestBody PageObject<A13TbEquipmentPartsDeclare> pageObject) {
        try {
            return new ResultMsg(a13TbEquipmentPartsDeclareService.findDataList(pageObject));
        } catch (RuntimeException  e) {
            log.info("{}:异常"+e.getMessage(),pageObject);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(), ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }

}
