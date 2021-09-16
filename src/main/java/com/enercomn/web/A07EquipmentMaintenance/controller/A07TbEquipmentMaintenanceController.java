package com.enercomn.web.A07EquipmentMaintenance.controller;

import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.utils.ResultMsg;
import com.enercomn.web.A07EquipmentMaintenance.bean.A07TbEquipmentMaintenance;
import com.enercomn.web.A07EquipmentMaintenance.service.A07TbEquipmentMaintenanceService;
import com.enercomn.web.baseManager.controller.BaseController;
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
 * @Date: 2021/8/12 11:25<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Api(value = "设备预防性维护标准数据控制器" ,tags = "a-07-maintenance-controller-rest")
@RestController
@RequestMapping("maintenance")
public class A07TbEquipmentMaintenanceController extends BaseController {
    private final A07TbEquipmentMaintenanceService a07TbEquipmentMaintenanceService;

    public A07TbEquipmentMaintenanceController(A07TbEquipmentMaintenanceService a07TbEquipmentMaintenanceService) {
        this.a07TbEquipmentMaintenanceService = a07TbEquipmentMaintenanceService;
    }

    @PostMapping("addData")
    @ApiOperation(value = "新增设备预防性维护标准数据")
    @PublicProperties(saveSet = true)
    public ResultMsg addData(@RequestBody A07TbEquipmentMaintenance maintenance){
        try {
            return new ResultMsg(a07TbEquipmentMaintenanceService.addData(maintenance));
        } catch (RuntimeException e) {
            log.error(e.getMessage()+"请求对象：{}",maintenance);
            return new ResultMsg(ResultStatusCode.ADD_DATA_FAIL.getResultCode(),ResultStatusCode.ADD_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("delData")
    @ApiOperation(value = "删除设备预防性维护标准数据")
    public ResultMsg delData(@RequestBody A07TbEquipmentMaintenance maintenance){
        try {
            return new ResultMsg(a07TbEquipmentMaintenanceService.delData(maintenance));
        } catch (RuntimeException e) {
            log.error(e.getMessage()+"请求对象：{}",maintenance);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(),ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }

    @PostMapping("updateData")
    @ApiOperation(value = "更新设备预防性维护标准数据")
    @PublicProperties(updateSet = true)
    public ResultMsg updateData(@RequestBody A07TbEquipmentMaintenance maintenance){
        try {
            return new ResultMsg(a07TbEquipmentMaintenanceService.updateData(maintenance));
        } catch (RuntimeException e) {
            log.error(e.getMessage()+"请求对象：{}",maintenance);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(),ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("findDataList")
    @ApiOperation(value = "查询设备预防性维护标准数据[分页]")
    public ResultMsg findDataList(@RequestBody PageObject<A07TbEquipmentMaintenance> pageObject){
        try {
            return new ResultMsg(a07TbEquipmentMaintenanceService.findDataList(pageObject));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),pageObject);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(),ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }
}
