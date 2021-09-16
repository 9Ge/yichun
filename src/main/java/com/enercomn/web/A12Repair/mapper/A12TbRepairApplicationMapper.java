package com.enercomn.web.A12Repair.mapper;

import com.enercomn.web.A12Repair.bean.A12TbRepairApplication;
import com.enercomn.web.A12Repair.bean.A12TbRepairApplicationVo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author yangcheng
 */
@Repository
public interface A12TbRepairApplicationMapper extends Mapper<A12TbRepairApplication> {

    List<A12TbRepairApplicationVo> queryRepairApplication(A12TbRepairApplication a12TbRepairApplication);
}