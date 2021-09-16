package com.enercomn.web.A13PartsDeclare.bean;

import com.enercomn.web.baseManager.model.ModelCommonProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * tb_equipment_parts_declare
 * @author 
 */
@Data
@ApiModel
@Table(name = "tb_equipment_parts_declare")
public class A13TbEquipmentPartsDeclare extends ModelCommonProperties implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "tepd_id")
    @ApiModelProperty("主键")
    private String tepdId;

    /**
     * 系统物料号
     */
    @ApiModelProperty("系统物料号")
    private String systemMaterialNumber;

    /**
     * 名称型号
     */
    @ApiModelProperty("名称型号")
    private String nameModel;

    /**
     * 采购量
     */
    @ApiModelProperty("采购量")
    private Long purchaseNum;

    /**
     * 单位
     */
    @ApiModelProperty("单位")
    private String company;

    /**
     * 申报部门
     */
    @ApiModelProperty("申报部门")
    private String declarationDepartment;

    /**
     * 原始申报人
     */
    @ApiModelProperty("原始申报人")
    private String originalApplicant;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String tel;

    /**
     * OA流程时间
     */
    @ApiModelProperty("OA流程时间")
    private Date processDate;

    /**
     * 建议供应商
     */
    @ApiModelProperty("建议供应商")
    private String suppler;

    /**
     * 备件品牌
     */
    @ApiModelProperty("备件品牌")
    private String equipmentPartsBrand;


    /**
     * 所用部位
     */
    @ApiModelProperty("所用部位")
    private String useParts;

    /**
     * 最高存量
     */
    @ApiModelProperty("最高存量")
    private Long maxStock;

    /**
     * 最低存量
     */
    @ApiModelProperty("最低存量")
    private Long minStock;


    /**
     * 实际到货期
     */
    @ApiModelProperty("实际到货期")
    private Date loadDate;

    /**
     * 验收情况及问题
     */
    @ApiModelProperty("验收情况及问题")
    private String acceptanceProblems;

    /**
     * 工厂
     */
    @ApiModelProperty("工厂")
    private String factory;

    /**
     * 库位
     */
    @ApiModelProperty("库位")
    private String location;

    /**
     * 急用情况详细说明
     */
    @ApiModelProperty("急用情况详细说明")
    private String situationExplain;

    /**
     * 理想到货期
     */
    @ApiModelProperty("理想到货期")
    private Date estimateDate;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;

    /**
     * 申请类别(字典)
     */
    @ApiModelProperty("申请类别(字典)")
    private String applyType;

    /**
     * 采购申请号
     */
    @ApiModelProperty("采购申请号")
    private String purchaseApplyNo;

    /**
     * 采购员
     */
    @ApiModelProperty("采购员")
    private String purchaser;

    /**
     * 预期消耗
     */
    @ApiModelProperty("预期消耗")
    private Long estimateNum;

    /**
     * 备件员
     */
    @ApiModelProperty("备件员")
    private String partsMan;

    /**
     * 是否是新增备件(字典)
     */
    @ApiModelProperty("是否是新增备件(字典)")
    private String isNew;

    /**
     * 总分类
     */
    @ApiModelProperty("总分类")
    private String sumType;

    /**
     * 备件价格
     */
    @ApiModelProperty("备件价格")
    private BigDecimal partsPrice;

    /**
     * 总价
     */
    @ApiModelProperty("总价")
    private BigDecimal amount;

    private static final long serialVersionUID = 1L;
}
