package com.enercomn.web.A06EquipmentMaterial.service;

import com.enercomn.utils.ListPageUtil;
import com.enercomn.utils.PageBean;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A06EquipmentMaterial.bean.A06TbEquipmentMaterial;
import com.enercomn.web.A06EquipmentMaterial.bean.A06TbEquipmentMaterialUse;
import com.enercomn.web.A06EquipmentMaterial.mapper.A06TbEquipmentMaterialMapperTk;
import com.enercomn.web.A06EquipmentMaterial.mapper.A06TbEquipmentMaterialUseMapper;
import com.enercomn.web.A12Repair.bean.A12TbMaintenanceRecordLedger;
import com.enercomn.web.A12Repair.mapper.A12TbMaintenanceRecordLedgerMapper;
import com.enercomn.web.A13PartsDeclare.service.A13TbEquipmentPartsDeclareService;
import com.enercomn.web.A99TableExcelCommon.handle.SpringContextUtil;
import com.enercomn.web.baseManager.model.PageObject;
import com.github.pagehelper.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Date: 2021/8/10 16:40<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Service
public class A06TbEquipmentMaterialUseService {

    private final A06TbEquipmentMaterialUseMapper a06TbEquipmentMaterialUseMapper;
    private final A06TbEquipmentMaterialMapperTk a06TbEquipmentMaterialMapperTk ;
    private final A12TbMaintenanceRecordLedgerMapper a12TbMaintenanceRecordLedgerMapper ;

    public A06TbEquipmentMaterialUseService(A06TbEquipmentMaterialUseMapper a06TbEquipmentMaterialUseMapper,
                                            A06TbEquipmentMaterialMapperTk a06TbEquipmentMaterialMapperTk, A12TbMaintenanceRecordLedgerMapper a12TbMaintenanceRecordLedgerMapper) {
        this.a06TbEquipmentMaterialUseMapper = a06TbEquipmentMaterialUseMapper;
        this.a06TbEquipmentMaterialMapperTk = a06TbEquipmentMaterialMapperTk;
        this.a12TbMaintenanceRecordLedgerMapper = a12TbMaintenanceRecordLedgerMapper;
    }

    /**
     * 维修申请记录添加时 新增领用记录
     * @param recordLedger
     */
    @Transactional(rollbackFor = Exception.class)
    public void generateUseByAddRepair(A12TbMaintenanceRecordLedger recordLedger) {
        if(StringUtil.isNotEmpty(recordLedger.getTepId() )){
            A06TbEquipmentMaterialUse materialUse = new A06TbEquipmentMaterialUse();
            materialUse.setActualUseDate(recordLedger.getDate());
            materialUse.setActualChangeDate(recordLedger.getDate());
            materialUse.setMaterialId(recordLedger.getTepId());
            materialUse.setSourceId(recordLedger.getTmrlId());
            materialUse.setUseNum(recordLedger.getMaterialNum());
            this.addData(materialUse);
            if(recordLedger.getMaterialNum() != null){
                this.dealMaterialStock(recordLedger.getTepId(),recordLedger.getMaterialNum().intValue(),true);
            }
        }
    }

    public synchronized void dealMaterialStock(String id,int num,boolean flag){
        A06TbEquipmentMaterial a06TbEquipmentMaterial = a06TbEquipmentMaterialMapperTk.selectByPrimaryKey(id);
        if(a06TbEquipmentMaterial == null){
            return;
        }
        Integer stock = a06TbEquipmentMaterial.getStock();
        if(stock == null){
            stock = 0;
        }
        if(flag){
            stock = stock - num;
        }else{
            stock = stock + num;
        }
        a06TbEquipmentMaterial.setStock(stock);
        a06TbEquipmentMaterialMapperTk.updateByPrimaryKey(a06TbEquipmentMaterial);
        this.generateDeclareByStock(id);
    }

    /**
     * 处理库存信息。
     * 当库存小于安全库存时怎加备件申请
     * @param id
     */
    public void generateDeclareByStock( String id){
        A13TbEquipmentPartsDeclareService a13TbEquipmentPartsDeclareService = (A13TbEquipmentPartsDeclareService)SpringContextUtil.getBean("a13TbEquipmentPartsDeclareService");
        A06TbEquipmentMaterial a06TbEquipmentMaterial = a06TbEquipmentMaterialMapperTk.selectByPrimaryKey(id);
        Integer enoughStock = a06TbEquipmentMaterial.getEnoughStock()==null?0:a06TbEquipmentMaterial.getEnoughStock();
        Integer stock = a06TbEquipmentMaterial.getStock()==null?0:a06TbEquipmentMaterial.getStock();
        if(stock<0 || stock< enoughStock){
            a13TbEquipmentPartsDeclareService.generateDeclareByMaterialStock(a06TbEquipmentMaterial);
        }
    }

    /**
     * 维修申请记录修改时 先删除之前的记录 再新增领用记录
     * @param recordLedger
     */
    public void generateUseByUpdateRepair(A12TbMaintenanceRecordLedger recordLedger) {
        deleteBySourceId(recordLedger);
        this.generateUseByAddRepair(recordLedger);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteBySourceId(A12TbMaintenanceRecordLedger recordLedger){
        String tepId = recordLedger.getTepId();
        recordLedger = a12TbMaintenanceRecordLedgerMapper.selectByPrimaryKey(recordLedger.getTmrlId());
        BigDecimal materialNum = recordLedger.getMaterialNum();
        if(materialNum ==null){
            materialNum=BigDecimal.valueOf(0);
        }
        this.dealMaterialStock(tepId,materialNum.intValue(),false);
        Example example = new Example(A06TbEquipmentMaterialUse.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("sourceId",recordLedger.getTmrlId());
        a06TbEquipmentMaterialUseMapper.deleteByExample(example);
    }

    /**
     *
     * @param materialId
     * @return
     */
    public List<A06TbEquipmentMaterialUse> findByMaterialId(String materialId){
        Example example = new Example(A06TbEquipmentMaterialUse.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("materialId",materialId);
        List<A06TbEquipmentMaterialUse> a06TbEquipmentMaterialUses = a06TbEquipmentMaterialUseMapper.selectByExample(example);
        return a06TbEquipmentMaterialUses;
    }

    /**
     * 添加物料领用
     *
     * @param materialUse
     * @return
     */
    public boolean addData(A06TbEquipmentMaterialUse materialUse) {
        try {
            materialUse.setUseId(StringUtils.getUUID());
            return a06TbEquipmentMaterialUseMapper.insert(materialUse) >= 0;
        } catch (Exception e) {
            log.error("新增失败，请求对象{},异常：" + e.getMessage(), materialUse);
            throw new RuntimeException("add data failed" + e.getMessage());
        }
    }

    /**
     * 删除物料领用
     *
     * @param materialUse
     * @return
     */
    public boolean deleteData(A06TbEquipmentMaterialUse materialUse) {
        try {
            materialUse.setIsDel(1);
            return a06TbEquipmentMaterialUseMapper.updateByPrimaryKey(materialUse) >= 0;
        } catch (Exception e) {
            log.error("删除失败，请求对象{},异常：" + e.getMessage(), materialUse);
            throw new RuntimeException("delete data failed" + e.getMessage());
        }
    }

    /**
     * 修改物料领用
     *
     * @param materialUse
     * @return
     */
    public boolean updateData(A06TbEquipmentMaterialUse materialUse) {
        try {
            return a06TbEquipmentMaterialUseMapper.updateByPrimaryKey(materialUse) >= 0;
        } catch (Exception e) {
            log.error("修改失败，请求对象{},异常：" + e.getMessage(), materialUse);
            throw new RuntimeException("update data failed" + e.getMessage());
        }
    }

    /**
     * 物料领用集合分页
     *
     * @param pageObject
     * @return
     */
    public PageBean findDataList(PageObject<A06TbEquipmentMaterialUse> pageObject) {
        try {
            A06TbEquipmentMaterialUse materialUse = pageObject.getData();
            Example example = new Example(A06TbEquipmentMaterial.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("isDel", 0);
            List<A06TbEquipmentMaterialUse> materials = a06TbEquipmentMaterialUseMapper.selectByExample(example);
            return ListPageUtil.pageInfo(pageObject, materials);
        } catch (Exception e) {
            log.error("新增失败，请求对象{},异常：" + e.getMessage(), pageObject);
            throw new RuntimeException("query data failed" + e.getMessage());
        }
    }

}
