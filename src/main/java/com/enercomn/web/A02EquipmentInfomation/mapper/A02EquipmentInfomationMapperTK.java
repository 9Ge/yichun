package com.enercomn.web.A02EquipmentInfomation.mapper;

import com.enercomn.web.A02EquipmentInfomation.bean.A02EquipmentInfomationBean;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * DAO 使用通用Mapper
 * DSO接口需要继承 tk.mybatis.mapper.common.Mapper
 */
@Repository
public interface A02EquipmentInfomationMapperTK extends Mapper<A02EquipmentInfomationBean> {


}
