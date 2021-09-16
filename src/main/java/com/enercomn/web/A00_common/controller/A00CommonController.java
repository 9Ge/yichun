package com.enercomn.web.A00_common.controller;


import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.utils.ResultMsg;

import com.enercomn.web.A00_common.bean.A00DictionariesSelectBean;
import com.enercomn.web.A00_common.bean.A00DictionariesSelectParamBean;
import com.enercomn.web.A00_common.service.A00CommonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 通用
 * author sjc
 * data 2020/06/08
 */
@Api(value = "通用", description = "通用")
@RestController
@RequestMapping("/A00CommonController")
public class A00CommonController {


    @Value("${file.uploadUrl}")
    private String uploadUrl;

    @Autowired
    private A00CommonService a00CommonService;

    @ApiOperation(value = "查询字典表信息", notes = "查询字典表信息", response = ResultMsg.class)
    @Authorization("Sjc")
    @PostMapping("/findDictionariesInfo")
    public Object findDictionariesInfo(@RequestBody A00DictionariesSelectParamBean dictionariesSelectParamBean ){
        if(dictionariesSelectParamBean != null){

            List<A00DictionariesSelectBean > selectParamBeanList = a00CommonService.findDictionariesInfo(dictionariesSelectParamBean);
            if(selectParamBeanList != null){
                //返回接口成功状态，并返回参数集
                return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(),selectParamBeanList);
            }else{
                //HostBean，返回访问失败
                return new ResultMsg(ResultStatusCode.DATA_FIND_NULL.getResultCode(), ResultStatusCode.DATA_FIND_NULL.getResultMessage(), "未找到数据！");
            }
        }else{
            return new ResultMsg(ResultStatusCode.NEED_INFO.getResultCode(), ResultStatusCode.NEED_INFO.getResultMessage(),"参数不完整！");
        }
    }


    /**
     * 功能说明：文件上传
     *
     * @param file    myfile
     * @param request request
     * @return Object
     */
    @ApiOperation(value = "文件上传", notes = "文件上传", response = HashMap.class)
    @RequestMapping(value = "/filesUpload",
            method = RequestMethod.POST)
    @ResponseBody
    public Object filesUpload(@ApiParam(value = "file", required = true) @RequestParam(value = "file", required = true) MultipartFile file,
                              HttpServletRequest request) {

        ResultMsg resultMsg;

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String path = uploadUrl + sdf.format(d);
        String fileName = file.getOriginalFilename();
        if (file.isEmpty()) {
            resultMsg = new ResultMsg(-1, "文件上传失败", "文件为空");
        } else {
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
//            String newFileName = a00CommonService.createFileName(fileType);
            String webPath = path + "/" + fileName;
            File dest = new File(webPath);
            //判断文件父目录是否存在
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdir();
            }
            try {
                file.transferTo(dest);
                resultMsg = new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), webPath.replace(uploadUrl, ""));
            } catch (Exception e) {
                e.printStackTrace();
                resultMsg = new ResultMsg(-1, "文件上传失败", e.toString());
                return resultMsg;
            }
        }
        return resultMsg;
    }

}
