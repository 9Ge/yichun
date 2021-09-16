package com.enercomn.web.A06EquipmentMaterial.controller;

import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.utils.ResultMsg;
import com.enercomn.web.A06EquipmentMaterial.bean.A06TbEquipmentMaterial;
import com.enercomn.web.A06EquipmentMaterial.service.A06TbEquipmentMaterialService;
import com.enercomn.web.baseManager.model.PageObject;
import com.enercomn.web.handle.PublicProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date: 2021/8/10 16:43<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Api(value = "物料信息表操作控制器",tags = "a-06-material-controller-rest")
@RestController
@RequestMapping("material")
public class A06TbEquipmentMaterialController {

    private final A06TbEquipmentMaterialService a06TbEquipmentMaterialService;

    public A06TbEquipmentMaterialController(A06TbEquipmentMaterialService a06TbEquipmentMaterialService) {
        this.a06TbEquipmentMaterialService = a06TbEquipmentMaterialService;
    }

    @ApiOperation(value = "新增物料信息数据")
    @Authorization("yangon")
    @PostMapping("/addData")
    @PublicProperties(saveSet = true)
    public ResultMsg addData(@RequestBody A06TbEquipmentMaterial material){
        try {
            return new ResultMsg( a06TbEquipmentMaterialService.addData(material));
        } catch (RuntimeException e) {
            log.error(e.getMessage()+"请求对象：{}",material);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(),ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }
    @ApiOperation(value = "删除物料信息数据")
    @Authorization("yangon")
    @PostMapping("/delData")
    public ResultMsg delData(@RequestBody A06TbEquipmentMaterial material){
        try {
            return new ResultMsg( a06TbEquipmentMaterialService.deleteData(material));
        } catch (RuntimeException e) {
            log.error(e.getMessage()+"请求对象：{}",material);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(),ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }

    @ApiOperation(value = "修改物料信息数据")
    @Authorization("yangon")
    @PostMapping("/updateData")
    @PublicProperties(updateSet = true)
    public ResultMsg updateData(@RequestBody A06TbEquipmentMaterial material){
        try {
            return new ResultMsg( a06TbEquipmentMaterialService.updateData(material));
        } catch (RuntimeException e) {
            log.error(e.getMessage()+"请求对象：{}",material);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(),ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }

    @ApiOperation(value = "物料信息数据列表[分页]")
    @Authorization("yangon")
    @PostMapping("/findDataList")
    public ResultMsg findDataList(@RequestBody PageObject<A06TbEquipmentMaterial> pageObject){
        try {
            return new ResultMsg( a06TbEquipmentMaterialService.findDataList(pageObject));
        } catch (RuntimeException e) {
            log.error(e.getMessage()+"请求对象：{}",pageObject);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(),ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }

    @ApiOperation(value = "[下拉框]物料信息查询,供设备选择")
    @Authorization("yangon")
    @PostMapping("/query/material")
    public ResultMsg findDataList(){
        try {
            return new ResultMsg( a06TbEquipmentMaterialService.queryMaterial2Equipment());
        } catch (RuntimeException e) {
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(),ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }
}
