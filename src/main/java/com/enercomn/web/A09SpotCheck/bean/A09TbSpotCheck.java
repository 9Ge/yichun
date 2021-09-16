package com.enercomn.web.A09SpotCheck.bean;

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
import java.util.Date;


/**
 * tb_spot_check
 * @author 
 */
@Data
@ApiModel
@Table(name = "tb_spot_check")
public class A09TbSpotCheck extends ModelCommonProperties implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "tsc_id")
    @ApiModelProperty(value = "主键")
    @DefaultPrimaryKey
    private String tscId;

    /**
     * 设备资产Id(外键，关联设备资产管理台账表)
     */
    @Conversion(saveColumn = "tea_id", saveProperties = "tbEquipmentAssetsId",byTable="tb_equipment_assets", showColumn = "equipment_code", showProperties ="equipmentCode" )
    @ApiModelProperty(value = "设备资产Id(外键，关联设备资产管理台账表)")
    private String tbEquipmentAssetsId;

    /**
     * 使用部门
     */
    @Excel(name = "使用部门")
    @ApiModelProperty(value = "使用部门")
    private String useDepartment;

    /**
     * 点检部位
     */
    @Excel(name = "点检部位")
    @ApiModelProperty(value = "点检部位")
    private String position;

    /**
     * 点检判断标准及要求
     */
    @Excel(name = "点检判断标准及要求")
    @ApiModelProperty(value = "点检判断标准及要求")
    private String checkRequirements;

    /**
     * 方法及工具
     */
    @Excel(name = "方法及工具")
    @ApiModelProperty(value = "方法及工具")
    private String methodsTool;

    /**
     * 班次
     */
    @Excel(name = "班次")
    @ApiModelProperty(value = "班次")
    private String workType;

    /**
     * 作业时间
     */
    @Excel(name = "作业时间")
    @ApiModelProperty(value = "作业时间")
    private Date date;

    @Excel(name = "点检状态")
    @ApiModelProperty(value = "点检状态 0-未点检 1-点检异常  2-已点检 3-已复核 4-再次复核")
    private String checkStatus;
    /**
     * 检点类型(字典)
     */
    @Excel(name = "检点类型")
    @ApiModelProperty(value = "检点类型(字典)")
    private Integer checkType;


    @Excel(name = "设备编号")
    @Transient
    @ApiModelProperty(value = "设备编号")
    private String equipmentCode;

    @Transient
    @ApiModelProperty(value = "设备名称")
    private String equipmentName;

    @Transient
    @ApiModelProperty(value = "设备型号")
    private String equipmentModel;


    @ApiModelProperty(value = "点检编号")
    private String checkCode;

    @ApiModelProperty(value = "来源编号")
    private String sourceId;

    @ApiModelProperty(value = "来源编号")
    private String sourceCode;

    private static final long serialVersionUID = 1L;
}