package com.enercomn.web.A01EquipmentManagement.mapper;


import com.enercomn.web.A01EquipmentManagement.bean.A01TbEquipmentMaterialRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.enercomn.web.A01EquipmentManagement.vo.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author yangcheng
 */
@Repository
public interface A01TbEquipmentMaterialRelationMapper extends Mapper<A01TbEquipmentMaterialRelation> {
    int saveBatch(List<A01TbEquipmentMaterialRelation> list);

    /**
     * 根据设备Id查找和物料的关联关系
     * @param teaId
     * @return
     */
    List<A01EquipmentVo> queryEquipmentByTeaId(@Param("teaId") String teaId);
}