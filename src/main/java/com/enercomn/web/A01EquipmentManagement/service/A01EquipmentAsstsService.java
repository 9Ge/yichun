package com.enercomn.web.A01EquipmentManagement.service;

import com.enercomn.utils.ListPageUtil;
import com.enercomn.utils.PageBean;
import com.enercomn.utils.PageUtil;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A01EquipmentManagement.bean.A01EquipmentAssetsBean;
import com.enercomn.web.A01EquipmentManagement.bean.A01TbEquipmentMaterialRelation;
import com.enercomn.web.A01EquipmentManagement.mapper.A01EquipmentAsstsMapperTK;
import com.enercomn.web.A01EquipmentManagement.vo.A01EquipmentAssetsVo;
import com.enercomn.web.A05OPLClass.bean.A05TbOplClass;
import com.enercomn.web.baseManager.model.PageObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class A01EquipmentAsstsService {

    @Autowired
    private A01TbEquipmentMaterialRelationService a01TbEquipmentMaterialRelationService;
    @Autowired
    A01EquipmentAsstsMapperTK mapperTK;

    public PageBean findDataList(A01EquipmentAssetsBean bean, int currPage, int pageSize) {
        try {
            if (bean != null){
                String equipmentCode = bean.getEquipmentCode();
                String equipmentName = bean.getEquipmentName();
                if(StringUtils.isNotEmpty(equipmentCode)){
                    bean.setEquipmentCode("%"+equipmentCode+"%");
                }
                if(StringUtils.isNotEmpty(equipmentName)){
                    bean.setEquipmentName("%"+equipmentName+"%");
                }
            }

            List<A01EquipmentAssetsBean> passageWayInfoList = mapperTK.queryEquipment(bean);
            return  ListPageUtil.pageInfo(new PageObject(currPage,pageSize),passageWayInfoList);
        } catch (Exception e) {
            log.error("查询设备资产台账管理信息出错：", e);
            return null;
        }

    }



    /**
     * 设备资产下拉框
     *
     * @return
     */
    public List<A01EquipmentAssetsVo> equipmentSelect() {
        try {
            List<A01EquipmentAssetsBean> passageWayInfoList = mapperTK.selectAll();
            List<A01EquipmentAssetsVo> resultVos = new ArrayList<>();
            passageWayInfoList.forEach(e -> {
                A01EquipmentAssetsVo vo = new A01EquipmentAssetsVo();
                vo.setEquipmentCode(e.getEquipmentCode());
                vo.setTeaId(e.getTeaId());
                vo.setEquipmentName(e.getEquipmentName());
                resultVos.add(vo);
            });
            return resultVos;
        } catch (Exception e) {
            log.error("查询设备资产台账管理信息下拉框出错：", e);
            return null;
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public boolean addData(A01EquipmentAssetsBean bean) {
        bean.setTeaId(StringUtils.getUUID());
        mapperTK.insert(bean);
        a01TbEquipmentMaterialRelationService.addBatchData(bean.getRelationList(), bean.getTeaId());
        return true;

    }


    @Transactional(rollbackFor = Exception.class)
    public boolean updateData(A01EquipmentAssetsBean bean) {
        a01TbEquipmentMaterialRelationService.removeDataByEquId(bean.getTeaId());
        mapperTK.updateByPrimaryKeySelective(bean);
        a01TbEquipmentMaterialRelationService.addBatchData(bean.getRelationList(), bean.getTeaId());
        return true;

    }

    /**
     * 删除
     *
     * @param bean
     * @return
     */
    public int deleteData(A01EquipmentAssetsBean bean) {
        a01TbEquipmentMaterialRelationService.removeDataByEquId(bean.getTeaId());
        return mapperTK.deleteByPrimaryKey(bean);
    }

    public A01EquipmentAssetsBean findBeanById(@NonNull String uuid) {
        A01EquipmentAssetsBean assetsBean = mapperTK.selectByPrimaryKey(uuid);
        return assetsBean;
    }

    /**
     * 查询所有设备
     * @return
     */
    public List<A01EquipmentAssetsBean> findAllAssets() {
        try {
            return mapperTK.selectAll();
        } catch (Exception e) {
            log.error("查询设备资产台账管理信息出错：", e);
            return null;
        }
    }

    private List<A01TbEquipmentMaterialRelation> getRelationsByIdStr(List<A01TbEquipmentMaterialRelation> relationList) {
        List<A01TbEquipmentMaterialRelation> saveRelationList = new ArrayList<>();
        if (CollectionUtils.isEmpty(relationList)) {
            return saveRelationList;
        }
        while (true) {
            relationList.forEach(o -> {
                String idString = o.getIdStr();
                String[] ids = idString.split(",");
                for (int i = 0; i < ids.length; i++) {
                    A01TbEquipmentMaterialRelation relation = new A01TbEquipmentMaterialRelation();
                    relation.setMaterialId(ids[i]);
                    relation.setTbEquipmentAssetsId(ids[i]);
                    relation.setCapacity(o.getCapacity());
                    relation.setUnit(o.getUnit());
                    saveRelationList.add(relation);
                }
            });
            break;
        }
        return saveRelationList;
    }
}
