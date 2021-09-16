package com.enercomn.web.A07EquipmentMaintenance.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;


/**
 * tb_equipment_maintenance
 * @author 
 */
@Data
@ApiModel
public class A07EquipmentMaintenanceVo {
    /**
     * 主键
     */
    @Id
    @Column(name = "maintenance_id")
    @ApiModelProperty(value = "主键")
    private String maintenanceId;

    /**
     * #{item.
     */
    @ApiModelProperty(value = "设备编号")
    private String equipmentCode;
    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称")
    private String equipmentName;

    /**
     * 模块/单元(外键，关联设备模块表)
     */
    @ApiModelProperty(value = "模块/单元(外键，关联设备模块表)")
    private String moduleId;

    /**
     * 维护部位(外键，关联设备部位表)
     */
    @ApiModelProperty(value = "维护部位(外键，关联设备部位表)")
    private String positionId;

    /**
     * 维护项目
     */
    @ApiModelProperty(value = "维护项目")
    private String project;

    /**
     * 维护标准
     */
    @ApiModelProperty(value = "主键")
    private String standard;

    /**
     * 维护方法
     */
    @ApiModelProperty(value = "维护方法")
    private String method;

    /**
     * 维护周期
     */
    @ApiModelProperty(value = "维护周期")
    private String week;

    /**
     * 维护责任人(外键，关联设备管理人员信息清单)
     */
    @ApiModelProperty(value = "维护责任人(外键，关联设备管理人员信息清单)")
    private String teiId;

    /**
     * 维护人数
     */
    @ApiModelProperty(value = "维护人数")
    private Integer peopleNum;

    /**
     * 维护工时
     */
    @ApiModelProperty(value = "维护工时")
    private Integer hour;

    /**
     * 备件编号
     */
    @ApiModelProperty(value = "备件编号")
    private String materialCode;
    /**
     * 备件名称
     */
    @ApiModelProperty(value = "备件名称")
    private String materialName;

    @ApiModelProperty(value = "备件数量")
    private String materialNum;

    /**
     * 维护工具
     */
    @ApiModelProperty(value = "维护工具")
    private String tool;

    /**
     * 维护动能需求
     */
    @ApiModelProperty(value = "维护动能需求")
    private String demand;

    /**
     * 是否需停机实施(字典)
     */
    @ApiModelProperty(value = "是否需停机实施(字典)")
    private Integer isDown;

    /**
     * 是否需操作工支持(字典)
     */
    @ApiModelProperty(value = "是否需操作工支持(字典)")
    private Integer isMaker;

    /**
     * 是否为维护关键设备(字典)
     */
    @ApiModelProperty(value = "是否为维护关键设备(字典)")
    private Integer isEquipmentParts;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "模块/单元")
    private String moduleUnit;


    @ApiModelProperty(value = "部位")
    private String maintenanceParts;


    @ApiModelProperty(value = "维护项目")
    private String maintenanceProjects;

    @ApiModelProperty(value = "是否需要备件")
    private String isNeedMaterial;

    @ApiModelProperty(value = "设备Id")
    private String tbEquipmentAssetsId;

    @ApiModelProperty(value = "模块单元Id,设备基础参数Id")
    private String tbEquipmentParamId;


    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @Transient
    @ApiModelProperty(value = "预防维护和关联关系集合")
    private List<A07TbEquipmentMaintenanceMaterialRelation> relationList;

    @ApiModelProperty(value = "维护标准编号")
    private String maintenanceCode;
}
