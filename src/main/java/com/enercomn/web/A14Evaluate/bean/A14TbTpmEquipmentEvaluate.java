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
 * tb_tpm_equipment_evaluate
 * @author 
 */
@Data
@ApiModel
@Table(name = "tb_tpm_equipment_evaluate")
public class A14TbTpmEquipmentEvaluate extends ModelCommonProperties implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "ttee_id")
    @ApiModelProperty("主键")
    private String tteeId;

    /**
     * 车间
     */
    @ApiModelProperty("车间")
    private String workshop;

    /**
     * TPM入阶整体评价
     */
    @ApiModelProperty("TPM入阶整体评价")
    private String tmpEvaluate;

    /**
     * 专业开展评价
     */
    @ApiModelProperty("专业开展评价")
    private String majorEvaluate;

    /**
     * 自主开展评价
     */
    @ApiModelProperty("自主开展评价")
    private String autonomyEvaluate;

    /**
     * OPL开展整体评价
     */
    @ApiModelProperty("OPL开展整体评价")
    private String oplEvaluate;

    /**
     * 点检、保养工作评价
     */
    @ApiModelProperty("点检、保养工作评价")
    private String checkEvaluate;

    /**
     * 车间评价得分
     */
    @ApiModelProperty("车间评价得分")
    private Double evaluateNum;

    /**
     * 评价时间
     */
    @ApiModelProperty("评价时间")
    private Date evaluateDate;

    private static final long serialVersionUID = 1L;
}
