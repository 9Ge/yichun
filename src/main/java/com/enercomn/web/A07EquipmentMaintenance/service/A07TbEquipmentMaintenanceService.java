package com.enercomn.web.A07EquipmentMaintenance.service;

import com.enercomn.Enum.CodePrefix;
import com.enercomn.utils.CodeUtil;
import com.enercomn.utils.ListPageUtil;
import com.enercomn.utils.PageBean;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A07EquipmentMaintenance.bean.A07EquipmentMaintenanceVo;
import com.enercomn.web.A07EquipmentMaintenance.bean.A07TbEquipmentMaintenance;
import com.enercomn.web.A07EquipmentMaintenance.bean.A07TbEquipmentMaintenanceMaterialRelation;
import com.enercomn.web.A07EquipmentMaintenance.mapper.A07TbEquipmentMaintenanceMapper;
import com.enercomn.web.baseManager.model.PageObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Date: 2021/8/12 11:08<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Service
public class A07TbEquipmentMaintenanceService {

    private final A07TbEquipmentMaintenanceMapper a07TbEquipmentMaintenanceMapper;
    private final A07TbEquipmentMaintenanceMaterialRelationService a07TbEquipmentMaintenanceMaterialRelationService;


    public A07TbEquipmentMaintenanceService(A07TbEquipmentMaintenanceMapper a07TbEquipmentMaintenanceMapper, A07TbEquipmentMaintenanceMaterialRelationService a07TbEquipmentMaintenanceMaterialRelationService) {
        this.a07TbEquipmentMaintenanceMapper = a07TbEquipmentMaintenanceMapper;
        this.a07TbEquipmentMaintenanceMaterialRelationService = a07TbEquipmentMaintenanceMaterialRelationService;
    }

    /**
     * 新增设备预防性维护标准
     *
     * @param maintenance
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public boolean addData(A07TbEquipmentMaintenance maintenance) {
        maintenance.setMaintenanceId(StringUtils.getUUID());
        maintenance.setMaintenanceCode(CodeUtil.generateCode(CodePrefix.YFWH.getPrefix()));
        a07TbEquipmentMaintenanceMapper.insert(maintenance);
        if (CollectionUtils.isEmpty(maintenance.getRelationList())) {
            return true;
        }
        saveMaterialRelation(maintenance);
        return true;
    }

    public void saveMaterialRelation(A07TbEquipmentMaintenance maintenance) {
        List<A07TbEquipmentMaintenanceMaterialRelation> saveRelationList = maintenance.getRelationList();
        maintenance.getRelationList().forEach(o -> {
            o.setMaintenanceId(maintenance.getMaintenanceId());
            o.setTemmrId(StringUtils.getUUID());
        });
        a07TbEquipmentMaintenanceMaterialRelationService.saveBatch(saveRelationList);
    }

    /**
     * 删除设备预防性维护标准
     *
     * @param maintenance
     * @return
     */
    public boolean delData(A07TbEquipmentMaintenance maintenance) {
        A07TbEquipmentMaintenance delMaintenance = new A07TbEquipmentMaintenance();
        delMaintenance.setMaintenanceId(maintenance.getMaintenanceId());
        delMaintenance.setIsDel(1);
        a07TbEquipmentMaintenanceMapper.updateByPrimaryKey(delMaintenance);
        a07TbEquipmentMaintenanceMaterialRelationService.deleteByMaintenanceId(maintenance.getMaintenanceId());
        return true;
    }

    /**
     * 修改设备预防性维护标准
     *
     * @param maintenance
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public boolean updateData(A07TbEquipmentMaintenance maintenance) {
        a07TbEquipmentMaintenanceMaterialRelationService.deleteByMaintenanceId(maintenance.getMaintenanceId());
        a07TbEquipmentMaintenanceMapper.updateByPrimaryKey(maintenance);
        List<A07TbEquipmentMaintenanceMaterialRelation> relationList = maintenance.getRelationList();
        if (!CollectionUtils.isEmpty(relationList)) {
            saveMaterialRelation(maintenance);
        }
        return true;
    }

    /**
     * 查询设备预防性维护标准列表【分页】
     *
     * @param pageObject 分页对象
     * @return
     */
    public PageBean findDataList(PageObject<A07TbEquipmentMaintenance> pageObject) {
        A07TbEquipmentMaintenance data = pageObject.getData();
        if(data != null){
            String project = data.getProject();
            if(StringUtils.isNotEmpty(project)){
                data.setProject("%"+project+"%");
            }
            String code = data.getMaintenanceCode();
            if(StringUtils.isNotEmpty(code)){
                data.setMaintenanceCode("%"+code+"%");
            }
        }
        List<A07EquipmentMaintenanceVo> a08TbEquipmentMainbraceList = a07TbEquipmentMaintenanceMapper.queryEquipmentAndMaterial(data);
        return ListPageUtil.pageInfo(pageObject, a08TbEquipmentMainbraceList);
    }


    public A07TbEquipmentMaintenance findDataById(String id) {
        if(StringUtils.isEmpty(id)){
            return new A07TbEquipmentMaintenance();
        }
        return a07TbEquipmentMaintenanceMapper.selectByPrimaryKey(id);
    }

    /**
     * 1次/1班   1
     * 1次/1周   2
     * 1次/1月   3
     * 1次/1季度   4
     * 1次/1半年   5
     * 1次/1年   6
     *
     * @param week
     * @return
     */
    public List<A07TbEquipmentMaintenance> getMaintenanceByWeek(int week) {
        Example example = new Example(A07TbEquipmentMaintenance.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel", 0);
        criteria.andEqualTo("week", "1011" + week);
        List<A07TbEquipmentMaintenance> maintenanceList = a07TbEquipmentMaintenanceMapper.selectByExample(example);
        return maintenanceList;
    }

}
