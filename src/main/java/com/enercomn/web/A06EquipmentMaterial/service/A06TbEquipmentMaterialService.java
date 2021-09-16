package com.enercomn.web.A06EquipmentMaterial.service;

import com.enercomn.utils.ListPageUtil;
import com.enercomn.utils.PageBean;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A06EquipmentMaterial.bean.A06TbEquipmentMaterial;
import com.enercomn.web.A06EquipmentMaterial.bean.A06TbEquipmentMaterialPurchase;
import com.enercomn.web.A06EquipmentMaterial.bean.A06TbEquipmentMaterialUse;
import com.enercomn.web.A06EquipmentMaterial.mapper.A06TbEquipmentMaterialMapperTk;
import com.enercomn.web.A06EquipmentMaterial.mapper.A06TbEquipmentMaterialUseMapper;
import com.enercomn.web.A13PartsDeclare.bean.A13TbEquipmentPartsDeclare;
import com.enercomn.web.baseManager.model.PageObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
public class A06TbEquipmentMaterialService {

    private final A06TbEquipmentMaterialMapperTk a06TbEquipmentMaterialMapperTk ;
    private final A06TbEquipmentMaterialPurchaseService a06TbEquipmentMaterialPurchaseService ;
    private final A06TbEquipmentMaterialUseService a06TbEquipmentMaterialUseService ;

    public A06TbEquipmentMaterialService(A06TbEquipmentMaterialMapperTk a06TbEquipmentMaterialMapperTk, A06TbEquipmentMaterialPurchaseService a06TbEquipmentMaterialPurchaseService, A06TbEquipmentMaterialUseService a06TbEquipmentMaterialUseService) {
        this.a06TbEquipmentMaterialMapperTk = a06TbEquipmentMaterialMapperTk;
        this.a06TbEquipmentMaterialPurchaseService = a06TbEquipmentMaterialPurchaseService;
        this.a06TbEquipmentMaterialUseService = a06TbEquipmentMaterialUseService;
    }

    /**
     * 添加物料
     * @param a06TbEquipmentMaterial
     * @return
     */
    public  boolean addData(A06TbEquipmentMaterial a06TbEquipmentMaterial){
        try {
            a06TbEquipmentMaterial.setMaterialId(StringUtils.getUUID());
            a06TbEquipmentMaterialMapperTk.insert(a06TbEquipmentMaterial);
            a06TbEquipmentMaterialPurchaseService.saveBatch(a06TbEquipmentMaterial);
            return true;
        } catch (Exception e) {
            log.error("新增失败，请求对象{},异常："+e.getMessage(),a06TbEquipmentMaterial);
            throw new RuntimeException("add data failed"+e.getMessage());
        }
    }

    /**
     * 删除物料
     * @param a06TbEquipmentMaterial
     * @return
     */
    public  boolean deleteData(A06TbEquipmentMaterial a06TbEquipmentMaterial)  {
        try {
            A06TbEquipmentMaterial delMaterial = a06TbEquipmentMaterialMapperTk.selectByPrimaryKey(a06TbEquipmentMaterial.getMaterialId());
            delMaterial.setIsDel(1);
            a06TbEquipmentMaterialMapperTk.updateByPrimaryKey(delMaterial);
            a06TbEquipmentMaterialPurchaseService.deleteByMaterialId(a06TbEquipmentMaterial.getMaterialId());
            return true;
        } catch (Exception e) {
            log.error("删除失败，请求对象{},异常："+e.getMessage(),a06TbEquipmentMaterial);
            throw new RuntimeException("delete data failed"+e.getMessage());
        }
    }

    /**
     * 修改物料
     * @param a06TbEquipmentMaterial
     * @return
     */
    public  boolean updateData(A06TbEquipmentMaterial a06TbEquipmentMaterial)  {
        try {
            a06TbEquipmentMaterialMapperTk.updateByPrimaryKey(a06TbEquipmentMaterial);
            a06TbEquipmentMaterialPurchaseService.deleteByMaterialId(a06TbEquipmentMaterial.getMaterialId());
            a06TbEquipmentMaterialPurchaseService.saveBatch(a06TbEquipmentMaterial);
            return true;
        } catch (Exception e) {
            log.error("修改失败，请求对象{},异常："+e.getMessage(),a06TbEquipmentMaterial);
            throw new RuntimeException("update data failed"+e.getMessage());
        }
    }

    /**
     * 物料集合分页
     * @param pageObject
     * @return
     */
    public PageBean findDataList(PageObject<A06TbEquipmentMaterial> pageObject){
        try {
            A06TbEquipmentMaterial material = pageObject.getData();
            Example example = new Example(A06TbEquipmentMaterial.class);
            Example.Criteria criteria = example.createCriteria();
            if(org.apache.commons.lang3.StringUtils.isNotEmpty(material.getName())){
                criteria.andLike("name",material.getName());
            }
            criteria.andEqualTo("isDel",0);
            List<A06TbEquipmentMaterial> materials = a06TbEquipmentMaterialMapperTk.selectByExample(example);
            materials.forEach(m->{
                List<A06TbEquipmentMaterialPurchase> a06TbEquipmentMaterialPurchases = a06TbEquipmentMaterialPurchaseService.findByMaterialId(m.getMaterialId());
                List<A06TbEquipmentMaterialUse> a06TbEquipmentMaterialUses = a06TbEquipmentMaterialUseService.findByMaterialId(m.getMaterialId());
                m.setA06TbEquipmentMaterialPurchaseList(a06TbEquipmentMaterialPurchases);
                m.setA06TbEquipmentMaterialUseList(a06TbEquipmentMaterialUses);
            });

            return ListPageUtil.pageInfo(pageObject,materials);
        } catch (Exception e) {
            log.error("新增失败，请求对象{},异常："+e.getMessage(),pageObject);
            throw new RuntimeException("query data failed"+e.getMessage());
        }
    }

    private A06TbEquipmentMaterial findMaterialsByDeclareId(String declareId){
        Example example = new Example(A06TbEquipmentMaterial.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel",0);
        criteria.andEqualTo("declareId",declareId);
        List<A06TbEquipmentMaterial> a06TbEquipmentMaterials = a06TbEquipmentMaterialMapperTk.selectByExample(example);
        if(a06TbEquipmentMaterials != null && a06TbEquipmentMaterials.size()>0){
            return a06TbEquipmentMaterials.get(0);
        }
        return null;
    }

    public void generateMaterialByAddDeclare(A13TbEquipmentPartsDeclare a13TbEquipmentPartsDeclare){
        A06TbEquipmentMaterial a06TbEquipmentMaterial = this.setMaterialByDeclare(a13TbEquipmentPartsDeclare, new A06TbEquipmentMaterial());
        this.addData(a06TbEquipmentMaterial);
    }

    public void generateMaterialByUpdateDeclare(A13TbEquipmentPartsDeclare a13TbEquipmentPartsDeclare){
        A06TbEquipmentMaterial material = this.findMaterialsByDeclareId(a13TbEquipmentPartsDeclare.getTepdId());
        if(material != null){
            A06TbEquipmentMaterial a06TbEquipmentMaterial = this.setMaterialByDeclare(a13TbEquipmentPartsDeclare,material);
            this.updateData(a06TbEquipmentMaterial);
        }else{
            if(Integer.valueOf(a13TbEquipmentPartsDeclare.getIsNew())==1){
                this.generateMaterialByAddDeclare(a13TbEquipmentPartsDeclare);
            }
        }
    }

    public void generateMaterialByDeleteDeclare(A13TbEquipmentPartsDeclare a13TbEquipmentPartsDeclare){
        A06TbEquipmentMaterial material = this.findMaterialsByDeclareId(a13TbEquipmentPartsDeclare.getTepdId());
        if(material != null){
            this.deleteData(material);
        }
    }

    public A06TbEquipmentMaterial setMaterialByDeclare(A13TbEquipmentPartsDeclare a13TbEquipmentPartsDeclare, A06TbEquipmentMaterial a06TbEquipmentMaterial){
        a06TbEquipmentMaterial.setCode(a13TbEquipmentPartsDeclare.getSystemMaterialNumber());
        a06TbEquipmentMaterial.setName(a13TbEquipmentPartsDeclare.getNameModel());
        a06TbEquipmentMaterial.setType(String.valueOf(a13TbEquipmentPartsDeclare.getApplyType()));
        a06TbEquipmentMaterial.setBrand(a13TbEquipmentPartsDeclare.getEquipmentPartsBrand());
        a06TbEquipmentMaterial.setModelNo(a13TbEquipmentPartsDeclare.getPurchaseApplyNo());
        a06TbEquipmentMaterial.setUseDepartment(a13TbEquipmentPartsDeclare.getUseParts());
        a06TbEquipmentMaterial.setUsePosition(a13TbEquipmentPartsDeclare.getUseParts());
        a06TbEquipmentMaterial.setStock(getLongValue(a13TbEquipmentPartsDeclare.getPurchaseNum()));
        a06TbEquipmentMaterial.setEnoughStock(getLongValue(a13TbEquipmentPartsDeclare.getMinStock()));
        a06TbEquipmentMaterial.setMaxStock(getLongValue(a13TbEquipmentPartsDeclare.getMaxStock()));
        a06TbEquipmentMaterial.setSupplerName(a13TbEquipmentPartsDeclare.getFactory());
        a06TbEquipmentMaterial.setSupplerTel(a13TbEquipmentPartsDeclare.getTel());
        a06TbEquipmentMaterial.setRemark(a13TbEquipmentPartsDeclare.getRemarks());
        a06TbEquipmentMaterial.setDeclareId(a13TbEquipmentPartsDeclare.getTepdId());
        return a06TbEquipmentMaterial;
    }
    public int getLongValue(Long val){
        if(val == null){
            return 0;
        }

        return val.intValue();

    }

    public  List<Map<String, String>> queryMaterial2Equipment(){
        List<Map<String, String>> maps = a06TbEquipmentMaterialMapperTk.queryMaterial(null);
        return maps;
    }

}
