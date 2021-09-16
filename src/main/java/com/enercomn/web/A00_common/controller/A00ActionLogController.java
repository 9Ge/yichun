package com.enercomn.web.A00_common.controller;

import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.jwt.TokenResolver;
import com.enercomn.utils.*;
import com.enercomn.web.A00_common.service.A00ActionLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

//import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 * 操作日志管理
 *
 * @author wbk
 */
@Api(value = "操作日志管理", description = "操作日志管理")
@RestController
@RequestMapping("/api/A00ActionLogController")
public class A00ActionLogController {

    @Resource
    A00ActionLogService a00ActionLogService;

    @Resource
    TokenResolver tokenResolver;

    /**
     * 获取操作日志
     *
     * @param cOperateModule    操作模块
     * @param cOperateDateBegin 操作时间-起始
     * @param cOperateDateEnd   操作时间-截止
     * @param appToken          用户appToken
     * @param currPage          当前页
     * @param pageSize          每页记录数
     * @param orderColumn       排序列
     * @param orderType         排序类型
     * @return 操作结果
     */
    //@Ignore
    @ApiOperation(value = "获取操作日志", notes = "获取操作日志", response = ResultMsg.class)
    @GetMapping("/getActionLogInfo")
    public Object getActionLogInfo(@ApiParam(value = "操作模块", required = false) @RequestParam(value = "cOperateModule", required = false) String cOperateModule,
                                   @ApiParam(value = "姓名", required = false) @RequestParam(value = "cName", required = false) String cName,
                                   @ApiParam(value = "操作时间-起始", required = false) @RequestParam(value = "cOperateDateBegin", required = false) String cOperateDateBegin,
                                   @ApiParam(value = "操作时间-截止", required = false) @RequestParam(value = "cOperateDateEnd", required = false) String cOperateDateEnd,
                                   @ApiParam(value = "当前页", required = true) @RequestParam(required = true) int currPage,
                                   @ApiParam(value = "每页记录数", required = true) @RequestParam(required = true) int pageSize,
                                   @ApiParam(value = "排序列", required = false) @RequestParam(value = "orderColumn", required = false, defaultValue = "operateDate") String orderColumn,
                                   @ApiParam(value = "排序类型", required = false) @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                   @ApiParam(value = "用户appToken ") @RequestHeader(required = true) String appToken) {
        RowMsg rowMsg;
        String areaId = tokenResolver.getAreaId(appToken);
        PageBean pageBean = a00ActionLogService.getActionLogInfo(cOperateModule, cName, cOperateDateBegin, cOperateDateEnd, currPage, pageSize, orderColumn, orderType,areaId);
        rowMsg = new RowMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), pageBean);
        return rowMsg;
    }

    /**
     * 保存操作日志
     *
     * @param operateModule   操作模块
     * @param operateType     操作类型
     * @param areaId          所属区域
     * @param operateUser     操作人
     * @param operateDescribe 操作描述
     * @return 操作结果
     */
    //@Ignore
    @ApiOperation(value = "保存操作日志", notes = "保存操作日志", response = ResultMsg.class)
    @PostMapping("/saveActionLogInfo")
    public Object saveActionLogInfo(@ApiParam(value = "操作模块", required = true) @RequestParam(value = "operateModule", required = true) String operateModule,
                                    @ApiParam(value = "操作类型", required = true) @RequestParam(value = "operateType", required = true) String operateType,
                                    @ApiParam(value = "操作描述", required = true) @RequestParam(value = "operateDescribe", required = true) String operateDescribe,
                                    @ApiParam(value = "操作人ID", required = true) @RequestParam(value = "operateUser", required = true) String operateUser,
                                    @ApiParam(value = "所属区域", required = true) @RequestParam(value = "areaId", required = true) String areaId,
                                    HttpServletRequest request) {
        ResultMsg resultMsg;
        String operateIP = CusAccessObjectUtil.getIpAddress(request);
        boolean result = a00ActionLogService.saveActionLogInfo(operateModule, operateType, operateUser, areaId, operateDescribe, operateIP);
        resultMsg = new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), result);
        return resultMsg;
    }

    /**
     * 获取操作模块select下拉框数据
     *
     * @return 操作结果
     */
    //@Ignore
    @ApiOperation(value = "获取操作模块select下拉框数据", notes = "获取操作模块select下拉框数据", response = ResultMsg.class)
    @GetMapping("/getModules")
    public Object getModules() {
        ResultMsg resultMsg;
        List<String> result = a00ActionLogService.getModules();
        resultMsg = new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), result);
        return resultMsg;
    }


    /**
     * 查询姓名select下拉框数据
     *
     * @return Object
     */
    //@Ignore
    @ApiOperation(value = "查询姓名select下拉框数据", notes = "查询姓名select下拉框数据", response = ResultMsg.class)
    @GetMapping("/getAllNames")
    public Object getAllNames(@ApiParam(value = "用户appToken ") @RequestHeader(required = true) String appToken) {
        ResultMsg resultMsg;
        List<String> result = a00ActionLogService.getAllNames(tokenResolver.getAreaId(appToken));
        resultMsg = new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), result);
        return resultMsg;
    }
}
