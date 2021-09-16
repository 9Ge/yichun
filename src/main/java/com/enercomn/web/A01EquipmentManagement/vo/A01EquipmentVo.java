package com.enercomn.web.A01EquipmentManagement.vo;

import lombok.Data;

import java.util.List;


/**
 * @author yangcheng
 */
@Data
public class A01EquipmentVo {
    private String equipmentCode;
    private String equipmentName;
    private String equipmentModel;
    private String department;
    private String installaSit;

    private List<A01MaterialVo> a01MaterialVoList;

}