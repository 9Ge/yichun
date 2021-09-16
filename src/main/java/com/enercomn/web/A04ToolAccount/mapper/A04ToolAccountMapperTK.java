package com.enercomn.web.A04ToolAccount.mapper;

import com.enercomn.web.A04ToolAccount.bean.A04ToolAccount;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * DAO 使用通用Mapper
 * DSO接口需要继承 tk.mybatis.mapper.common.Mapper
 */
@Repository
public interface A04ToolAccountMapperTK extends Mapper<A04ToolAccount> {


}
