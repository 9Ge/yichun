package com.enercomn.web.A01EquipmentManagement.mapper;
import com.enercomn.web.A01EquipmentManagement.bean.A01EquipmentAssetsBean;
import com.enercomn.web.A01EquipmentManagement.bean.A01TbEquipmentMaterialRelation;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * DAO 使用通用Mapper
 * DSO接口需要继承 tk.mybatis.mapper.common.Mapper
 */
@Repository
public interface A01EquipmentAsstsMapperTK extends Mapper<A01EquipmentAssetsBean> {

    List<A01EquipmentAssetsBean> queryEquipment(A01EquipmentAssetsBean assetsBean);

}
