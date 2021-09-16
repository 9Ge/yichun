package com.enercomn.web.A99TableExcelCommon.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.utils.ResultMsg;
import com.enercomn.web.A99TableExcelCommon.constant.ExcelTableEnum;
import com.enercomn.web.A99TableExcelCommon.handle.SaveExcelDataHandle;
import com.enercomn.web.A99TableExcelCommon.service.FileWithExcelUtil;
import com.enercomn.web.A99TableExcelCommon.vo.ExcelPropertiesVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * @Date: 2021/9/7 14:20<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/A99Excel")
@Api(value = "Excel导入导出",tags = "a-99-excel-controller-rest")
public class ExcelUploadController {

    @Autowired
    private SaveExcelDataHandle saveExcelDataHandle;

    @ApiOperation(value = "Excel导入数据", notes = "Excel导入数据", response = HashMap.class)
    @RequestMapping(value = "/import/{tableName}", method = RequestMethod.POST)
    public ResultMsg importExcel(@ApiParam(value = "file", required = true) @RequestParam(value = "file", required = true) MultipartFile file,
                                 @PathVariable(name = "tableName") String tableName) throws Exception {
        ExcelPropertiesVo excelPropertiesVo = ExcelTableEnum.getExcelPropertiesByEnName(tableName);
        if( excelPropertiesVo== null){
            return new ResultMsg(ResultStatusCode.REQUEST_FAIL.getResultCode(),"当前表枚举中不存在，请联系管理员添加");
        }
        Class beanClazz = excelPropertiesVo.getBeanClazz();
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        params.setNeedSave(true);
        List<Object> excelList = null;
        try {
            excelList = ExcelImportUtil.importExcel(file.getInputStream(), beanClazz, params);
        } catch (NumberFormatException e) {
            return new ResultMsg(ResultStatusCode.REQUEST_FAIL.getResultCode(),e.getMessage().replace("For input string: ", "") + "请求改成数字");
        } catch (Exception e) {
            log.info("excel转化失败，{}", e);
            return new ResultMsg(ResultStatusCode.REQUEST_FAIL.getResultCode(),"转换Excel失败,请联系管理员!");
        }
        log.info("Excel中的数据{}", excelList);
        Class mapperClazz = excelPropertiesVo.getMapperClazz();
        return new ResultMsg(ResultStatusCode.OK.getResultCode(),saveExcelDataHandle.saveExcelData(mapperClazz, excelList));
    }


    @ApiOperation(value = "Excel导出数据", notes = "Excel导出数据", response = HashMap.class)
    @RequestMapping(value = "/export/{tableName}", method = RequestMethod.GET)
    public void exportExcel(@PathVariable(name = "tableName") String tableName, HttpServletResponse response) throws Exception {
        ExcelPropertiesVo excelPropertiesVo = ExcelTableEnum.getExcelPropertiesByEnName(tableName);
        if( excelPropertiesVo== null){
            throw new RuntimeException("当前表枚举中不存在，请联系管理员添加") ;
        }
        Class mapperClazz = excelPropertiesVo.getMapperClazz();
        Class beanClazz = excelPropertiesVo.getBeanClazz();
        String tableChName = excelPropertiesVo.getTableChName();
        List list = saveExcelDataHandle.queryExcelData(mapperClazz, beanClazz);
        log.info("导出的数据{}", list);
        FileWithExcelUtil.exportExcel(list,tableChName,tableChName,beanClazz,tableChName+".xls",response);
    }

}
