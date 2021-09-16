package com.enercomn.web.A00_dict.bean;

import com.enercomn.web.baseManager.model.ModelCommonProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel
@Table(name="tb_dict")
public class A00TbDict extends ModelCommonProperties implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name="dict_id")
    @ApiModelProperty(value = "主键")
    private String dictId;

    /**
     * 下拉框类型码
     */
    @ApiModelProperty(value = "下拉框类型码")
    private String baseCode;

    /**
     * 下拉框类型名称
     */
    @ApiModelProperty(value = "下拉框类型名称")
    private String baseName;

    /**
     * 下拉框Key
     */
    @ApiModelProperty(value = "下拉框Key")
    private String key;

    /**
     * 下拉框Value
     */
    @ApiModelProperty(value = "下拉框Value")
    private String value;



    private static final long serialVersionUID = 1L;
}