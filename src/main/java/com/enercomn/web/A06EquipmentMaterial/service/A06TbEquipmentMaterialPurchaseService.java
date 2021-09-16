package com.enercomn.web.A06EquipmentMaterial.service;

import com.enercomn.utils.ListPageUtil;
import com.enercomn.utils.PageBean;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A06EquipmentMaterial.bean.A06TbEquipmentMaterial;
import com.enercomn.web.A06EquipmentMaterial.bean.A06TbEquipmentMaterialPurchase;
import com.enercomn.web.A06EquipmentMaterial.mapper.A06TbEquipmentMaterialPurchaseMapper;
import com.enercomn.web.baseManager.model.PageObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @Date: 2021/8/10 16:40<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Service
public class A06TbEquipmentMaterialPurchaseService {

    private final A06TbEquipmentMaterialPurchaseMapper a06TbEquipmentMaterialPurchaseMapper;

    public A06TbEquipmentMaterialPurchaseService(A06TbEquipmentMaterialPurchaseMapper a06TbEquipmentMaterialPurchaseMapper) {
        this.a06TbEquipmentMaterialPurchaseMapper = a06TbEquipmentMaterialPurchaseMapper;
    }

    public void deleteByMaterialId(String materialId){
        Example example = new Example(A06TbEquipmentMaterialPurchase.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("materialId",materialId);
        a06TbEquipmentMaterialPurchaseMapper.deleteByExample(example);
    }

    /**
     *
     * @param materialId
     * @return
     */
    public List<A06TbEquipmentMaterialPurchase> findByMaterialId(String materialId){
        Example example = new Example(A06TbEquipmentMaterialPurchase.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("materialId",materialId);
        List<A06TbEquipmentMaterialPurchase> a06TbEquipmentMaterialPurchases = a06TbEquipmentMaterialPurchaseMapper.selectByExample(example);
        return a06TbEquipmentMaterialPurchases;
    }
    public void  saveBatch(A06TbEquipmentMaterial a06TbEquipmentMaterial){
        List<A06TbEquipmentMaterialPurchase> a06TbEquipmentMaterialPurchaseList = a06TbEquipmentMaterial.getA06TbEquipmentMaterialPurchaseList();
        if(!CollectionUtils.isEmpty(a06TbEquipmentMaterialPurchaseList )){
            a06TbEquipmentMaterialPurchaseList.forEach(p->{
                p.setPurchaseId(StringUtils.getUUID());
                p.setMaterialId(a06TbEquipmentMaterial.getMaterialId());
            });
            a06TbEquipmentMaterialPurchaseMapper.saveBatch(a06TbEquipmentMaterialPurchaseList);
        }
    }
    /**
     * 添加物料采购记录
     * @param materialPurchase
     * @return
     */
    public  boolean addData(A06TbEquipmentMaterialPurchase materialPurchase){
        try {
            materialPurchase.setMaterialId(StringUtils.getUUID());
            return a06TbEquipmentMaterialPurchaseMapper.insert(materialPurchase)>=0;
        } catch (Exception e) {
            log.error("新增失败，请求对象{},异常："+e.getMessage(),materialPurchase);
            throw new RuntimeException("add data failed"+e.getMessage());
        }
    }

    /**
     * 删除物料采购记录
     * @param materialPurchase
     * @return
     */
    public  boolean deleteData(A06TbEquipmentMaterialPurchase materialPurchase)  {
        try {
            A06TbEquipmentMaterialPurchase delMaterial = a06TbEquipmentMaterialPurchaseMapper.selectByPrimaryKey(materialPurchase.getMaterialId());
            delMaterial.setIsDel(1);
            return a06TbEquipmentMaterialPurchaseMapper.updateByPrimaryKey(delMaterial)>=0;
        } catch (Exception e) {
            log.error("删除失败，请求对象{},异常："+e.getMessage(),materialPurchase);
            throw new RuntimeException("delete data failed"+e.getMessage());
        }
    }

    /**
     * 修改物料采购记录
     * @param materialPurchase
     * @return
     */
    public  boolean updateData(A06TbEquipmentMaterialPurchase materialPurchase)  {
        try {
            return a06TbEquipmentMaterialPurchaseMapper.updateByPrimaryKey(materialPurchase)>=0;
        } catch (Exception e) {
            log.error("修改失败，请求对象{},异常："+e.getMessage(),materialPurchase);
            throw new RuntimeException("update data failed"+e.getMessage());
        }
    }

    /**
     * 物料采购记录集合分页
     * @param pageObject
     * @return
     */
    public PageBean findDataList(PageObject<A06TbEquipmentMaterialPurchase> pageObject){
        try {
            PageHelper.startPage(pageObject.getCurrPage(),pageObject.getPageSize());
            A06TbEquipmentMaterialPurchase materialPurchase = pageObject.getData();
            Example example = new Example(A06TbEquipmentMaterial.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("isDel",0);
            List<A06TbEquipmentMaterialPurchase> materials = a06TbEquipmentMaterialPurchaseMapper.selectByExample(example);
            return ListPageUtil.pageInfo(pageObject,materials);
        } catch (Exception e) {
            log.error("新增失败，请求对象{},异常："+e.getMessage(),pageObject);
            throw new RuntimeException("query data failed"+e.getMessage());
        }
    }

}
