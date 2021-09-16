package com.enercomn.web.A99TableExcelCommon.mapper;

import com.enercomn.web.A99TableExcelCommon.vo.ConversionVo;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.Map;

/**
 * @Date: 2021/9/16 13:06<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Mapper
public interface A99ExcelMapper {
    String conversion(ConversionVo conversionVo);
    String execSql(@Param("sql") String sql);
}
