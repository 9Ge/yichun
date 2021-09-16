package com.enercomn.web.A10CheckPlan.bean;

import com.enercomn.web.baseManager.model.ModelCommonProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

;

/**
 * @Date: 2021/8/16 10:38<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */

@Data
@ApiModel
public class A10TbEquipmentCheckPlanVo  implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private String tecpId;


    /**
     * 设备Id(外键，关联设备资产台账管理表)
     */
    @ApiModelProperty("资产设备Id(外键，关联设备资产台账管理表)")
    private String equipmentId;

    /**
     * 维修申请单id(关联维修申请单表)
     */
    @ApiModelProperty("维修申请单id(关联维修申请单表)")
    private String traId;

    @ApiModelProperty("设备基础信息(外键，关联设备基础资料表)")
    private String tbEquipmentParamId;

    /**
     * 所在位置
     */
    @ApiModelProperty("所在位置")
    private String place;

    /**
     * 类别(字典)
     */
    @ApiModelProperty("类别(字典)")
    private Integer type;

    /**
     * 部位
     */
    @ApiModelProperty("部位")
    private String position;

    /**
     * 检修主要内容
     */
    @ApiModelProperty("检修主要内容")
    private String checkContent;

    /**
     * 安全注意事项
     */
    @ApiModelProperty("主键")
    private String safetyPrecautions;

    /**
     * 需求工具
     */
    @ApiModelProperty("需求工具")
    private String demandTool;

    /**
     * 需求动能
     */
    @ApiModelProperty("需求动能")
    private String demandKineticEnergy;

    /**
     * 需求停机
     */
    @ApiModelProperty("需求停机")
    private String demandDown;

    /**
     * 需求操作工支持
     */
    @ApiModelProperty("需求操作工支持")
    private String dolSupport;

    /**
     * 计划日期
     */
    @ApiModelProperty("计划日期")
    private Date planDate;

    /**
     * 计划来源
     */
    @ApiModelProperty("计划来源")
    private String planFrom;

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("设备编号")
    private String equipmentCode;

    @ApiModelProperty("设备名称")
    private String equipmentName;

    @ApiModelProperty("模块/单元")
    private String moduleUnit;

    @ApiModelProperty("维护部位")
    private String maintenanceParts;

    @ApiModelProperty("维护项目")
    private String maintenanceProjects;

    private static final long serialVersionUID = 1L;


    @ApiModelProperty("来源编码")
    private String planFromCode;

    @ApiModelProperty("计划编号")
    private String planCode;
}
