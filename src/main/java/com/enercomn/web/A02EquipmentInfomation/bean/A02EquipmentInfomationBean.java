package com.enercomn.web.A02EquipmentInfomation.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.enercomn.web.A99TableExcelCommon.anno.DefaultPrimaryKey;
import com.enercomn.web.A99TableExcelCommon.anno.DictCodeProperties;
import com.enercomn.web.baseManager.model.ModelCommonProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_equipment_information")
public class A02EquipmentInfomationBean extends ModelCommonProperties {
    /**
     * 主键
     */
    @Id
    @Column(name = "tei_id")//指定自增策略
    @DefaultPrimaryKey
    private String teiId;
    /**
     * 工号
     */
    @Excel(name = "工号")
    private String jobNo;
    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String name;
    /**
     * 性别
     */
    @Excel(name = "性别")
    private String sex;
    /**
     * 工作年限
     */
    @Excel(name = "工作年限")
    private Integer workYears;
    /**
     * 岗位序列
     */
    @DictCodeProperties
    @Excel(name = "岗位序列")
    private String sequence;

    /**
     * 联系方式
     */
    @Excel(name = "联系方式")
    private String tel;
    /**
     * 岗位
     */
    @Excel(name = "岗位")
    private String post;

    @Excel(name = "用户分组")
    private String userGroup;

    @Excel(name = "用户密码")
    private String userPwd;
    /**
     * 车间
     */
    @Excel(name = "车间")
    private String workShop;
    /**
     * 紧急联系人
     */
    @Excel(name = "紧急联系人")
    private String people;
    /**
     * 紧急联系方式
     */
    @Excel(name = "紧急联系方式")
    private String telBack;
    /**
     * 联系邮箱
     */
    @Excel(name = "联系邮箱")
    private String email;
    /**
     * 第一学历
     */
    @Excel(name = "第一学历")
    private String oneDegree;
    /**
     * 毕业学校
     */
    @Excel(name = "毕业学校")
    private String graduationSchool;
    /**
     * 第一学历专业
     */
    @Excel(name = "第一学历专业")
    private String degreeMajor;
    /**
     * 专长
     */
    @Excel(name = "专长")
    private String major;
    /**
     * 职称信息
     */
    @Excel(name = "职称信息")
    private String titleInfor;
    /**
     * 技能等级
     */
    @Excel(name = "技能等级")
    private String skillLevel;
    /**
     * 资格证书
     */
    @Excel(name = "资格证书")
    private String qualification;
    /**
     * 短袖衬衫尺码
     */
    @Excel(name = "短袖衬衫尺码")
    private String shortShirtSize;
    /**
     * 长袖衬衫尺码
     */
    @Excel(name = "长袖衬衫尺码")
    private String longShirtSize;
    /**
     * 短袖POLO衫尺码
     */
    @Excel(name = "短袖POLO衫尺码")
    private String shortPoloSize;
    /**
     * 长袖POLO衫尺码
     */
    @Excel(name = "长袖POLO衫尺码")
    private String longPoloSize;
    /**
     * 夹克尺码
     */
    @Excel(name = "夹克尺码")
    private String jacketSize;
    /**
     * 工装外套尺码
     */
    @Excel(name = "工装外套尺码")
    private String frockcoatSize;
    /**
     * 工装裤春秋款尺码
     */
    @Excel(name = "工装裤春秋款尺码")
    private String springautumnOverallsSize;
    /**
     * 工装裤夏款尺码
     */
    @Excel(name = "工装裤夏款尺码")
    private String summerOverallsSize;
    /**
     * 工装外套冬款尺码
     */
    @Excel(name = "工装外套冬款尺码")
    private String winterFrockcoatSize;
    /**
     * 工装需备注防阻燃或防静电(字典)
     */
    @Excel(name = "工装备注")
    private String clothesRemarks;
    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;

    @ApiModelProperty("用户权限")
    private String role;
}
