package com.enercomn.web.A06EquipmentMaterial.bean;

import com.enercomn.web.baseManager.model.ModelCommonProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * tb_equipment_material_purchase
 * @author 
 */
@ApiModel
@Data
@Table(name = "tb_equipment_material_purchase")
public class A06TbEquipmentMaterialPurchase extends ModelCommonProperties implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private String purchaseId;

    /**
     * 采购周期
     */
    @ApiModelProperty("采购周期")
    private String purWeek;

    /**
     * 采购单价
     */
    @ApiModelProperty("采购单价")
    private BigDecimal price;

    @ApiModelProperty("采购数量")
    private BigDecimal purchaseNum;

    /**
     * 要求到货日期
     */
    @ApiModelProperty("要求到货日期")
    private Date estimateLoadDate;

    /**
     * 实际到货日期
     */
    @ApiModelProperty("实际到货日期")
    private Date actualLoadDate;

    /**
     * 到货率
     */
    @ApiModelProperty("到货率")
    private String rate;

    /**
     * 外键（关联物料表Id）
     */
    @ApiModelProperty("外键（关联物料表Id）")
    private String materialId;

    private static final long serialVersionUID = 1L;
}