package com.enercomn.web.A01EquipmentManagement.controller;

import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.utils.ResultMsg;
import com.enercomn.web.A01EquipmentManagement.bean.A01EquipmentAssetsBean;
import com.enercomn.web.A01EquipmentManagement.bean.A01TbEquipmentMaterialRelation;
import com.enercomn.web.A01EquipmentManagement.bean.A01TbEquipmentResume;
import com.enercomn.web.A01EquipmentManagement.service.A01TbEquipmentResumeService;
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
 * @Date: 2021/8/11 16:54<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/resume")
@Api(value = "设备履历表控制器" ,tags = "a-01-resume-controller-rest")
public class A01TbEquipmentResumeController {

    public final A01TbEquipmentResumeService a01TbEquipmentResumeService;

    public A01TbEquipmentResumeController(A01TbEquipmentResumeService a01TbEquipmentResumeService) {
        this.a01TbEquipmentResumeService = a01TbEquipmentResumeService;
    }


    @PostMapping("addData")
    @ApiOperation(value = "新增设备履历表")
    @PublicProperties(saveSet = true)
    public ResultMsg addData(@RequestBody A01TbEquipmentResume resume){
        try {
            return new ResultMsg(a01TbEquipmentResumeService.addData(resume));
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResultMsg(ResultStatusCode.ADD_DATA_FAIL.getResultCode(),ResultStatusCode.ADD_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("delData")
    @ApiOperation(value = "删除设备履历表")
    public ResultMsg delData(@RequestBody A01TbEquipmentResume resume){
        try {
            return new ResultMsg(a01TbEquipmentResumeService.delData(resume));
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(),ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }

    @PostMapping("updateData")
    @ApiOperation(value = "更新设备履历表")
    @PublicProperties(updateSet = true)
    public ResultMsg updateData(@RequestBody A01TbEquipmentResume resume){
        try {
            return new ResultMsg(a01TbEquipmentResumeService.updateData(resume));
        } catch (Exception e) {
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(),ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("findDataList")
    @ApiOperation(value = "查询设备履历表[分页]")
    public ResultMsg findDataList(@RequestBody PageObject<A01TbEquipmentResume> pageObject){
        try {
            return new ResultMsg(a01TbEquipmentResumeService.findDataList(pageObject));
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(),ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }

}
