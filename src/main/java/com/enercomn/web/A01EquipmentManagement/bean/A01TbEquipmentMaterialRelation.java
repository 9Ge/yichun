package com.enercomn.web.A01EquipmentManagement.bean;

import com.enercomn.web.baseManager.model.ModelCommonProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;


/**
 * tb_equipment_material_relation
 * @author 
 */
@Data
@ApiModel
@Table(name = "tb_equipment_material_relation")
public class A01TbEquipmentMaterialRelation extends ModelCommonProperties implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "relation_id")
    @ApiModelProperty(value = "主键")
    private String relationId;

    /**
     * 设备资产Id
     */
    @ApiModelProperty(value = "设备资产Id")
    private String tbEquipmentAssetsId;

    /**
     * 物料Id
     */
    @ApiModelProperty(value = "物料Id")
    private String materialId;

    /**
     * 装置量
     */
    @ApiModelProperty(value = "装置量")
    private String capacity;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String unit;

    @Transient
    @ApiModelProperty(value = "物料名称")
    private String name;
    @Transient
    @ApiModelProperty(value = "物料编码")
    private String code;

    @Transient
    @ApiModelProperty(value = "物料Id字符串")
    private String idStr;

    private static final long serialVersionUID = 1L;
}