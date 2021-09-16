package com.enercomn.web.A01EquipmentManagement.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @Date: 2021/8/17 15:47<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@ApiModel
@Data
public class A01EquipmentAssetsVo {

    @ApiModelProperty(value="设备主键")
    private  String  teaId;
    /**
     *#{item.
     */
    @ApiModelProperty(value="设备编号")
    private   String equipmentCode;
    /**
     *设备名称
     */
    @ApiModelProperty(value="设备名称")
    private   String equipmentName;
}
