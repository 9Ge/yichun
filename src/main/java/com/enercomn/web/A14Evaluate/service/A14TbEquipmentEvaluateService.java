package com.enercomn.web.A14Evaluate.service;

import com.enercomn.utils.PageBean;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A14Evaluate.bean.A14TbEquipmentEvaluate;
import com.enercomn.web.A14Evaluate.mapper.A14TbEquipmentEvaluateMapper;
import com.enercomn.web.baseManager.model.PageObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Date: 2021/8/16 14:34<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Service
public class A14TbEquipmentEvaluateService {

    private final A14TbEquipmentEvaluateMapper a14TbEquipmentEvaluateMapper;

    public A14TbEquipmentEvaluateService(A14TbEquipmentEvaluateMapper a14TbEquipmentEvaluateMapper) {
        this.a14TbEquipmentEvaluateMapper = a14TbEquipmentEvaluateMapper;
    }


    /**
     * 新增设备管理评价表数据
     *
     * @param evaluate
     * @return
     */
    public boolean addData(A14TbEquipmentEvaluate evaluate) {
        try {
            evaluate.setTeeId(StringUtils.getUUID());
            return a14TbEquipmentEvaluateMapper.insert(evaluate) >= 0;
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
    public boolean deleteData(A14TbEquipmentEvaluate evaluate) {
        try {
            A14TbEquipmentEvaluate delParam = new A14TbEquipmentEvaluate();
            delParam.setTeeId(evaluate.getTeeId());
            delParam.setIsDel(1);
            return a14TbEquipmentEvaluateMapper.updateByPrimaryKey(delParam) >= 0;
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
    public boolean updateData(A14TbEquipmentEvaluate evaluate) {
        try {
            return a14TbEquipmentEvaluateMapper.updateByPrimaryKey(evaluate) >= 0;
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
    public PageBean findDataList(PageObject<A14TbEquipmentEvaluate> pageObject) {
        A14TbEquipmentEvaluate evaluate = pageObject.getData();

        PageHelper.startPage(pageObject.getCurrPage(), pageObject.getPageSize());
        Example example = new Example(A14TbEquipmentEvaluate.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel", 0);

        List<A14TbEquipmentEvaluate> a10TbSpotCheckList = a14TbEquipmentEvaluateMapper.selectByExample(example);
        PageInfo<A14TbEquipmentEvaluate> pageInfo = new PageInfo(a10TbSpotCheckList);
        return new PageBean(pageInfo);
    }



}
