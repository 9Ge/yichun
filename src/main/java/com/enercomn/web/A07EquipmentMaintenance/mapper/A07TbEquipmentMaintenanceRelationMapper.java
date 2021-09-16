package com.enercomn.web.A07EquipmentMaintenance.mapper;


import com.enercomn.web.A07EquipmentMaintenance.bean.A07EquipmentMaintenanceVo;
import com.enercomn.web.A07EquipmentMaintenance.bean.A07TbEquipmentMaintenance;
import com.enercomn.web.A07EquipmentMaintenance.bean.A07TbEquipmentMaintenanceMaterialRelation;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author yangcheng
 */
@Repository
public interface A07TbEquipmentMaintenanceRelationMapper extends Mapper<A07TbEquipmentMaintenanceMaterialRelation> {

    int saveBatch(List<A07TbEquipmentMaintenanceMaterialRelation> relationList);
}