package com.enercomn.web.A04ToolAccount.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.enercomn.web.A99TableExcelCommon.anno.DefaultPrimaryKey;
import com.enercomn.web.baseManager.model.ModelCommonProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Table(name = "tb_tool_account")
public class A04ToolAccount extends ModelCommonProperties {

    @Id
    @Column(name = "tta_id")
    @DefaultPrimaryKey
    private String ttaId;

    @Excel(name = "工具编号")
    private String toolNo;

    @Excel(name = "用途")
    private String toolUse;

    @Excel(name = "工具名称")
    private String toolName;

    @Excel(name = "报废日期")
    private Date scrapDate;

    @Excel(name = "封存日期")
    private Date storageDate;

    @Excel(name = "数量")
    @NotNull(message = "数量不能空")
    @Min(value = 1,message = "数量必须大于等于1")
    private int num;

    @Excel(name = "工具是否需校验")
    private String isVerification;

    @Excel(name = "班组")
    private String team;

    @Excel(name = "工具分类")
    private String type;

    @Excel(name = "工位")
    private String station;

    @Excel(name = "责任人")
    private String userId;

    @Excel(name = "工具状态")
    private String toolStatus;

    @Excel(name = "车间")
    private String department;

    @Excel(name = "领用日期")
    private String useDate;

    @Excel(name = "备注")
    private String remark;

    @Excel(name = "校验内容")
    @ApiModelProperty(value="校验内容")
    private String verificationContent;

    @Excel(name = "校验周期")
    @ApiModelProperty(value="校验周期")
    private String verificationWeek;

    @Excel(name = "校验时间")
    @ApiModelProperty(value="校验时间")
    private String verificationDate;

    @Excel(name = "校验是否合格")
    @ApiModelProperty(value="校验是否合格")
    private String isPass;
}
