package com.enercomn.web.A01EquipmentManagement.controller;

import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.jwt.TokenResolver;
import com.enercomn.utils.DateTimeUtils;
import com.enercomn.utils.ResultMsg;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A01EquipmentManagement.bean.A01EquipmentAssetsBean;
import com.enercomn.web.A01EquipmentManagement.bean.A01FindEquipmentAssetsBean;
import com.enercomn.web.A01EquipmentManagement.service.A01EquipmentAsstsService;
import com.enercomn.web.A99TableExcelCommon.anno.DefaultPrimaryKey;
import com.enercomn.web.baseManager.controller.BaseController;
import com.enercomn.web.handle.PublicProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 设备资产台账管理ontroller层
 * 创建时间2021-07-13
 *
 */
@Slf4j
@Api(value = "a01设备资产管理", description = "a01设备资产管理")
@RestController
@RequestMapping("/A01EquipmentAsstsManagement")
public class A01EquipmentAsstsController extends BaseController {

    @Resource
    private A01EquipmentAsstsService managementService;

    @ApiOperation(value = "查询设备资产台账管理", notes = "查询设备资产台账管理", response = ResultMsg.class)
    @Authorization("CJ")
    @PostMapping("/findDataList")
    public  Object findDataList(@RequestBody A01FindEquipmentAssetsBean bean){
        try {
            return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), managementService.findDataList(bean.getEquipmentAssetsBean(),bean.getCurrPage(),bean.getPageSize()));
        }catch (Exception e){
            log.error("查询设备资产台账管理信息出错：" , e);
            return new ResultMsg(ResultStatusCode.DATA_FIND_NULL.getResultCode(), ResultStatusCode.DATA_FIND_NULL.getResultMessage());
        }

    }

    @ApiOperation(value = "新增设备资产", notes = "新增设备资产", response = ResultMsg.class)
    @Authorization("CJ")
    @PostMapping("/addData")
    @PublicProperties(saveSet = true)
    public  Object addData(@RequestBody @Validated  A01EquipmentAssetsBean bean){
        try {
            return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), managementService.addData(bean));
        }catch (Exception e){
            log.error("新增设备资产：" , e);
            return new ResultMsg(ResultStatusCode.ADD_DATA_FAIL.getResultCode(), ResultStatusCode.ADD_DATA_FAIL.getResultMessage());
        }

    }

    @ApiOperation(value = "更新设备资产信息", notes = "更新设备资产信息", response = ResultMsg.class)
    @Authorization("CJ")
    @PostMapping("/updateData")
    @PublicProperties(updateSet = true)
    public  Object updateData(@RequestBody @Validated  A01EquipmentAssetsBean bean){
        try {
            return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), managementService.updateData(bean));
        }catch (Exception e){
            log.error("修改设备资产台账管理信息出错：" , e);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }

    }

    @ApiOperation(value = "删除设备资产信息", notes = "删除设备资产信息", response = ResultMsg.class)
    @Authorization("CJ")
    @PostMapping("/deleteData")
    public  Object deleteData(@RequestBody A01EquipmentAssetsBean bean){
        try {
            if(StringUtils.isEmpty(bean.getTeaId())){
                return new ResultMsg(ResultStatusCode.NEED_INFO.getResultCode(), ResultStatusCode.NEED_INFO.getResultMessage(),"主键id未传！");
            }
            return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), managementService.deleteData(bean));
        }catch (Exception e){
            log.error("删除设备资产台账管理信息出错：" , e);
            return new ResultMsg(ResultStatusCode.DELETE_DATA_FAIL.getResultCode(), ResultStatusCode.DELETE_DATA_FAIL.getResultMessage());
        }

    }


    @ApiOperation(value = "设备资产台账管理数据[下拉框]", notes = "查询设备资产台账管理", response = ResultMsg.class)
    @Authorization("yc")
    @PostMapping("/equipmentSelect")
    public  ResultMsg equipmentSelect(){

        try {
            return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), managementService.equipmentSelect());
        }catch (Exception e){
            log.error("查询设备资产台账管理信息出错：" , e);
            return new ResultMsg(ResultStatusCode.DATA_FIND_NULL.getResultCode(), ResultStatusCode.DATA_FIND_NULL.getResultMessage());
        }

    }

}
