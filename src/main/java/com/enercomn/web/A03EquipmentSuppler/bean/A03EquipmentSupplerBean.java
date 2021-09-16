package com.enercomn.web.A03EquipmentSuppler.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.enercomn.web.A99TableExcelCommon.anno.Conversion;
import com.enercomn.web.A99TableExcelCommon.anno.DefaultPrimaryKey;
import com.enercomn.web.baseManager.model.ModelCommonProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Table(name = "tb_equipment_suppler")
public class A03EquipmentSupplerBean extends ModelCommonProperties {

    /**
     *供应商名字
     */

    @Excel(name = "供应商名字")
    private String supplerName;

    /**
     *主键
     */
    @DefaultPrimaryKey
    @Id
    @Column(name = "tes_id")//指定自增策略
    private String tesId;


    @Excel(name = "项目名称")
    private String name;

    @Conversion(saveColumn = "tea_id", saveProperties = "equipmentId",byTable="tb_equipment_assets", showColumn = "equipment_code", showProperties ="equipmentCode" )
    private String equipmentId;
    /**
     *供应商名字
     */
    @ApiModelProperty(value="联系人姓名")
    @Excel(name = "联系人姓名")
    private String telName;
    /**
     *供应商名字
     */
    @Excel(name = "联系人电话")
    @ApiModelProperty(value="电话")
    private String tel;



    @Excel(name = "联系人邮箱")
    private String email;

    private String remarks;

    @Excel(name = "设备编号")
    @Transient
    @ApiModelProperty(value="设备编号[用作查看，新增编辑不需要操作]")
    private String equipmentCode;

    @Transient
    @ApiModelProperty(value="设备名称[用作查看，新增编辑不需要操作]")
    private String equipmentName;

}
