package com.enercomn.web.A10CheckPlan.bean;

 ;
 import cn.afterturn.easypoi.excel.annotation.Excel;
 import com.enercomn.web.A99TableExcelCommon.anno.Conversion;
 import com.enercomn.web.A99TableExcelCommon.anno.SQLInfo;
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
 * @Date: 2021/8/16 10:38<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */

@Data
@ApiModel
@Table(name="tb_equipment_check_plan")
public class A10TbEquipmentCheckPlan extends ModelCommonProperties implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @Id
    @Column(name = "tecp_id")
    private String tecpId;


    /**
     * 设备Id(外键,关联设备资产管理台账)
     */
    @Conversion(saveColumn = "tea_id", saveProperties = "equipmentId",byTable="tb_equipment_assets", showColumn = "equipment_code", showProperties ="equipmentCode" )
    @ApiModelProperty(value = "设备Id(外键,关联设备资产管理台账)")
    private String equipmentId;

    /**
     * 设备基础信息(外键，关联设备基础资料表)
     */
    @Conversion(saveColumn = "teps_id", saveProperties = "tbEquipmentParamId", byTable = "tb_equipment_param", showColumn = "module_unit", showProperties = "moduleUnit",saveFlag = true
            ,saveSql = @SQLInfo(sql = "select * from tb_equipment_param where module_unit=? and tb_equipment_assets_id=?",param = {"moduleUnit","equipmentId"}))
    @ApiModelProperty(value = "设备基础信息(外键，关联设备基础资料表)")
    private String tbEquipmentParamId;

    /**
     * 维修申请单id(关联维修申请单表)
     */
    @ApiModelProperty("维修申请单id(关联维修申请单表)")
    private String traId;

    /**
     * 所在位置
     */
    @Excel(name = "所在位置")
    @ApiModelProperty("所在位置")
    private String place;

    /**
     * 类别(字典)
     */
    @Excel(name = "类别")
    @ApiModelProperty("类别(字典)")
    private String type;

    /**
     * 部位
     */
    @Excel(name = "部位")
    @ApiModelProperty("部位")
    private String position;

    /**
     * 检修主要内容
     */
    @Excel(name = "检修主要内容")
    @ApiModelProperty("检修主要内容")
    private String checkContent;

    /**
     * 安全注意事项
     */
    @Excel(name = "安全注意事项")
    @ApiModelProperty("安全注意事项")
    private String safetyPrecautions;

    /**
     * 需求工具
     */
    @Excel(name = "需求工具")
    @ApiModelProperty("需求工具")
    private String demandTool;

    /**
     * 需求动能
     */
    @Excel(name = "需求动能")
    @ApiModelProperty("需求动能")
    private String demandKineticEnergy;

    /**
     * 需求停机
     */
    @Excel(name = "需求停机")
    @ApiModelProperty("需求停机")
    private String demandDown;

    /**
     * 需求操作工支持
     */
    @Excel(name = "需求操作工支持")
    @ApiModelProperty("需求操作工支持")
    private String dolSupport;

    /**
     * 计划日期
     */
    @Excel(name = "计划日期")
    @ApiModelProperty("计划日期")
    private Date planDate;

    /**
     * 计划来源
     */
    @ApiModelProperty("计划来源")
    private String planFrom;

    @ApiModelProperty(value = "计划来源Id",hidden = true)
    private String planFromId;

    /**
     * 状态
     */
    @Excel(name="状态")
    @ApiModelProperty("状态")
    private String status;

    @Excel(name="设备编号")
    @Transient
    @ApiModelProperty("设备编号")
    private String equipmentCode;

    @Transient
    @ApiModelProperty("设备名称")
    private String equipmentName;

    @Excel(name="设备基础模块")
    @Transient
    @ApiModelProperty("模块/单元")
    private String moduleUnit;

    @Transient
    @ApiModelProperty("维护部位")
    private String maintenanceParts;

    @Transient
    @ApiModelProperty("维护项目")
    private String maintenanceProjects;

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("来源编码")
    private String planFromCode;

    @ApiModelProperty("计划编号")
    private String planCode;
}
