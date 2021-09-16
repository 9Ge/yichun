package com.enercomn.web.A14Evaluate.service;

import com.enercomn.utils.PageBean;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A14Evaluate.bean.A14TbTpmEquipmentEvaluate;
import com.enercomn.web.A14Evaluate.mapper.A14TbTpmEquipmentEvaluateMapper;
import com.enercomn.web.baseManager.model.PageObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Date: 2021/8/16 14:57<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Service
@SuppressWarnings({"all"})
public class A14TbTpmEquipmentEvaluateService {

    private final A14TbTpmEquipmentEvaluateMapper a14TbTpmEquipmentEvaluateMapper;

    public A14TbTpmEquipmentEvaluateService(A14TbTpmEquipmentEvaluateMapper a14TbTpmEquipmentEvaluateMapper) {
        this.a14TbTpmEquipmentEvaluateMapper = a14TbTpmEquipmentEvaluateMapper;
    }

    /**
     * 新增设备管理评价表数据
     *
     * @param evaluate
     * @return
     */
    public boolean addData(A14TbTpmEquipmentEvaluate evaluate) {
        try {
            evaluate.setTteeId(StringUtils.getUUID());
            return a14TbTpmEquipmentEvaluateMapper.insert(evaluate) >= 0;
        } catch (Exception e) {
            log.info("{}", evaluate);
            throw new RuntimeException("新增异常：" + e.getMessage());
        }
    }


    /**
     * 删除设备管理评价表数据
     *
     * @param evaluate
     * @return
     */
    public boolean deleteData(A14TbTpmEquipmentEvaluate evaluate) {
        try {
            A14TbTpmEquipmentEvaluate delParam = new A14TbTpmEquipmentEvaluate();
            delParam.setTteeId(evaluate.getTteeId());
            delParam.setIsDel(1);
            return a14TbTpmEquipmentEvaluateMapper.updateByPrimaryKey(delParam) >= 0;
        } catch (Exception e) {
            log.info("{}", evaluate);
            throw new RuntimeException("删除异常：" + e.getMessage());
        }
    }


    /**
     * 更新设备管理评价表数据
     *
     * @param evaluate
     * @return
     */
    public boolean updateData(A14TbTpmEquipmentEvaluate evaluate) {
        try {
            return a14TbTpmEquipmentEvaluateMapper.updateByPrimaryKey(evaluate) >= 0;
        } catch (Exception e) {
            log.info("{}", evaluate);
            throw new RuntimeException("修改异常：" + e.getMessage());
        }
    }


    /**
     * 查询设备管理评价表数据列表
     * 分页
     * @param pageObject
     * @return
     */
    public PageBean findDataList(PageObject<A14TbTpmEquipmentEvaluate> pageObject) {
        A14TbTpmEquipmentEvaluate evaluate = pageObject.getData();

        PageHelper.startPage(pageObject.getCurrPage(), pageObject.getPageSize());
        Example example = new Example(A14TbTpmEquipmentEvaluate.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel", 0);

        List<A14TbTpmEquipmentEvaluate> a10TbSpotCheckList = a14TbTpmEquipmentEvaluateMapper.selectByExample(example);
        PageInfo<A14TbTpmEquipmentEvaluate> pageInfo = new PageInfo(a10TbSpotCheckList);
        return new PageBean(pageInfo);
    }

}
