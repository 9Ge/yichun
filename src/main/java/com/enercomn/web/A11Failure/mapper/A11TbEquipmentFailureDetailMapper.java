package com.enercomn.web.A11Failure.mapper;


import com.enercomn.web.A11Failure.bean.A11TbEquipmentFailureDetail;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author yangcheng
 */
@Repository
public interface A11TbEquipmentFailureDetailMapper extends Mapper<A11TbEquipmentFailureDetail> {
    List<A11TbEquipmentFailureDetail> queryFailureDetail(A11TbEquipmentFailureDetail detail);
}