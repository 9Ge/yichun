package com.enercomn.web.A10CheckPlan.mapper;

import com.enercomn.web.A10CheckPlan.bean.A10TbEquipmentCheckPlan;
import com.enercomn.web.A10CheckPlan.bean.A10TbEquipmentCheckPlanVo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author yangcheng
 */
@Repository
public interface A10TbEquipmentCheckPlanMapper extends Mapper<A10TbEquipmentCheckPlan> {
    List<A10TbEquipmentCheckPlanVo> queryCheckPlan(A10TbEquipmentCheckPlan plan);

    int batchSaveCheckPlan(List<A10TbEquipmentCheckPlan> plan);
}