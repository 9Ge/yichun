package com.enercomn.web.A00_dict.controller;

import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.utils.ResultMsg;
import com.enercomn.web.A00_dict.bean.A00TbDict;
import com.enercomn.web.A00_dict.service.A00TbDictService;
import com.enercomn.web.baseManager.controller.BaseController;
import com.enercomn.web.handle.PublicProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Date: 2021/8/10 15:44<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Api(value = "A00数据字典Rest接口")
@RestController
@RequestMapping("/a00dict")
public class A00TbDictController extends BaseController {

    private final A00TbDictService a00TbDictService;

    public A00TbDictController(A00TbDictService a00TbDictService) {
        this.a00TbDictService = a00TbDictService;
    }

    @ApiOperation(value = "数据字典列表")
    @Authorization("yangon")
    @PostMapping("/findDataList")
    public ResultMsg findDataList(@RequestBody @ApiParam(name = "code") Map map){
        try {
            if(StringUtils.isEmpty(String.valueOf(map.get("code")))){
                return new ResultMsg( ResultStatusCode.NEED_INFO.getResultCode(),ResultStatusCode.NEED_INFO.getResultMessage());
            }
            return new ResultMsg(a00TbDictService.findDataList(String.valueOf(map.get("code"))));
        } catch (Exception e) {
            log.error(e.getMessage()+"请求对象：{}",map);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(),ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }

    @ApiOperation(value = "新增数据字典")
    @Authorization("yangon")
    @PostMapping("/addData")
    @PublicProperties(saveSet = true)
    public ResultMsg addData(@RequestBody List<A00TbDict> dicts){
        try {
            return new ResultMsg(a00TbDictService.addData(dicts));
        } catch (Exception e) {
            log.error(e.getMessage()+"请求对象：{}",dicts);
            return new ResultMsg(ResultStatusCode.REQUEST_ERROR.getResultCode(),ResultStatusCode.REQUEST_ERROR.getResultMessage());
        }
    }
}
