package com.enercomn.web.A03EquipmentSuppler.controller;

import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.jwt.TokenResolver;
import com.enercomn.utils.ResultMsg;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A03EquipmentSuppler.bean.A03EquipmentSupplerBean;
import com.enercomn.web.A03EquipmentSuppler.bean.A03FindEquipmentSupplerBean;
import com.enercomn.web.A03EquipmentSuppler.service.A03EquipmentSupplerService;
import com.enercomn.web.baseManager.controller.BaseController;
import com.enercomn.web.handle.PublicProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 设备资产台账管理ontroller层
 * 创建时间2021-07-13
 *
 */
@Slf4j
@Api(value = "a03设备供应商管理", description = "设备管理")
@RestController
@RequestMapping("/A03EquipmentSupplerController")
public class A03EquipmentSupplerController extends BaseController {

    //获取token值
    @Resource
    TokenResolver tokenResolver;

    @Resource
    private A03EquipmentSupplerService managementService;

    @ApiOperation(value = "查询设备供应商", notes = "查询设备供应商", response = ResultMsg.class)
    @Authorization("CJ")
    @PostMapping("/findDataList")
    public  Object findDataList(@RequestBody A03FindEquipmentSupplerBean bean){
        try {
            return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), managementService.findDataList(bean.getEquipmentSupplerBean(),bean.getCurrPage(),bean.getPageSize()));
        }catch (Exception e){
            log.error("查询设备供应商出错：" , e);
            return new ResultMsg(ResultStatusCode.DATA_FIND_NULL.getResultCode(), ResultStatusCode.DATA_FIND_NULL.getResultMessage());
        }

    }

    @ApiOperation(value = "新增设备供应商", notes = "新增设备供应商", response = ResultMsg.class)
    @Authorization("CJ")
    @PostMapping("/addData")
    @PublicProperties(saveSet = true)
    public  Object addData(@RequestBody A03EquipmentSupplerBean bean){
        try {
            return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), managementService.addData(bean));
        }catch (Exception e){
            log.error("新增设备供应商出差：" , e);
            return new ResultMsg(ResultStatusCode.ADD_DATA_FAIL.getResultCode(), ResultStatusCode.ADD_DATA_FAIL.getResultMessage());
        }

    }

    @ApiOperation(value = "根据ID查询信息", notes = "根据ID查询信息", response = ResultMsg.class)
    @Authorization("CJ")
    @PostMapping("/findDataDetail")
    public  Object findDataDetail(@RequestBody A03EquipmentSupplerBean bean){
        try {
            return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), managementService.findDataDetail(bean.getTesId()));
        }catch (Exception e){
            log.error("根据ID查询：" , e);
            return new ResultMsg(ResultStatusCode.DATA_FIND_NULL.getResultCode(), ResultStatusCode.DATA_FIND_NULL.getResultMessage());
        }

    }
    @ApiOperation(value = "更新设备供应商信息", notes = "更新设备供应商信息", response = ResultMsg.class)
    @Authorization("CJ")
    @PostMapping("/updateData")
    @PublicProperties(updateSet = true)
    public  Object updateData(@RequestBody A03EquipmentSupplerBean bean){
        try {
            return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), managementService.updateData(bean));
        }catch (Exception e){
            log.error("查询设备供应商出错：" , e);
            return new ResultMsg(ResultStatusCode.DELETE_DATA_FAIL.getResultCode(), ResultStatusCode.DELETE_DATA_FAIL.getResultMessage());
        }

    }

    @ApiOperation(value = "删除设备资产信息", notes = "删除设备资产信息", response = ResultMsg.class)
    @Authorization("CJ")
    @PostMapping("/deleteData")
    public  Object deleteData(@RequestBody A03EquipmentSupplerBean bean){
        try {
            if(StringUtils.isEmpty(bean.getTesId())){
                return new ResultMsg(ResultStatusCode.NEED_INFO.getResultCode(), ResultStatusCode.NEED_INFO.getResultMessage(),"主键id未传！");
            }
            return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), managementService.deleteData(bean));
        }catch (Exception e){
            log.error("删除设备供应商信息出错：" , e);
            return new ResultMsg(ResultStatusCode.DELETE_DATA_FAIL.getResultCode(), ResultStatusCode.DELETE_DATA_FAIL.getResultMessage());
        }
    }

}
