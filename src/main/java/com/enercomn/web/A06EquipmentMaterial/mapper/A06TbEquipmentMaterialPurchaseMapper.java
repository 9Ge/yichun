package com.enercomn.web.A06EquipmentMaterial.mapper;

import com.enercomn.web.A06EquipmentMaterial.bean.A06TbEquipmentMaterialPurchase;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface A06TbEquipmentMaterialPurchaseMapper extends Mapper<A06TbEquipmentMaterialPurchase> {

    int saveBatch(List<A06TbEquipmentMaterialPurchase> list);
}