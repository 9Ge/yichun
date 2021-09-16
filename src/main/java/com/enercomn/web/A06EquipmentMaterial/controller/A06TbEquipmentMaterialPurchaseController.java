package com.enercomn.web.A06EquipmentMaterial.controller;

import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.utils.ResultMsg;
import com.enercomn.web.A06EquipmentMaterial.bean.A06TbEquipmentMaterial;
import com.enercomn.web.A06EquipmentMaterial.bean.A06TbEquipmentMaterialPurchase;
import com.enercomn.web.A06EquipmentMaterial.service.A06TbEquipmentMaterialPurchaseService;
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
@Api(value = "物料信息采购表操作控制器",tags = "a-06-material-purchase-controller-rest")
@RestController
@RequestMapping("purchase")
public class A06TbEquipmentMaterialPurchaseController {

    private final A06TbEquipmentMaterialPurchaseService a06TbEquipmentMaterialPurchaseService;

    public A06TbEquipmentMaterialPurchaseController(A06TbEquipmentMaterialPurchaseService a06TbEquipmentMaterialPurchaseService) {
        this.a06TbEquipmentMaterialPurchaseService = a06TbEquipmentMaterialPurchaseService;
    }

    @ApiOperation(value = "新增物料信息采购表数据")
    @Authorization("yangon")
    @PostMapping("/addData")
    @PublicProperties(saveSet = true)
    public ResultMsg addData(@RequestBody A06TbEquipmentMaterialPurchase  materialPurchase){
        try {
            return new ResultMsg( a06TbEquipmentMaterialPurchaseService.addData(materialPurchase));
        } catch (RuntimeException e) {
            log.error(e.getMessage()+"请求对象：{}",materialPurchase);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(),ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }
    @ApiOperation(value = "删除物料信息采购表数据")
    @Authorization("yangon")
    @PostMapping("/delData")
    public ResultMsg delData(@RequestBody A06TbEquipmentMaterialPurchase materialPurchase){
        try {
            return new ResultMsg( a06TbEquipmentMaterialPurchaseService.deleteData(materialPurchase));
        } catch (RuntimeException e) {
            log.error(e.getMessage()+"请求对象：{}",materialPurchase);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(),ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }

    @ApiOperation(value = "修改物料信息采购表数据")
    @Authorization("yangon")
    @PostMapping("/updateData")
    @PublicProperties(updateSet = true)
    public ResultMsg updateData(@RequestBody A06TbEquipmentMaterialPurchase materialPurchase){
        try {
            return new ResultMsg( a06TbEquipmentMaterialPurchaseService.updateData(materialPurchase));
        } catch (RuntimeException e) {
            log.error(e.getMessage()+"请求对象：{}",materialPurchase);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(),ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }

    @ApiOperation(value = "物料信息采购表数据列表[分页]")
    @Authorization("yangon")
    @PostMapping("/findDataList")
    public ResultMsg findDataList(@RequestBody PageObject<A06TbEquipmentMaterialPurchase> pageObject){
        try {
            return new ResultMsg( a06TbEquipmentMaterialPurchaseService.findDataList(pageObject));
        } catch (RuntimeException e) {
            log.error(e.getMessage()+"请求对象：{}",pageObject);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(),ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }

}
