package com.enercomn.web.A99TableExcelCommon.constant;

import com.enercomn.web.A01EquipmentManagement.bean.A01EquipmentAssetsBean;
import com.enercomn.web.A01EquipmentManagement.mapper.A01EquipmentAsstsMapperTK;
import com.enercomn.web.A02EquipmentInfomation.bean.A02EquipmentInfomationBean;
import com.enercomn.web.A02EquipmentInfomation.mapper.A02EquipmentInfomationMapperTK;
import com.enercomn.web.A03EquipmentSuppler.bean.A03EquipmentSupplerBean;
import com.enercomn.web.A03EquipmentSuppler.mapper.A03EquipmentSupplerMapperTK;
import com.enercomn.web.A04ToolAccount.bean.A04ToolAccount;
import com.enercomn.web.A04ToolAccount.mapper.A04ToolAccountMapperTK;
import com.enercomn.web.A05OPLClass.bean.A05TbOplClass;
import com.enercomn.web.A05OPLClass.mapper.A05TbOplClassMapper;
import com.enercomn.web.A06EquipmentMaterial.bean.A06TbEquipmentMaterial;
import com.enercomn.web.A06EquipmentMaterial.mapper.A06TbEquipmentMaterialMapperTk;
import com.enercomn.web.A07EquipmentMaintenance.bean.A07TbEquipmentMaintenance;
import com.enercomn.web.A07EquipmentMaintenance.mapper.A07TbEquipmentMaintenanceMapper;
import com.enercomn.web.A08EquipmentParam.bean.A08TbEquipmentParam;
import com.enercomn.web.A08EquipmentParam.mapper.A08TbEquipmentParamMapper;
import com.enercomn.web.A99TableExcelCommon.vo.ExcelPropertiesVo;

/**
 * @Date: 2021/9/7 14:19<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
public enum ExcelTableEnum {
    A01EquipmentAssetsBean(A01EquipmentAssetsBean.class, A01EquipmentAsstsMapperTK.class, "tb_equipment_assets","设备资产信息"),
    A02EquipmentInfomationBean(A02EquipmentInfomationBean.class, A02EquipmentInfomationMapperTK.class, "tb_equipment_information","设备人员信息"),
    A03EquipmentSupplerBean(A03EquipmentSupplerBean.class, A03EquipmentSupplerMapperTK.class, "tb_equipment_suppler","设备供应商信息"),
    A04FindToolAccount(A04ToolAccount.class, A04ToolAccountMapperTK.class, "tb_tool_account","设备工具台账信息"),
    A05TbOplClass(A05TbOplClass.class, A05TbOplClassMapper.class, "tb_opl_class","OPL单点课信息"),
    A06TbEquipmentMaterial(A06TbEquipmentMaterial.class, A06TbEquipmentMaterialMapperTk.class, "tb_equipment_material","设备物料"),
//    A07TbEquipmentMaintenance(A07TbEquipmentMaintenance.class, A07TbEquipmentMaintenanceMapper.class, "tb_equipment_material"),
//    A08TbEquipmentParam(A08TbEquipmentParam.class, A08TbEquipmentParamMapper.class, "tb_equipment_param"),
//    A09TbSpotCheck(A09TbSpotCheck.class, A09TbSpotCheckMapper.class,"tb_spot_check"),
//    A10TbEquipmentCheckPlan(A10TbEquipmentCheckPlan.class, A10TbEquipmentCheckPlanMapper.class,"tb_equipment_check_plan")
    ;
    private Class beanClazz;
    private Class mapperClazz;
    private String tableEnName;
    private String tableChName;

    ExcelTableEnum(Class beanClazz, Class mapperClazz, String tableEnName, String tableChName) {
        this.beanClazz = beanClazz;
        this.mapperClazz = mapperClazz;
        this.tableEnName = tableEnName;
        this.tableChName = tableChName;
    }

    public static ExcelPropertiesVo getExcelPropertiesByEnName(String tableName) {
        ExcelTableEnum[] values = ExcelTableEnum.values();
        for (int i = 0; i < values.length; i++) {
            ExcelTableEnum value = values[i];
            if (tableName.equals(value.tableEnName)) {
                return new ExcelPropertiesVo(value.beanClazz,value.mapperClazz,value.tableEnName,value.tableChName);
            }
        }
        return null;
    }


}
