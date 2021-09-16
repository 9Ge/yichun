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
import java.util.Date;


/**
 * tb_repair_application
 * @author yangcheng
 */
@ApiModel
@Data
@Table(name = "tb_repair_application")
public class A12TbRepairApplication extends ModelCommonProperties implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "tra_id")
    @ApiModelProperty("主键")
    private String traId;

    /**
     * 设备Id(外键，关联设备资产台账管理表)
     */
    @ApiModelProperty("设备id(外键，关联设备资产台账管理表)")
    private String equipmentId;

    /**
     * 设备现状
     */
    @ApiModelProperty("设备现状")
    private String equipmentStatus;

    /**
     * 维修目的
     */
    @ApiModelProperty("维修目的")
    private String repairObjective;

    /**
     * 申请人
     */
    @ApiModelProperty("申请人")
    private String applicant;

    /**
     * 状态跟踪
     */
    @ApiModelProperty("状态跟踪 已申请 已申请未完成 已申请已完成")
    private String status;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;

    @Transient
    @ApiModelProperty("设备编号")
    private String equipmentCode;

    @ApiModelProperty("维修申请编号")
    private String repairCode;

    @Transient
    @ApiModelProperty("设备名称")
    private String equipmentName;

    private static final long serialVersionUID = 1L;
}