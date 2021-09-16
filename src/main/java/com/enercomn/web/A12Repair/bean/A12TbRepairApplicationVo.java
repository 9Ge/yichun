package com.enercomn.web.A12Repair.bean;

import com.enercomn.web.baseManager.model.ModelCommonProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * tb_repair_application
 * @author yangcheng
 */
@ApiModel
@Data
public class A12TbRepairApplicationVo implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private String traId;

    /**
     * 设备id(外键，关联设备资产台账管理表)
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
    @ApiModelProperty("状态跟踪")
    private String status;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;

    @ApiModelProperty("设备编号")
    private String equipmentCode;

    @ApiModelProperty("设备名称")
    private String equipmentName;

    @ApiModelProperty("维修申请编号")
    private String repairCode;
    private static final long serialVersionUID = 1L;
}