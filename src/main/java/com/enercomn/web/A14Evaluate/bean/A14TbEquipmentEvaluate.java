package com.enercomn.web.A14Evaluate.bean;

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
 * tb_equipment_evaluate
 * @author 
 */
@ApiModel
@Data
@Table(name = "tb_equipment_evaluate")
public class A14TbEquipmentEvaluate extends ModelCommonProperties implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "tee_id")
    @ApiModelProperty("主键")
    private String teeId;


    @ApiModelProperty("车间")
    private String workshop;
    /**
     * 设备TPM管理综合评价
     */
    @ApiModelProperty("设备TPM管理综合评价")
    private String comprehensiveEvaluate;

    /**
     * 快速维修反应评价
     */
    @ApiModelProperty("快速维修反应评价")
    private String repairEvaluate;

    /**
     * 设备管理现场目视化评价
     */
    @ApiModelProperty("设备管理现场目视化评价")
    private String visualizationEvaluate;

    /**
     * 设备维修人员技能达标率评价
     */
    @ApiModelProperty("设备维修人员技能达标率评价")
    private String complianceRateEvaluate;

    /**
     * 设备管理指标完成评价
     */
    @ApiModelProperty("设备管理指标完成评价")
    private String indexEvaluate;

    /**
     * 评价总得分
     */
    @ApiModelProperty("评价总得分")
    private Double evaluateNum;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;

    /**
     * 评价时间
     */
    @ApiModelProperty("评价时间")
    private Date evaluateDate;

    /**
     * 外键，关联设备资产台账管理
     */
    @ApiModelProperty("外键，关联设备资产台账管理")
    private String equipmentId;

    private static final long serialVersionUID = 1L;
}
