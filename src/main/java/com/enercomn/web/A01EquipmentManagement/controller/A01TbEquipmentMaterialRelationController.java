package com.enercomn.web.A01EquipmentManagement.controller;

import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.utils.ResultMsg;
import com.enercomn.web.A01EquipmentManagement.bean.A01EquipmentAssetsBean;
import com.enercomn.web.A01EquipmentManagement.bean.A01TbEquipmentMaterialRelation;
import com.enercomn.web.A01EquipmentManagement.service.A01TbEquipmentMaterialRelationService;
import com.enercomn.web.baseManager.model.PageObject;
import com.enercomn.web.handle.PublicProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Date: 2021/8/11 16:54<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/relation")
@Api(value = "设备和物料关联表" ,tags = "a-01-relation-controller-rest")
public class A01TbEquipmentMaterialRelationController {

    public final A01TbEquipmentMaterialRelationService a01TbEquipmentMaterialRelationService;

    public A01TbEquipmentMaterialRelationController(A01TbEquipmentMaterialRelationService a01TbEquipmentMaterialRelationService) {
        this.a01TbEquipmentMaterialRelationService = a01TbEquipmentMaterialRelationService;
    }

    @PostMapping("addData")
    @ApiOperation(value = "新增设备与物料关系")
    @PublicProperties(saveSet = true)
    public ResultMsg addData(@RequestBody A01TbEquipmentMaterialRelation relation){
        try {
            return new ResultMsg(a01TbEquipmentMaterialRelationService.addData(relation));
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResultMsg(ResultStatusCode.ADD_DATA_FAIL.getResultCode(),ResultStatusCode.ADD_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("delData")
    @ApiOperation(value = "删除设备与物料关系")
    public ResultMsg delData(@RequestBody A01TbEquipmentMaterialRelation relation){
        try {
            return new ResultMsg(a01TbEquipmentMaterialRelationService.updateData(relation));
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(),ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }

    @PostMapping("updateData")
    @ApiOperation(value = "更新设备与物料关系")
    @PublicProperties(updateSet = true)
    public ResultMsg updateData(@RequestBody A01TbEquipmentMaterialRelation relation){
        try {
            return new ResultMsg(a01TbEquipmentMaterialRelationService.updateData(relation));
        } catch (Exception e) {
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(),ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("findDataList")
    @ApiOperation(value = "通过设备Id查找设备下的物料列表")
    public ResultMsg findDataList(@RequestBody PageObject<A01EquipmentAssetsBean> pageObject){
        try {
            return new ResultMsg(a01TbEquipmentMaterialRelationService.findDataListByTeaId(pageObject));
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(),ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }






}
