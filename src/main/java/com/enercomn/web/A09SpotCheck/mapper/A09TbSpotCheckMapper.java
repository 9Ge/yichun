package com.enercomn.web.A09SpotCheck.mapper;

import com.enercomn.web.A09SpotCheck.bean.A09TbSpotCheck;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author yangcheng
 */
@Repository
public interface A09TbSpotCheckMapper extends Mapper<A09TbSpotCheck> {

    int saveBatchCheck(List<A09TbSpotCheck> list);

    List<A09TbSpotCheck> querySpotCheck(A09TbSpotCheck a09TbSpotCheck);
}