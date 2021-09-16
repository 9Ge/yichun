package com.enercomn.web.A99TableExcelCommon.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Date: 2021/9/8 11:51<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
public class ExcelPropertiesVo {
    private Class beanClazz;
    private Class mapperClazz;
    private String tableEnName;
    private String tableChName;

    public ExcelPropertiesVo(Class beanClazz, Class mapperClazz, String tableEnName, String tableChName) {
        this.beanClazz = beanClazz;
        this.mapperClazz = mapperClazz;
        this.tableEnName = tableEnName;
        this.tableChName = tableChName;
    }
}
