package com.enercomn.web.A07EquipmentMaintenance.service;

import com.enercomn.web.A07EquipmentMaintenance.bean.A07TbEquipmentMaintenanceMaterialRelation;
import com.enercomn.web.A07EquipmentMaintenance.mapper.A07TbEquipmentMaintenanceRelationMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Date: 2021/8/30 18:16<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Service
public class A07TbEquipmentMaintenanceMaterialRelationService {

    private final A07TbEquipmentMaintenanceRelationMapper a07TbEquipmentMaintenanceRelationMapper;

    public A07TbEquipmentMaintenanceMaterialRelationService(A07TbEquipmentMaintenanceRelationMapper a07TbEquipmentMaintenanceRelationMapper) {
        this.a07TbEquipmentMaintenanceRelationMapper = a07TbEquipmentMaintenanceRelationMapper;
    }

    /**
     * 批量新增预防维护关系
     * @param relationList
     */
    public void saveBatch(List<A07TbEquipmentMaintenanceMaterialRelation> relationList){
        a07TbEquipmentMaintenanceRelationMapper.saveBatch(relationList);
    }

    /**
     * 根据预防维护Id删除关联关系
     * @param maintenanceId
     */
    public void deleteByMaintenanceId(String maintenanceId){
        Example example = new Example(A07TbEquipmentMaintenanceMaterialRelation.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("maintenanceId",maintenanceId);
        a07TbEquipmentMaintenanceRelationMapper.deleteByExample(example);
    }


}
