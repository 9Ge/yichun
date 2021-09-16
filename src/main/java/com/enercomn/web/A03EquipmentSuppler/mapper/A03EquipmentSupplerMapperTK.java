package com.enercomn.web.A03EquipmentSuppler.mapper;

import com.enercomn.web.A03EquipmentSuppler.bean.A03EquipmentSupplerBean;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * DAO 使用通用Mapper
 * DSO接口需要继承 tk.mybatis.mapper.common.Mapper
 */
@Repository
public interface A03EquipmentSupplerMapperTK extends Mapper<A03EquipmentSupplerBean> {

    List<A03EquipmentSupplerBean>  querySupplerAll(A03EquipmentSupplerBean bean);

}
