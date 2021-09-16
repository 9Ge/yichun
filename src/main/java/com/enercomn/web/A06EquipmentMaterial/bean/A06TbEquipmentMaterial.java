package com.enercomn.web.A06EquipmentMaterial.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.enercomn.web.A99TableExcelCommon.anno.DefaultPrimaryKey;
import com.enercomn.web.baseManager.model.ModelCommonProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * tb_equipment_material
 * @author 
 */
@Data
@ApiModel
@Table(name = "tb_equipment_material")
public class A06TbEquipmentMaterial extends ModelCommonProperties implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "material_id")
    @ApiModelProperty("主键")
    @DefaultPrimaryKey
    private String materialId;

    /**
     * 物料编号
     */
    @Excel(name="物料编号")
    @ApiModelProperty("物料编号")
    private String code;

    /**
     * 物料名称
     */
    @Excel(name="物料名称")
    @ApiModelProperty("物料名称")
    private String name;

    /**
     * 物料类别
     */
    @Excel(name="物料类别")
    @ApiModelProperty("物料类别")
    private String type;

    /**
     * 物料品牌
     */
    @Excel(name="物料品牌")
    @ApiModelProperty("物料品牌")
    private String brand;

    /**
     * 规格型号
     */
    @Excel(name="规格型号")
    @ApiModelProperty("规格型号")
    private String modelNo;

    /**
     * 使用部位
     */
    @Excel(name="使用部门")
    @ApiModelProperty("使用部门")
    private String useDepartment;
    /**
     * 使用部位
     */
    @Excel(name="使用部位")
    @ApiModelProperty("使用部位")
    private String usePosition;

    /**
     * 现有库存
     */
    @Excel(name="现有库存")
    @ApiModelProperty("现有库存")
    private Integer stock;

    /**
     * 安全库存
     */
    @Excel(name="安全库存")
    @ApiModelProperty("安全库存")
    private Integer enoughStock;

    /**
     * 最高储存量
     */
    @Excel(name="最高储存量")
    @ApiModelProperty("最高储存量")
    private Integer maxStock;

    /**
     * 物料存放点
     */
    @Excel(name="物料存放点")
    @ApiModelProperty("物料存放点")
    private String place;

    /**
     * 物料供应厂家
     */
    @Excel(name="物料供应厂家")
    @ApiModelProperty("物料供应厂家")
    private String supplerName;

    /**
     * 厂家联系方式
     */
    @Excel(name="厂家联系方式")
    @ApiModelProperty("厂家联系方式")
    private String supplerTel;

    /**
     * 物料图片
     */
    @ApiModelProperty("物料图片")
    private String photo;

    /**
     * 备注
     */
    @Excel(name="备注")
    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty(value = "物料采购id",hidden = true)
    private String declareId;

    @Transient
    @ApiModelProperty(value = "物料采购信息")
    private List<A06TbEquipmentMaterialPurchase> a06TbEquipmentMaterialPurchaseList;


    @Transient
    @ApiModelProperty(value = "物料领用信息")
    private List<A06TbEquipmentMaterialUse> a06TbEquipmentMaterialUseList;

    private static final long serialVersionUID = 1L;
}