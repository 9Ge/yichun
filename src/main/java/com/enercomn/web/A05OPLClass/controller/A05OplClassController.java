package com.enercomn.web.A05OPLClass.controller;

import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.utils.ResultMsg;
import com.enercomn.web.A05OPLClass.bean.A05TbOplClass;
import com.enercomn.web.A05OPLClass.service.A05OplClassService;
import com.enercomn.web.A05OPLClass.service.A05TbOplTrainRecordsService;
import com.enercomn.web.baseManager.controller.BaseController;
import com.enercomn.web.baseManager.model.PageObject;
import com.enercomn.web.handle.PublicProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author yangcheng
 */
@Slf4j
@RestController
@RequestMapping("/opl")
@Api(value = "A05OPL单点课控制器", tags = "a-05-opl-controller-rest")
public class A05OplClassController extends BaseController {

    public final A05OplClassService a05OplClassService;
    public final A05TbOplTrainRecordsService a05TbOplTrainRecordsService;

    public A05OplClassController(A05OplClassService a05OplClassService, A05TbOplTrainRecordsService a05TbOplTrainRecordsService) {
        this.a05OplClassService = a05OplClassService;
        this.a05TbOplTrainRecordsService = a05TbOplTrainRecordsService;
    }

    @PostMapping("/addData")
    @ApiOperation(value = "新增OPL单点课及人员学习记录", response = ResultMsg.class)
    @PublicProperties(saveSet = true)
    public ResultMsg addData(@RequestBody A05TbOplClass a05TbOplClass) {
        try {
            return new ResultMsg(a05OplClassService.insert(a05TbOplClass));
        } catch (RuntimeException e) {
            log.error(e.getMessage()+"请求对象：{}",a05TbOplClass);
            return new ResultMsg(ResultStatusCode.ADD_DATA_FAIL.getResultCode(), ResultStatusCode.ADD_DATA_FAIL.getResultMessage());
        }
    }

    @ApiOperation(value = "修改OPL单点课及人员学习记录", response = ResultMsg.class)
    @PostMapping("/updateData")
    @PublicProperties(updateSet = true)
    public ResultMsg updateData(@RequestBody A05TbOplClass a05TbOplClass) {
        try {
            return new ResultMsg(a05OplClassService.updateAndRemove(a05TbOplClass));
        } catch (RuntimeException e) {
            log.error(e.getMessage()+"请求对象：{}",a05TbOplClass);
            return new ResultMsg(ResultStatusCode.UPDATE_DATA_FAIL.getResultCode(), ResultStatusCode.UPDATE_DATA_FAIL.getResultMessage());
        }
    }


    @ApiOperation(value = "查询OPL单点课及人员学习记录", response = ResultMsg.class)
    @PostMapping("/findDataList")
    public ResultMsg findDataList(@RequestBody PageObject<A05TbOplClass> pageObject) {
        try {
            return new ResultMsg(a05OplClassService.query(pageObject.getData(),pageObject));
        } catch (RuntimeException e) {
            log.error(e.getMessage()+"请求对象：{}",pageObject);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(), ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }

    @ApiOperation(value = "查询OPL单点课明细，包含学习记录", response = ResultMsg.class)
    @GetMapping("/findDataDetail")
    public ResultMsg findDataDetail(@RequestParam String tocId) {
        return new ResultMsg(a05OplClassService.detail(tocId));
    }

    @ApiOperation(value = "删除OPL单点课，同时删除学习记录", response = ResultMsg.class)
    @PostMapping("/delData")
    public ResultMsg delData(@RequestBody A05TbOplClass a05TbOplClass) {
        try {
            return new ResultMsg(a05OplClassService.delete(a05TbOplClass));
        } catch (RuntimeException e) {
            log.error(e.getMessage()+"请求对象：{}",a05TbOplClass);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(), ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }
}
