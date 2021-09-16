package com.enercomn.web.A05OPLClass.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.enercomn.web.A99TableExcelCommon.anno.DefaultPrimaryKey;
import com.enercomn.web.baseManager.model.ModelCommonProperties;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * @author yangcheng
 */
@Data
@ApiModel
@Table(name = "tb_opl_class")
public class A05TbOplClass extends ModelCommonProperties {
    /**
     * opl单点课
     */
    @Id
    @Column(name = "toc_id")
    @ApiModelProperty("opl单点课Id")
    @DefaultPrimaryKey
    private String tocId;

    /**
     * 主题
     */
    @Excel(name = "主题")
    @ApiModelProperty("主题")
    private String theme;

    /**
     * 部门
     */
    @Excel(name = "主题")
    @ApiModelProperty("部门")
    private String department;

    /**
     * 内容
     */
    @Excel(name = "内容")
    @ApiModelProperty("内容 1-安全 2-点检 3-5S 4-质量 5-维修 6-保养 7-操作 8-其他")
    private Integer category;

    /**
     * 备注1
     */
    @Excel(name = "备注1")
    @ApiModelProperty("备注1")
    private String content;

    @ApiModelProperty("内容图片")
    private String categoryPic;

    /**
     * 编写单位
     */
    @Excel(name = "备注1")
    @ApiModelProperty(value = "编写单位",hidden = true)
    private String editorUnit;

    /**
     * 编写人id
     */
    @ApiModelProperty(value = "编写人id",hidden = true)
    private String editor;

    /**
     * 编辑日期
     */
    @Excel(name = "编辑日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "编辑日期",hidden = true)
    private Date editDate;

    /**
     * 审核人id
     */
    @ApiModelProperty(value = "审核人id",hidden = true)
    private String reviewer;

    /**
     * 备注
     */
    @Excel(name = "说明")
    @ApiModelProperty("说明")
    private String remark;

    /**
     * 学习记录集合
     */
    @Transient
    @ApiModelProperty("学习记录集合")
    private List<A05TbOplTrainRecords> recordsList;

    private static final long serialVersionUID = 1L;
}