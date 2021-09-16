package com.enercomn.web.A01EquipmentManagement.bean;

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
 * tb_equipment_resume
 * @author 
 */
@Data
@Table(name = "tb_equipment_resume")
@ApiModel
public class A01TbEquipmentResume extends ModelCommonProperties implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "ter_id")
    @ApiModelProperty("主键")
    private String terId;

    /**
     * 日期
     */
    @ApiModelProperty("日期")
    private Date date;

    /**
     * 维修单位
     */
    @ApiModelProperty("维修单位")
    private String maintenanceUnit;

    /**
     * 设备Id(外键，关联设备资产管理台账表)
     */
    @ApiModelProperty("设备Id(外键，关联设备资产管理台账表)")
    private String equipmentId;

    /**
     * 设备维修部位
     */
    @ApiModelProperty("设备维修部位")
    private String maintenancePart;

    /**
     * 维修类型(字典)
     */
    @ApiModelProperty("维修类型(字典)")
    private Integer type;

    /**
     * 维修开始时间
     */
    @ApiModelProperty("维修开始时间")
    private Date dateBegin;

    /**
     * 维修结束时间
     */
    @ApiModelProperty("维修结束时间")
    private Date dateEnd;

    /**
     * 维修时间
     */
    @ApiModelProperty("维修时间")
    private Integer hours;

    /**
     * 停机时间
     */
    @ApiModelProperty("停机时间")
    private Date downDate;

    /**
     * 故障现象
     */
    @ApiModelProperty("故障现象")
    private String faultPhenomenon;

    /**
     * 故障原因
     */
    @ApiModelProperty("故障原因")
    private String faultReason;

    /**
     * 维修过程
     */
    @ApiModelProperty("维修过程")
    private String maintenanceProcess;

    /**
     * 维修人员(外键，关联设备管理人员信息清单)
     */
    @ApiModelProperty("维修人员(外键，关联设备管理人员信息清单)")
    private String teiId;

    /**
     * 更换零件名称/型号
     */
    @ApiModelProperty("备件外键Id,(关联备件)")
    private String tepId;

    /**
     * 零件数量
     */
    @ApiModelProperty("备件数量")
    private Integer modelNum;

    @ApiModelProperty("履历来源")
    private String resumeSource;

    @ApiModelProperty("履历来源Id")
    private String resumeSourceId;

    @ApiModelProperty("履历来源编号")
    private String resumeSourceCode;


    @Transient
    @ApiModelProperty("设备编号")
    private String equipmentCode;

    @Transient
    @ApiModelProperty("设备名称")
    private String equipmentName;

    @Transient
    @ApiModelProperty("备件编号")
    private String materialCode;

    @Transient
    @ApiModelProperty("备件名称")
    private String materialName;

    private static final long serialVersionUID = 1L;
}