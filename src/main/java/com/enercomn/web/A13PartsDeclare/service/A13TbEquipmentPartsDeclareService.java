package com.enercomn.web.A13PartsDeclare.service;

import com.enercomn.utils.PageBean;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A06EquipmentMaterial.bean.A06TbEquipmentMaterial;
import com.enercomn.web.A06EquipmentMaterial.service.A06TbEquipmentMaterialService;
import com.enercomn.web.A13PartsDeclare.bean.A13TbEquipmentPartsDeclare;
import com.enercomn.web.A13PartsDeclare.mapper.A13TbEquipmentPartsDeclareMapper;
import com.enercomn.web.baseManager.model.PageObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Date: 2021/8/16 14:00<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Service
@SuppressWarnings({"all"})
public class A13TbEquipmentPartsDeclareService {

    private final A13TbEquipmentPartsDeclareMapper a13TbEquipmentPartsDeclareMapper;
    private final A06TbEquipmentMaterialService a06TbEquipmentMaterialService;

    public A13TbEquipmentPartsDeclareService(A13TbEquipmentPartsDeclareMapper a13TbEquipmentPartsDeclareMapper, A06TbEquipmentMaterialService a06TbEquipmentMaterialService) {
        this.a13TbEquipmentPartsDeclareMapper = a13TbEquipmentPartsDeclareMapper;
        this.a06TbEquipmentMaterialService = a06TbEquipmentMaterialService;
    }

    public void generateDeclareByMaterialStock(A06TbEquipmentMaterial material){
        A13TbEquipmentPartsDeclare partsDeclare = new A13TbEquipmentPartsDeclare();
        partsDeclare.setSystemMaterialNumber( material.getCode());
        partsDeclare.setNameModel(material.getName());
        partsDeclare.setApplyType(material.getType());
        partsDeclare.setEquipmentPartsBrand(material.getBrand());
        partsDeclare.setPurchaseApplyNo(material.getModelNo());
        partsDeclare.setUseParts( material.getUseDepartment());
        partsDeclare.setUseParts(material.getUsePosition());
//        partsDeclare.setPurchaseNum(getLongValue(material.getStock()));
        partsDeclare.setMinStock( getLongValue(material.getEnoughStock()));
        partsDeclare.setMaxStock(  getLongValue(material.getMaxStock()) );
        partsDeclare.setFactory(material.getSupplerName());
        partsDeclare.setTel(   material.getSupplerTel());
        partsDeclare.setRemarks(  material.getRemark());
        partsDeclare.setTepdId(material.getDeclareId());
        partsDeclare.setIsNew("0");
        this.addData(partsDeclare);

    }

    public long getLongValue(Integer val){
        if(val == null){
            return 0;
        }
        return Long.valueOf(val);
    }
    /**
     * 新增备件申报数据
     * @param partsDeclare
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addData(A13TbEquipmentPartsDeclare partsDeclare) {
        try {
            partsDeclare.setTepdId(StringUtils.getUUID());
            a13TbEquipmentPartsDeclareMapper.insert(partsDeclare);
            if(Integer.valueOf(partsDeclare.getIsNew())==1){
                a06TbEquipmentMaterialService.generateMaterialByAddDeclare(partsDeclare);
            }
            return  true;
        } catch (Exception e) {
            log.info("{}", e);
            throw new RuntimeException("新增异常：" + e.getMessage());
        }
    }


    /**
     * 删除备件申报数据
     *
     * @param prartsDeclare
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteData(A13TbEquipmentPartsDeclare prartsDeclare) {
        try {
            A13TbEquipmentPartsDeclare delParam = new A13TbEquipmentPartsDeclare();
            delParam.setTepdId(prartsDeclare.getTepdId());
            delParam.setIsDel(1);
            a13TbEquipmentPartsDeclareMapper.updateByPrimaryKey(delParam);
            //a06TbEquipmentMaterialService.generateMaterialByDeleteDeclare(prartsDeclare);
            return true;
        } catch (Exception e) {
            log.info("{}", e);
            throw new RuntimeException("删除异常：" + e.getMessage());
        }
    }


    /**
     * 更新备件申报数据
     *
     * @param prartsDeclare
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updateData(A13TbEquipmentPartsDeclare prartsDeclare) {
        try {
            a13TbEquipmentPartsDeclareMapper.updateByPrimaryKey(prartsDeclare);
            if(Integer.valueOf(prartsDeclare.getIsNew())==1){
                a06TbEquipmentMaterialService.generateMaterialByUpdateDeclare(prartsDeclare);
            }else{
//                a06TbEquipmentMaterialService.generateMaterialByDeleteDeclare(prartsDeclare);
            }
            return true;
        } catch (Exception e) {
            log.info("{}", e);
            throw new RuntimeException("修改异常：" + e.getMessage());
        }
    }


    /**
     * 查询备件申报数据列表
     * 分页
     * @param pageObject
     * @return
     */
    public PageBean findDataList(PageObject<A13TbEquipmentPartsDeclare> pageObject) {
        A13TbEquipmentPartsDeclare prartsDeclare = pageObject.getData();
        PageHelper.startPage(pageObject.getCurrPage(), pageObject.getPageSize());
        Example example = new Example(A13TbEquipmentPartsDeclare.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel", 0);
        if(prartsDeclare != null && StringUtils.isNotEmpty(prartsDeclare.getNameModel())){
            criteria.andLike("nameModel", "%"+prartsDeclare.getNameModel()+"%");
        }
        if(prartsDeclare != null && StringUtils.isNotEmpty(prartsDeclare.getSystemMaterialNumber())){
            criteria.andLike("systemMaterialNumber", "%"+prartsDeclare.getSystemMaterialNumber()+"%");
        }
        List<A13TbEquipmentPartsDeclare> a10TbSpotCheckList = a13TbEquipmentPartsDeclareMapper.selectByExample(example);
        PageInfo<A13TbEquipmentPartsDeclare> pageInfo = new PageInfo(a10TbSpotCheckList);
        return new PageBean(pageInfo);
    }



}
