package com.enercomn.web.A99TableExcelCommon.anno;

/**
 * @Date: 2021/9/16 18:18<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
public @interface SQLInfo {
    String sql() ;
    String[] param() ;
}
