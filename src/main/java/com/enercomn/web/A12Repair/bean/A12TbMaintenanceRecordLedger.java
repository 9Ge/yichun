package com.enercomn.web.A12Repair.bean;

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


/**
 * tb_maintenance_record_ledger
 * @author yangcheng
 */
@ApiModel
@Data
@Table(name = "tb_maintenance_record_ledger")
public class A12TbMaintenanceRecordLedger extends ModelCommonProperties implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "tmrl_id")
    @ApiModelProperty("主键")
    private String tmrlId;

    /**
     * 设备备件卡id(外键，关联设备备件卡表)
     */
    @ApiModelProperty("设备备件卡id(外键，关联设备备件卡表)")
    private String tepId;


    @ApiModelProperty("设备备件数量")
    private BigDecimal materialNum;

    /**
     * 设备履历表id(外键，关联设备履历表id)
     */
    @ApiModelProperty("设备履历表id(外键，关联设备履历表id)")
    private String terId;

    /**
     * 设备Id(外键，关联设备资产台账管理表)
     */
    @ApiModelProperty("设备Id(外键，关联设备资产台账管理表)")
    private String equipmentId;

    /**
     * 日期
     */
    @ApiModelProperty("日期")
    private Date date;

    /**
     * 维修内容
     */
    @ApiModelProperty("维修内容")
    private String content;

    @ApiModelProperty("维修内容图片")
    private String contentPic;


    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private Date dateBegin;
    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private Date dateEnd;

    /**
     * 工时
     */
    @ApiModelProperty("工时")
    private Integer hours;

    /**
     * 维修人员
     */
    @ApiModelProperty("维修人员")
    private String repairPersonnel;

    /**
     * 维修来源
     */
    @ApiModelProperty("维修来源")
    private String maintenanceSource;

    @ApiModelProperty("维修来源Id")
    private String maintenanceSourceId;

    /**
     * 维修班次
     */
    @ApiModelProperty("维修班次")
    private String maintenanceNumber;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;

    @Transient
    @ApiModelProperty("设备编号")
    private String equipmentCode;

    @Transient
    @ApiModelProperty("设备名称")
    private String equipmentName;

    @Transient
    @ApiModelProperty("设备型号")
    private String equipmentModel;

    @Transient
    @ApiModelProperty("备件编码")
    private String code;

    @Transient
    @ApiModelProperty("备件名称")
    private String name;

    @Transient
    @ApiModelProperty("备件规格型号")
    private String modelNo;

    @ApiModelProperty("维修来源编号")
    private String sourceCode;
    @ApiModelProperty("维修记录编号")
    private String recordLedgerCode;
    private static final long serialVersionUID = 1L;
}