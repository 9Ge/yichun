package com.enercomn.web.A07EquipmentMaintenance.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * tb_equipment_maintenance_material_relation
 * @author 
 */
@Data
@Table(name = "tb_equipment_maintenance_material_relation")
@ApiModel
public class A07TbEquipmentMaintenanceMaterialRelation implements Serializable {

    @Id
    @Column(name = "temmr_id")
    @ApiModelProperty("主键")
    private String temmrId;

//    @Transient
//    @ApiModelProperty("主键Id")
//    private String idStr;

    @Transient
    @ApiModelProperty("物料名称")
    private String materialName;

    @Transient
    @ApiModelProperty("物料编码")
    private String materialCode;

    @ApiModelProperty("物料id 外键")
    private String tbEquipmentMaterialId;

    @ApiModelProperty("物料数量")
    private Integer materialNum;

    @ApiModelProperty("预防维护id 外键")
    private String maintenanceId;

    private static final long serialVersionUID = 1L;
}