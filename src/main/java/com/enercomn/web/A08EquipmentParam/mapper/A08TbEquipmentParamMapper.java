package com.enercomn.web.A08EquipmentParam.mapper;


import com.enercomn.web.A08EquipmentParam.bean.A08TbEquipmentParam;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author yangcheng
 */
@Repository
@SuppressWarnings({"all"})
public interface A08TbEquipmentParamMapper extends Mapper<A08TbEquipmentParam> {
    List<A08TbEquipmentParam> queryEquipmentParam(A08TbEquipmentParam a08TbEquipmentParam);
    List<A08TbEquipmentParam> paramSelect(A08TbEquipmentParam a08TbEquipmentParam);
}