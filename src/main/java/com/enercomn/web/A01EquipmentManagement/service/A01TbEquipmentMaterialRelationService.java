package com.enercomn.web.A01EquipmentManagement.service;

import com.enercomn.utils.ListPageUtil;
import com.enercomn.utils.PageBean;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A01EquipmentManagement.bean.A01EquipmentAssetsBean;
import com.enercomn.web.A01EquipmentManagement.bean.A01TbEquipmentMaterialRelation;
import com.enercomn.web.A01EquipmentManagement.mapper.A01TbEquipmentMaterialRelationMapper;
import com.enercomn.web.baseManager.model.PageObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import com.enercomn.web.A01EquipmentManagement.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Date: 2021/8/11 16:47<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Service
public class A01TbEquipmentMaterialRelationService {

    private final A01TbEquipmentMaterialRelationMapper a01TbEquipmentMaterialRelationMapper;

    public A01TbEquipmentMaterialRelationService(A01TbEquipmentMaterialRelationMapper a01TbEquipmentMaterialRelationMapper) {
        this.a01TbEquipmentMaterialRelationMapper = a01TbEquipmentMaterialRelationMapper;
    }

    /**
     * 新增关联关系
     * @param relation
     */
    public boolean addData(A01TbEquipmentMaterialRelation relation){
        relation.setRelationId(StringUtils.getUUID());
       return  a01TbEquipmentMaterialRelationMapper.insert(relation)>=0;
    }

    /**
     * 批量新增关联关系
     * @param relations
     */
    public boolean addBatchData(List<A01TbEquipmentMaterialRelation> relations,String equId){
        if(CollectionUtils.isEmpty(relations)){
            return true;
        }
        relations.forEach(relation -> {
            relation.setRelationId(StringUtils.getUUID());
            relation.setTbEquipmentAssetsId(equId);
        });
        return a01TbEquipmentMaterialRelationMapper.saveBatch(relations)>=0;
    }
    /**
     * 更新物料和设备关系
     * @param relation
     */
    public boolean updateData(A01TbEquipmentMaterialRelation relation){
        return a01TbEquipmentMaterialRelationMapper.updateByPrimaryKey(relation)>=0;
    }

    /**
     * 删除物料和设备关系
     * @param relation
     */
    public boolean deleteData(A01TbEquipmentMaterialRelation relation){
        relation.setIsDel(1);
        return a01TbEquipmentMaterialRelationMapper.updateByPrimaryKey(relation)>=0;
    }

    /**
     * 山观关联关系，根据设备Id
     * @param equId
     */
    public void removeDataByEquId(String equId){
        if(StringUtils.isEmpty(equId)){
            return;
        }
        Example example = new Example(A01TbEquipmentMaterialRelation.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("tbEquipmentAssetsId",equId);
        a01TbEquipmentMaterialRelationMapper.deleteByExample(example);
    }

    /**
     * 根据设备Id查找物料关联关系
     * @param pageObject
     * @return
     */
    public PageBean findDataListByTeaId(PageObject<A01EquipmentAssetsBean> pageObject){
        if(pageObject.getData()==null){
            return null;
        }
        List<A01EquipmentVo> relations = a01TbEquipmentMaterialRelationMapper.queryEquipmentByTeaId(pageObject.getData().getTeaId());
        return ListPageUtil.pageInfo(pageObject,relations);
    }
}
