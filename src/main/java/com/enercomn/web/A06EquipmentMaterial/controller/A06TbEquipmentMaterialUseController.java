package com.enercomn.web.A06EquipmentMaterial.controller;

import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.utils.ResultMsg;
import com.enercomn.web.A06EquipmentMaterial.bean.A06TbEquipmentMaterialUse;
import com.enercomn.web.A06EquipmentMaterial.service.A06TbEquipmentMaterialUseService;
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
@Api(value = "物料信息领用表操作控制器",tags = "a-06-material-use-controller-rest")
@RestController
@RequestMapping("use")
public class A06TbEquipmentMaterialUseController {

    private final A06TbEquipmentMaterialUseService a06TbEquipmentMaterialUseService;

    public A06TbEquipmentMaterialUseController(A06TbEquipmentMaterialUseService a06TbEquipmentMaterialUseService) {
        this.a06TbEquipmentMaterialUseService = a06TbEquipmentMaterialUseService;
    }


    @ApiOperation(value = "新增物料信息领用表数据")
    @Authorization("yangon")
    @PostMapping("/addData")
    @PublicProperties(saveSet = true)
    public ResultMsg addData(@RequestBody A06TbEquipmentMaterialUse materialUse ){
        try {
            return new ResultMsg( a06TbEquipmentMaterialUseService.addData(materialUse));
        } catch (RuntimeException e) {
            log.error(e.getMessage()+"请求对象：{}",materialUse);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(),ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }
    @ApiOperation(value = "删除物料信息领用表数据")
    @Authorization("yangon")
    @PostMapping("/delData")
    public ResultMsg delData(@RequestBody A06TbEquipmentMaterialUse materialUse ){
        try {
            return new ResultMsg( a06TbEquipmentMaterialUseService.deleteData(materialUse));
        } catch (RuntimeException e) {
            log.error(e.getMessage()+"请求对象：{}",materialUse);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(),ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }

    @ApiOperation(value = "修改物料信息领用表数据")
    @Authorization("yangon")
    @PostMapping("/updateData")
    @PublicProperties(updateSet = true)
    public ResultMsg updateData(@RequestBody A06TbEquipmentMaterialUse materialUse ){
        try {
            return new ResultMsg( a06TbEquipmentMaterialUseService.updateData(materialUse));
        } catch (RuntimeException e) {
            log.error(e.getMessage()+"请求对象：{}",materialUse);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(),ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }

    @ApiOperation(value = "物料信息领用表数据列表[分页]")
    @Authorization("yangon")
    @PostMapping("/findDataList")
    public ResultMsg findDataList(@RequestBody PageObject<A06TbEquipmentMaterialUse> pageObject){
        try {
            return new ResultMsg( a06TbEquipmentMaterialUseService.findDataList(pageObject));
        } catch (RuntimeException e) {
            log.error(e.getMessage()+"请求对象：{}",pageObject);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(),ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }

}
