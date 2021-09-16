package com.enercomn.web.A01EquipmentManagement.bean;


import cn.afterturn.easypoi.excel.annotation.Excel;
import com.enercomn.web.A99TableExcelCommon.anno.DefaultPrimaryKey;
import com.enercomn.web.A99TableExcelCommon.anno.DictCodeProperties;
import com.enercomn.web.A99TableExcelCommon.anno.UniqueProperties;
import com.enercomn.web.baseManager.model.ModelCommonProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "tb_equipment_assets")
public class A01EquipmentAssetsBean extends ModelCommonProperties {


    /**
     * 主键
     */
    @Id
    @Column(name = "tea_id")//指定自增策略
    @DefaultPrimaryKey
    private String teaId;

    /**
     * #{item.
     */
    @Excel(name = "设备编号")
    @ApiModelProperty(value = "设备编号")
    @NotNull(message =  "设备编号不能为空")
    @UniqueProperties
    private String equipmentCode;
    /**
     * 设备名称
     */
    @Excel(name = "设备名称")
    @ApiModelProperty(value = "设备名称")
    @NotNull(message =  "设备名称不能为空")
    private String equipmentName;
    /**
     * 设备规格型号
     */
    @Excel(name = "设备规格型号")
    @ApiModelProperty(value = "设备规格型号")
    private String equipmentModel;
    /**
     * 使用部门
     */
    @Excel(name = "使用部门")
    @ApiModelProperty(value = "使用部门")
    private String department;
    /**
     * 安装地点
     */
    @Excel(name = "安装地点")
    @ApiModelProperty(value = "安装地点")
    private String installaSite;
    /**
     * 生产线
     */
    @Excel(name = "生产线")
    @ApiModelProperty(value = "生产线")
    private String productionLine;
    /**
     * 序号
     */
    @Excel(name = "序号")
    @ApiModelProperty(value = "序号")
    private String equipmentNo;
    /**
     * 制造厂(国)
     */
    @Excel(name = "制造厂(国)")
    @ApiModelProperty(value = "制造厂(国)")
    private String manufacture;
    /**
     * 出厂编号
     */
    @Excel(name = "出厂编号")
    private String factoryNo;
    /**
     * 出厂年月
     */
    @Excel(name = "出厂年月",format = "yyyy-MM-dd")
    private Date factoryDate;
    /**
     * 设备供应商
     */
    @Excel(name = "设备供应商")
    private String equipmentSuppler;
    /**
     * 重量(吨)
     */
    @Excel(name = "重量(吨)")
    private double weight;
    /**
     * 功率(kw)
     */
    @Excel(name = "功率(kw)")
    private double power;
    /**
     * 设备类别
     */
    @DictCodeProperties
    @Excel(name = "设备类别")
    private String equipmentType;

    /**
     * 使用状态
     */
    @DictCodeProperties
    @Excel(name = "使用状态")
    private String useState;

    /**
     * 履历信息
     */
    private String resumeInfo;
    /**
     * 使用日期
     */
    @Excel(name = "使用日期", importFormat = "yyyy-MM-dd")
    private Date useDate;
    /**
     * 合同编号
     */
    @Excel(name = "合同编号")
    private String contractNo;
    /**
     * 合同名称
     */
    @Excel(name = "合同名称")
    private String contractName;
    /**
     * 备注
     */
    private String remarks;

    @Transient
    @ApiModelProperty("物料关联关系表")
    private List<A01TbEquipmentMaterialRelation> relationList;
}
