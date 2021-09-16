package com.enercomn.web.A11Failure.service;

import com.enercomn.utils.PageBean;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A11Failure.bean.A11TbEquipmentFailureReport;
import com.enercomn.web.A11Failure.mapper.A11TbEquipmentFailureReportMapper;
import com.enercomn.web.baseManager.model.PageObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Date: 2021/8/16 11:55<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Service
public class A11TbEquipmentFailureReportService {

    private final A11TbEquipmentFailureReportMapper a11TbEquipmentFailureReportMapper;

    public A11TbEquipmentFailureReportService(A11TbEquipmentFailureReportMapper a11TbEquipmentFailureReportMapper) {
        this.a11TbEquipmentFailureReportMapper = a11TbEquipmentFailureReportMapper;
    }


    /**
     * 新增设备故障报告数据
     *
     * @param failureReport
     * @return
     */
    public boolean addData(A11TbEquipmentFailureReport failureReport) {
        try {
            failureReport.setTefrId(StringUtils.getUUID());
            return a11TbEquipmentFailureReportMapper.insert(failureReport) >= 0;
        } catch (Exception e) {
            log.info("{}", failureReport);
            throw new RuntimeException("新增异常：" + e.getMessage());
        }
    }


    /**
     * 删除设备故障报告数据
     *
     * @param failureReport
     * @return
     */
    public boolean deleteData(A11TbEquipmentFailureReport failureReport) {
        try {
            A11TbEquipmentFailureReport delParam = new A11TbEquipmentFailureReport();
            delParam.setTefrId(failureReport.getTefrId());
            delParam.setIsDel(1);
            return a11TbEquipmentFailureReportMapper.updateByPrimaryKey(delParam) >= 0;
        } catch (Exception e) {
            log.info("{}", failureReport);
            throw new RuntimeException("删除异常：" + e.getMessage());
        }
    }


    /**
     * 更新设备故障报告数据
     *
     * @param failureReport
     * @return
     */
    public boolean updateData(A11TbEquipmentFailureReport failureReport) {
        try {
            return a11TbEquipmentFailureReportMapper.updateByPrimaryKey(failureReport) >= 0;
        } catch (Exception e) {
            log.info("{}", failureReport);
            throw new RuntimeException("修改异常：" + e.getMessage());
        }
    }


    /**
     * 查询设备故障报告数据列表
     * 分页
     * @param pageObject
     * @return
     */
    public PageBean findDataList(PageObject<A11TbEquipmentFailureReport> pageObject) {
        A11TbEquipmentFailureReport failureReport = pageObject.getData();

        PageHelper.startPage(pageObject.getCurrPage(), pageObject.getPageSize());
        Example example = new Example(A11TbEquipmentFailureReport.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel", 0);

        List<A11TbEquipmentFailureReport> a10TbSpotCheckList = a11TbEquipmentFailureReportMapper.selectByExample(example);
        PageInfo<A11TbEquipmentFailureReport> pageInfo = new PageInfo(a10TbSpotCheckList);
        return new PageBean(pageInfo);
    }

}
