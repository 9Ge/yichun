package com.enercomn.web.A08EquipmentParam.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
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

/**
 * tb_equipment_parm
 * @author 
 */
@Data
@ApiModel
@Table(name = "tb_equipment_param")
public class A08TbEquipmentParam extends ModelCommonProperties implements Serializable {
    /**
     * 主键
     */
    @DefaultPrimaryKey
    @Id
    @Column(name = "teps_id")
    @ApiModelProperty(value = "主键")
    private String tepsId;

    /**
     * 外键(关联设备资产管理台账表)
     */
    @ApiModelProperty(value = "外键(关联设备资产管理台账表)")
    @Conversion(saveColumn = "tea_id", saveProperties = "tbEquipmentAssetsId",byTable="tb_equipment_assets", showColumn = "equipment_code", showProperties ="equipmentCode" )
    private String tbEquipmentAssetsId;

    /**
     * 模块/单元
     */
    @ApiModelProperty(value = "模块/单元")
    private String moduleUnit;

    /**
     * 维护部位
     */
    @ApiModelProperty(value = "维护部位")
    private String maintenanceParts;

    /**
     * 维护项目
     */
    @ApiModelProperty(value = "维护项目")
    private String maintenanceProjects;

    /**
     * 参数内容
     */
    @ApiModelProperty(value = "参数内容")
    private String paramContent;

    /**
     * 设备参数
     */
    @ApiModelProperty(value = "设备参数")
    private String equipmentParam;

    /**
     * 工艺参数
     */
    @ApiModelProperty(value = "工艺参数")
    private String technologyParam;

    /**
     * 是否周期校正检测
     */
    @ApiModelProperty(value = "是否周期校正检测")
    private String isCheck;

    /**
     * 校正检验记录
     */
    @ApiModelProperty(value = "校正检验记录")
    private String checkRecords;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;

    @Excel(name="设备编号")
    @Transient
    @ApiModelProperty(value = "设备编号[只做展示使用，修改和新增不用操作]")
    private String equipmentCode;

}