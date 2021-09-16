package com.enercomn.web.A02EquipmentInfomation.controller;

import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.jwt.TokenResolver;
import com.enercomn.utils.ResultMsg;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A02EquipmentInfomation.bean.A02EquipmentInfomationBean;
import com.enercomn.web.A02EquipmentInfomation.bean.A02FindEquipmentInfomationBean;
import com.enercomn.web.A02EquipmentInfomation.service.A02EquipmentInfomationService;
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
@Api(value = "a02设备人员管理", description = "设备管理")
@RestController
@RequestMapping("/A02EquipmentInfomationController")
public class A02EquipmentInfomationController extends BaseController {

    @Resource
    private A02EquipmentInfomationService managementService;

    @ApiOperation(value = "查询设备管理人员", notes = "查询设备管理人员", response = ResultMsg.class)
    @Authorization("CJ")
    @PostMapping("/findDataList")
    public  Object findDataList(@RequestBody A02FindEquipmentInfomationBean bean){
        try {
            return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), managementService.findDataList(bean.getEquipmentInfomationBean(),bean.getCurrPage(),bean.getPageSize()));
        }catch (Exception e){
            log.error("查询设备管理人员出错：" , e);
            return new ResultMsg(ResultStatusCode.DATA_FIND_NULL.getResultCode(), ResultStatusCode.DATA_FIND_NULL.getResultMessage());
        }

    }

    @ApiOperation(value = "查询设备管理人员[下拉框]", notes = "查询设备管理人员下拉框", response = ResultMsg.class)
    @Authorization("YANGON")
    @PostMapping("/queryDataList")
    public  ResultMsg findDataList(){
        try {
            return new ResultMsg(managementService.query());
        }catch (Exception e){
            log.error("查询设备管理人员下拉框出错：" , e);
            return new ResultMsg(ResultStatusCode.DATA_FIND_NULL.getResultCode(), ResultStatusCode.DATA_FIND_NULL.getResultMessage());
        }

    }

    @ApiOperation(value = "新增设备管理人员", notes = "新增设备管理人员", response = ResultMsg.class)
    @Authorization("CJ")
    @PostMapping("/addData")
    @PublicProperties(saveSet = true)
    public  Object addData(@RequestBody A02EquipmentInfomationBean bean){
        try {
            return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), managementService.addData(bean));
        }catch (Exception e){
            log.error("新增设备管理人员出差：" , e);
            return new ResultMsg(ResultStatusCode.ADD_DATA_FAIL.getResultCode(), ResultStatusCode.ADD_DATA_FAIL.getResultMessage());
        }

    }

    @ApiOperation(value = "根据ID查询信息", notes = "根据ID查询信息", response = ResultMsg.class)
    @Authorization("CJ")
    @PostMapping("/findDataDetail")
    public  Object findDataDetail(@RequestBody A02EquipmentInfomationBean bean){
        try {
            return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), managementService.findDataDetail(bean.getTeiId()));
        }catch (Exception e){
            log.error("根据ID查询：" , e);
            return new ResultMsg(ResultStatusCode.DATA_FIND_NULL.getResultCode(), ResultStatusCode.DATA_FIND_NULL.getResultMessage());
        }

    }
    @ApiOperation(value = "更新设备管理人员信息", notes = "更新设备管理人员信息", response = ResultMsg.class)
    @Authorization("CJ")
    @PostMapping("/updateData")
    @PublicProperties(updateSet = true)
    public  Object updateData(@RequestBody A02EquipmentInfomationBean bean){
        try {
            return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), managementService.updateData(bean));
        }catch (Exception e){
            log.error("修改设备管理人员信息出错：" , e);
            return new ResultMsg(ResultStatusCode.DELETE_DATA_FAIL.getResultCode(), ResultStatusCode.DELETE_DATA_FAIL.getResultMessage());
        }

    }

    @ApiOperation(value = "删除设备管理人员信息", notes = "删除设备管理人员信息", response = ResultMsg.class)
    @Authorization("CJ")
    @PostMapping("/deleteData")
    public  Object deleteData(@RequestBody A02EquipmentInfomationBean bean){
        try {
            if(StringUtils.isEmpty(bean.getTeiId())){
                return new ResultMsg(ResultStatusCode.NEED_INFO.getResultCode(), ResultStatusCode.NEED_INFO.getResultMessage(),"主键id未传！");
            }
            return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), managementService.deleteData(bean));
        }catch (Exception e){
            log.error("删除设备管理人员信息出错：" , e);
            return new ResultMsg(ResultStatusCode.DELETE_DATA_FAIL.getResultCode(), ResultStatusCode.DELETE_DATA_FAIL.getResultMessage());
        }

    }
}
