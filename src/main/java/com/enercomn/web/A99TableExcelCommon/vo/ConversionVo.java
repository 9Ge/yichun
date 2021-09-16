package com.enercomn.web.A99TableExcelCommon.vo;

import lombok.Data;

import java.util.Map;

/**
 * @Date: 2021/9/16 13:10<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Data
public class ConversionVo {
    private String column;
    private String byTable;
    private String conditionsColumn;
    private String conditionsValue;
}
