package com.enercomn.web.A11Failure.bean;

import com.enercomn.web.baseManager.model.ModelCommonProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @Date: 2021/8/16 11:34<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@ApiModel
@Data
@Table(name = "tb_equipment_failure_detail")
public class A11TbEquipmentFailureDetail extends ModelCommonProperties {

    /**
     * 主键
     */
    @Id
    @Column(name = "tefd_id")
    @ApiModelProperty("主键")
    private String tefdId;

    /**
     * 设备Id(外键，关联设备资产管理台账表)
     */
    @ApiModelProperty("设备Id(外键，关联设备资产管理台账表)")
    private String equipmentId;

    /**
     * 故障描述
     */
    @ApiModelProperty("故障描述")
    private String failureDetail;

    /**
     * 维修措施
     */
    @ApiModelProperty("维修措施")
    private String maintenanceMeasures;

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
     * 故障是否解决(字典)
     */
    @ApiModelProperty("故障是否解决(字典)")
    private Integer isSolve;

    /**
     * 维修人员(外键,关联设备管理人员信息清单表)
     */
    @ApiModelProperty("维修人员(外键,关联设备管理人员信息清单表)")
    private String teiId;

    /**
     * 备件消耗
     */
    @ApiModelProperty("备件消耗")
    private String partsConsume;

    /**
     * 工时消耗
     */
    @ApiModelProperty("工时消耗")
    private Integer hourConsume;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;

    @Transient
    @ApiModelProperty("设备故障报告单")
    List<A11TbEquipmentFailureReport> a11TbEquipmentFailureReportList;

    @Transient
    @ApiModelProperty("设备编号")
    private String equipmentCode;

    @ApiModelProperty("设备故障编号")
    private String failureCode;

    @Transient
    @ApiModelProperty("设备名称")
    private String equipmentName;

    @ApiModelProperty("内容图片")
    private String categoryPic;

    private static final long serialVersionUID = 1L;
}
