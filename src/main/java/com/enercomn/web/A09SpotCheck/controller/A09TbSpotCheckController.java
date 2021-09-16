package com.enercomn.web.A09SpotCheck.controller;

import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.utils.ResultMsg;
import com.enercomn.web.A09SpotCheck.bean.A09TbSpotCheck;
import com.enercomn.web.A09SpotCheck.service.A09TbSpotCheckService;
import com.enercomn.web.baseManager.model.PageObject;
import com.enercomn.web.handle.PublicProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Date: 2021/8/13 14:33<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Api(value = "点检记录控制器", tags = "a-09-check-controller-rest")
@RestController
@RequestMapping("spot/check")
public class A09TbSpotCheckController {


    private final A09TbSpotCheckService a09TbSpotCheckService;

    public A09TbSpotCheckController(A09TbSpotCheckService a09TbSpotCheckService) {
        this.a09TbSpotCheckService = a09TbSpotCheckService;
    }


    @PostMapping("addData")
    @ApiOperation(value = "新增设备点检表")
    @PublicProperties(saveSet = true)
    public ResultMsg addData(@RequestBody A09TbSpotCheck check) {
        try {
            return new ResultMsg(a09TbSpotCheckService.addData(check));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),check);
            return new ResultMsg(ResultStatusCode.ADD_DATA_FAIL.getResultCode(), ResultStatusCode.ADD_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("delData")
    @ApiOperation(value = "删除设备点检表")
    public ResultMsg delData(@RequestBody A09TbSpotCheck check) {
        try {
            return new ResultMsg(a09TbSpotCheckService.deleteData(check));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),check);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }

    @PostMapping("updateData")
    @ApiOperation(value = "更新设备点检表")
    @PublicProperties(updateSet = true)
    public ResultMsg updateData(@RequestBody A09TbSpotCheck check) {
        try {
            return new ResultMsg(a09TbSpotCheckService.updateData(check));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),check);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }

    @PostMapping("updateData/{status}")
    @ApiOperation(value = "更新设备点检表")
    public ResultMsg updateData(@RequestBody A09TbSpotCheck check, @PathVariable("status") Integer status) {
        try {
            if(check.getCheckStatus().equals("1")){
                return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(),"当前数据已点检完成！禁止继续修改！");
            }
            if(status.intValue() == Integer.valueOf(check.getCheckStatus()).intValue()){
                return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(),"当前数据已进行过该操作！无需重复操作！");
            }
            if(check.getCheckStatus().equals("3") && status.intValue() == 2){
                return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(),"当前数据再次复核！无需修改成已复核！");
            }

            check.setCheckStatus(String.valueOf(status));
            return new ResultMsg(a09TbSpotCheckService.updateDataStatus(check));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),check);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("findDataList")
    @ApiOperation(value = "查询设备点检表[分页]")
    public ResultMsg findDataList(@RequestBody PageObject<A09TbSpotCheck> pageObject) {
        try {
            return new ResultMsg(a09TbSpotCheckService.findDataList(pageObject));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),pageObject);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(), ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }

    @PostMapping("groupDataByStatus")
    @ApiOperation(value = "查询设备点检表[状态分组]")
    public ResultMsg findDataList(@RequestBody A09TbSpotCheck tbSpotCheck) {
        try {
            return new ResultMsg(a09TbSpotCheckService.groupDataByStatus(tbSpotCheck));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),tbSpotCheck);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(), ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }

}
