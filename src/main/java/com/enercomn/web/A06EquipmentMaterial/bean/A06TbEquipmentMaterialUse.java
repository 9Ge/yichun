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
 * tb_equipment_material_use
 * @author 
 */
@ApiModel
@Data
@Table(name = "tb_equipment_material_use")
public class A06TbEquipmentMaterialUse extends ModelCommonProperties implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private String useId;

    @ApiModelProperty("使用数量")
    private BigDecimal useNum;

    /**
     * 实际领用日期
     */
    @ApiModelProperty("实际领用日期")
    private Date actualUseDate;

    /**
     * 更换日期
     */
    @ApiModelProperty("更换日期")
    private Date actualChangeDate;

    /**
     * 外键（关联物料表Id）
     */
    @ApiModelProperty("外键（关联物料表Id）")
    private String materialId;

    @ApiModelProperty("外键（关联物料表Id）")
    private String sourceId;
    private static final long serialVersionUID = 1L;
}