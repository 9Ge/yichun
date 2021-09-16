package com.enercomn.web.A07EquipmentMaintenance.mapper;


import com.enercomn.web.A07EquipmentMaintenance.bean.A07EquipmentMaintenanceVo;
import com.enercomn.web.A07EquipmentMaintenance.bean.A07TbEquipmentMaintenance;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author yangcheng
 */
@Repository
public interface A07TbEquipmentMaintenanceMapper extends Mapper<A07TbEquipmentMaintenance> {

    List<A07EquipmentMaintenanceVo> queryEquipmentAndMaterial(A07TbEquipmentMaintenance maintenance);
}