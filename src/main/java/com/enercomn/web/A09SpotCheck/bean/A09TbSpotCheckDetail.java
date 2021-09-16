package com.enercomn.web.A09SpotCheck.bean;

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
 * tb_spot_check_detail
 * @author 
 */
@Data
@ApiModel
@Table(name = "tb_spot_check_detail")
public class A09TbSpotCheckDetail extends ModelCommonProperties implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name="tscd_id")
    @ApiModelProperty(value = "主键")
    private String tscdId;

    /**
     * 点检日期
     */
    @ApiModelProperty(value = "点检日期")
    private Date checkDate;

    /**
     * 是否发现异常(字典)
     */
    @ApiModelProperty(value = "是否发现异常(字典)")
    private Integer isFind;

    /**
     * 异常发现日期
     */
    @ApiModelProperty(value = "异常发现日期")
    private Date abnormalDate;

    /**
     * 异常发现人
     */
    @ApiModelProperty(value = "异常发现人")
    private String abnormalPerson;

    /**
     * 异常问题内容
     */
    @ApiModelProperty(value = "异常问题内容")
    private String abnormalContent;

    /**
     * 异常问题处理过程及内容
     */
    @ApiModelProperty(value = "异常问题处理过程及内容")
    private String handleContent;

    /**
     * 异常处理实施人
     */
    @ApiModelProperty(value = "异常处理实施人")
    private String handlePerson;

    /**
     * 异常处理实施日期
     */
    @ApiModelProperty(value = "异常处理实施日期")
    private Date handleDate;

    /**
     * 外键(关联，设备点检检录表)
     */
    @ApiModelProperty(value = "外键(关联，设备点检检录表)")
    private String tscId;


    private static final long serialVersionUID = 1L;
}
