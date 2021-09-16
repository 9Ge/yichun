package com.enercomn.web.A04ToolAccount.controller;

import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.jwt.TokenResolver;
import com.enercomn.utils.ResultMsg;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A04ToolAccount.bean.A04FindToolAccount;
import com.enercomn.web.A04ToolAccount.bean.A04ToolAccount;
import com.enercomn.web.A04ToolAccount.service.A04ToolAccountService;
import com.enercomn.web.baseManager.controller.BaseController;
import com.enercomn.web.handle.PublicProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 设备资产台账管理ontroller层
 * 创建时间2021-07-13
 *
 */
@Slf4j
@Api(value = "a02维修工具台账管理", description = "设备管理")
@RestController
@RequestMapping("/A04ToolAccountController")
public class A04ToolAccountController extends BaseController {

    @Resource
    private A04ToolAccountService managementService;

    @ApiOperation(value = "查询维修工具台账", notes = "查询维修工具台账", response = ResultMsg.class)
    @Authorization("CJ")
    @PostMapping("/findDataList")
    public  Object findDataList(@RequestBody A04FindToolAccount bean){
        try {
            return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), managementService.findDataList(bean.getToolAccount(),bean.getCurrPage(),bean.getPageSize()));
        }catch (Exception e){
            log.error("查询维修工具台账出错：" , e);
            return new ResultMsg(ResultStatusCode.DATA_FIND_NULL.getResultCode(), ResultStatusCode.DATA_FIND_NULL.getResultMessage());
        }

    }

    @ApiOperation(value = "新增维修工具台账", notes = "新增维修工具台账", response = ResultMsg.class)
    @Authorization("CJ")
    @PostMapping("/addData")
    @PublicProperties(saveSet = true)
    public  Object addData(@RequestBody  @Validated A04ToolAccount bean ){
        try {
            return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), managementService.addData(bean));
        }catch (Exception e){
            log.error("新增维修工具台账出差：" , e);
            return new ResultMsg(ResultStatusCode.ADD_DATA_FAIL.getResultCode(), ResultStatusCode.ADD_DATA_FAIL.getResultMessage());
        }

    }

    @ApiOperation(value = "根据ID查询信息", notes = "根据ID查询信息", response = ResultMsg.class)
    @Authorization("CJ")
    @PostMapping("/findDataDetail")
    public  Object findDataDetail(@RequestBody A04ToolAccount bean){
        try {
            return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), managementService.findDataDetail(bean.getTtaId()));
        }catch (Exception e){
            log.error("根据ID查询：" , e);
            return new ResultMsg(ResultStatusCode.DATA_FIND_NULL.getResultCode(), ResultStatusCode.DATA_FIND_NULL.getResultMessage());
        }

    }
    @ApiOperation(value = "更新维修工具台账信息", notes = "更新维修工具台账信息", response = ResultMsg.class)
    @Authorization("CJ")
    @PostMapping("/updateData")
    @PublicProperties(updateSet = true)
    public  Object updateData(@Valid  @RequestBody A04ToolAccount bean ){
        try {
            return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), managementService.updateData(bean));
        }catch (Exception e){
            log.error("更新维修工具台账信息出错：" , e);
            return new ResultMsg(ResultStatusCode.DELETE_DATA_FAIL.getResultCode(), ResultStatusCode.DELETE_DATA_FAIL.getResultMessage());
        }

    }


    @ApiOperation(value = "删除维修工具台账信息", notes = "删除维修工具台账信息", response = ResultMsg.class)
    @Authorization("CJ")
    @PostMapping("/deleteData")
    public  Object deleteData(@RequestBody A04ToolAccount bean ){
        try {
            if(StringUtils.isEmpty(bean.getTtaId())){
                return new ResultMsg(ResultStatusCode.NEED_INFO.getResultCode(), ResultStatusCode.NEED_INFO.getResultMessage(),"主键id未传！");
            }
            bean.setUpdateUser(getUser().getTeiId());
            return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), managementService.deleteData(bean));
        }catch (Exception e){
            log.error("删除维修工具台账管理信息出错：" , e);
            return new ResultMsg(ResultStatusCode.DELETE_DATA_FAIL.getResultCode(), ResultStatusCode.DELETE_DATA_FAIL.getResultMessage());
        }
    }

    @ApiOperation(value = "维修工具台账信息[下拉框]", notes = "维修工具台账信息[下拉框]", response = ResultMsg.class)
    @PostMapping("/dataSelector")
    public  Object dataSelector(@RequestBody A04ToolAccount bean ){
        try {
            return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), managementService.dataSelector());
        }catch (Exception e){
            log.error("维修工具台账信息[下拉框]：" , e);
            return new ResultMsg(ResultStatusCode.DELETE_DATA_FAIL.getResultCode(), ResultStatusCode.DELETE_DATA_FAIL.getResultMessage());
        }
    }
}
