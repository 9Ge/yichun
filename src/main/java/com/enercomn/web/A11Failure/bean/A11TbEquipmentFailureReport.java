package com.enercomn.web.A11Failure.bean;

import com.enercomn.web.baseManager.model.ModelCommonProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * tb_equipment_failure_report
 * @author 
 */
@Data
@ApiModel
@Table(name = "tb_equipment_failure_report")
public class A11TbEquipmentFailureReport extends ModelCommonProperties implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "tefr_id")
    @ApiModelProperty("主键")
    private String tefrId;

    @ApiModelProperty("故障清单Id(外键，关联设备故障清单记录)")
    private String tefdId;
    /**
     * 设备Id(外键，关联设备资产台账管理)
     */
    @ApiModelProperty("设备Id(外键，关联设备资产台账管理)")
    private String equipmentId;

    /**
     * 故障现象
     */
    @ApiModelProperty("故障现象")
    private String failureContent;

    /**
     * 报告分析单位
     */
    @ApiModelProperty("报告分析单位")
    private String reportUnit;

    /**
     * 故障发生日期
     */
    @ApiModelProperty("故障发生日期")
    private Date failureDate;

    /**
     * 故障部位
     */
    @ApiModelProperty("故障部位")
    private String failurePosition;

    /**
     * 影响生产线
     */
    @ApiModelProperty("影响生产线")
    private String line;

    /**
     * 维修工/技师
     */
    @ApiModelProperty("维修工/技师")
    private String handler;

    /**
     * 责任设备工程师
     */
    @ApiModelProperty("任设备工程师")
    private String equipmentEngineer;

    /**
     * 设备故障时间段
     */
    @ApiModelProperty("设备故障时间段")
    private String failureDateSlot;

    /**
     * 生产线停线时间
     */
    @ApiModelProperty("生产线停线时间")
    private Date downDate;

    /**
     * 故障修复措施
     */
    @ApiModelProperty("故障修复措施")
    private String failureMeasures;

    /**
     * 修复实施结果
     */
    @ApiModelProperty("修复实施结果")
    private String result;

    /**
     * 修理过程图解
     */
    @ApiModelProperty("修理过程图解")
    private String imgurl;

    /**
     * 原因分析
     */
    @ApiModelProperty("原因分析")
    private String reason;

    /**
     * 预防措施
     */
    @ApiModelProperty("预防措施")
    private String preventiveMeasure;

    /**
     * 故障修复完成时间
     */
    @ApiModelProperty("故障修复完成时间")
    private Date endDate;


    private static final long serialVersionUID = 1L;
}
