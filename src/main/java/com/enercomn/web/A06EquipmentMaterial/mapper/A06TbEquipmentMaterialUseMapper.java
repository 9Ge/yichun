package com.enercomn.web.A06EquipmentMaterial.mapper;

import com.enercomn.web.A06EquipmentMaterial.bean.A06TbEquipmentMaterialUse;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface A06TbEquipmentMaterialUseMapper extends Mapper<A06TbEquipmentMaterialUse> {
    int saveBatch(List<A06TbEquipmentMaterialUse> list);
}