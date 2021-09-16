package com.enercomn.web.A06EquipmentMaterial.mapper;


import com.enercomn.web.A06EquipmentMaterial.bean.A06TbEquipmentMaterial;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author yangcheng
 */
@Repository
public interface A06TbEquipmentMaterialMapperTk extends Mapper<A06TbEquipmentMaterial> {

    List<Map<String ,String>> queryMaterial(A06TbEquipmentMaterial equipmentMaterial);
}