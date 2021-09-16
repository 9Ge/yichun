package com.enercomn.web.A07EquipmentMaintenance.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.enercomn.web.A01EquipmentManagement.bean.A01EquipmentAssetsBean;
import com.enercomn.web.A08EquipmentParam.bean.A08TbEquipmentParam;
import com.enercomn.web.A99TableExcelCommon.anno.Conversion;
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
import java.util.Date;
import java.util.List;


/**
 * tb_equipment_maintenance
 * @author 
 */
@Data
@ApiModel
@Table(name = "tb_equipment_maintenance")
public class A07TbEquipmentMaintenance extends ModelCommonProperties implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "maintenance_id")
    @ApiModelProperty(value = "主键")
    @DefaultPrimaryKey
    private String maintenanceId;

    /**
     * 设备Id(外键,关联设备资产管理台账)
     */
    @Conversion(saveColumn = "tea_id", saveProperties = "tbEquipmentAssetsId",byTable="tb_equipment_assets", showColumn = "equipment_code", showProperties ="equipmentCode" )
    @ApiModelProperty(value = "设备Id(外键,关联设备资产管理台账)")
    private String tbEquipmentAssetsId;

    @Excel(name="设备编号")
    @Transient
    private String equipmentCode;


    /**
     * 设备基础信息(外键，关联设备基础资料表)
     */
    @Conversion(saveColumn = "teps_id", saveProperties = "tbEquipmentParamId",byTable="tb_equipment_param", showColumn = "module_unit", showProperties ="moduleUnit" )
    @ApiModelProperty(value = "设备基础信息(外键，关联设备基础资料表)")
    private String tbEquipmentParamId;

    @Excel(name="设备基础模块")
    @Transient
    private String moduleUnit;


    /**
     * 维护项目
     */
    @Excel(name = "维护项目")
    @ApiModelProperty(value = "维护项目")
    private String project;

    /**
     * 维护标准
     */
    @Excel(name = "维护标准")
    @ApiModelProperty(value = "维护标准")
    private String standard;

    /**
     * 维护方法
     */
    @Excel(name = "维护方法")
    @ApiModelProperty(value = "维护方法")
    private String method;

    /**
     * 维护周期
     */
    @Excel(name = "维护周期")
    @ApiModelProperty(value = "维护周期")
    private String week;

    /**
     * 维护责任人(外键，关联设备管理人员信息清单)
     */
    @Excel(name = "维护责任人")
    @ApiModelProperty(value = "维护责任人(外键，关联设备管理人员信息清单)")
    private String teiId;

    /**
     * 维护人数
     */
    @Excel(name = "维护人数")
    @ApiModelProperty(value = "维护人数")
    private Integer peopleNum;

    /**
     * 维护工时
     */
    @Excel(name = "维护工时")
    @ApiModelProperty(value = "维护工时")
    private Integer hour;

    /**
     * 维护工具
     */
    @Excel(name = "维护工具")
    @ApiModelProperty(value = "维护工具")
    private String tool;

    /**
     * 维护动能需求
     */
    @Excel(name = "维护动能需求")
    @ApiModelProperty(value = "维护动能需求")
    private String demand;

    /**
     * 是否需停机实施(字典)
     */
    @Excel(name = "是否需停机实施")
    @ApiModelProperty(value = "是否需停机实施(字典)")
    private Integer isDown;

    /**
     * 是否需操作工支持(字典)
     */
    @Excel(name = "是否需操作工支持")
    @ApiModelProperty(value = "是否需操作工支持(字典)")
    private Integer isMaker;

    /**
     * 是否为维护关键设备(字典)
     */
    @Excel(name = "是否为维护关键设备")
    @ApiModelProperty(value = "是否为维护关键设备(字典)")
    private Integer isEquipmentParts;

    /**
     * 备注
     */
    @Excel(name = "备注")
    @ApiModelProperty(value = "备注")
    private String remarks;

    @Excel(name = "是否需要备件")
    @ApiModelProperty(value = "是否需要备件 0-是 1-否")
    private Integer isNeedMaterial;

    @Excel(name = "开始时间")
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @Transient
    @ApiModelProperty(value = "预防维护和关联关系集合")
    private List<A07TbEquipmentMaintenanceMaterialRelation> relationList;


    @ApiModelProperty(value = "维护标准编号")
    private String maintenanceCode;

    private static final long serialVersionUID = 1L;
}
