package com.enercomn.web.A09SpotCheck.controller;

import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.utils.ResultMsg;
import com.enercomn.web.A09SpotCheck.bean.A09TbSpotCheckDetail;
import com.enercomn.web.A09SpotCheck.service.A09TbSpotCheckDetailService;
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
 * @Date: 2021/8/13 17:33<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Api(value = "点检记录异常控制器", tags = "a-09-check-detail-controller-rest")
@RestController
@RequestMapping("check/exception")
public class A09TbSpotCheckDetailController {


    private final A09TbSpotCheckDetailService a09TbSpotCheckDetailService;

    public A09TbSpotCheckDetailController(A09TbSpotCheckDetailService a09TbSpotCheckDetailService) {
        this.a09TbSpotCheckDetailService = a09TbSpotCheckDetailService;
    }


    @PostMapping("addData")
    @ApiOperation(value = "新增设备点检异常数据")
    @PublicProperties(saveSet = true)
    public ResultMsg addData(@RequestBody A09TbSpotCheckDetail checkDetail) {
        try {
            return new ResultMsg(a09TbSpotCheckDetailService.addData(checkDetail));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),checkDetail);
            return new ResultMsg(ResultStatusCode.ADD_DATA_FAIL.getResultCode(), ResultStatusCode.ADD_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("delData")
    @ApiOperation(value = "删除设备点检异常数据")
    public ResultMsg delData(@RequestBody A09TbSpotCheckDetail checkDetail) {
        try {
            return new ResultMsg(a09TbSpotCheckDetailService.deleteData(checkDetail));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),checkDetail);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }

    @PostMapping("updateData")
    @ApiOperation(value = "更新设备点检异常记录数据")
    @PublicProperties(updateSet = true)
    public ResultMsg updateData(@RequestBody A09TbSpotCheckDetail checkDetail) {
        try {
            return new ResultMsg(a09TbSpotCheckDetailService.updateData(checkDetail));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),checkDetail);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }


    @PostMapping("findDataByTscId")
    @ApiOperation(value = "根据TscId查询设备点检异常表")
    public ResultMsg findDataList(@RequestBody A09TbSpotCheckDetail detail) {
        try {
            return new ResultMsg(a09TbSpotCheckDetailService.findDataList(detail.getTscId()));
        } catch (RuntimeException e) {
            log.info("{}:异常"+e.getMessage(),detail);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(), ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }

}
