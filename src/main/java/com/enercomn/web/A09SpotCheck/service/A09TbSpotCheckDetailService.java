package com.enercomn.web.A09SpotCheck.service;

import com.enercomn.Enum.CheckPlanSourceEnum;
import com.enercomn.utils.PageBean;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A09SpotCheck.bean.A09TbSpotCheck;
import com.enercomn.web.A09SpotCheck.bean.A09TbSpotCheckDetail;
import com.enercomn.web.A09SpotCheck.mapper.A09TbSpotCheckDetailMapper;
import com.enercomn.web.A10CheckPlan.service.A10TbEquipmentCheckPlanService;
import com.enercomn.web.baseManager.model.PageObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Date: 2021/8/13 17:37<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Service
@SuppressWarnings({"all"})
public class A09TbSpotCheckDetailService {


    private final A09TbSpotCheckDetailMapper a09TbSpotCheckDetailMapper;
    private final A10TbEquipmentCheckPlanService a10TbEquipmentCheckPlanService;
    private final A09TbSpotCheckService a09TbSpotCheckService;

    public A09TbSpotCheckDetailService(A09TbSpotCheckDetailMapper a09TbSpotCheckDetailMapper, A10TbEquipmentCheckPlanService a10TbEquipmentCheckPlanService, A09TbSpotCheckService a09TbSpotCheckService) {
        this.a09TbSpotCheckDetailMapper = a09TbSpotCheckDetailMapper;
        this.a10TbEquipmentCheckPlanService = a10TbEquipmentCheckPlanService;
        this.a09TbSpotCheckService = a09TbSpotCheckService;
    }


    /**
     * 新增设备点检明细列表
     *
     * @param checkDetail
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public boolean addData(A09TbSpotCheckDetail checkDetail) {
        try {
            checkDetail.setTscdId(StringUtils.getUUID());
            a09TbSpotCheckDetailMapper.insert(checkDetail);
            Integer isFind = checkDetail.getIsFind()==null?0:checkDetail.getIsFind();
            if(isFind == 1){
                a09TbSpotCheckService.updateCheckStatusById(checkDetail.getTscId(),1);
                a10TbEquipmentCheckPlanService.generateCheckPlanByAddCheck(checkDetail);
            }
            return true;
        } catch (Exception e) {
            log.info("{}", checkDetail);
            throw new RuntimeException("新增异常：" + e.getMessage());
        }
    }




    /**
     * 删除设备点检明细列表
     *
     * @param checkDetail
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public boolean deleteData(A09TbSpotCheckDetail checkDetail) {
        try {
            A09TbSpotCheckDetail delParam = new A09TbSpotCheckDetail();
            delParam.setTscId(checkDetail.getTscId());
            delParam.setIsDel(1);
            a09TbSpotCheckDetailMapper.updateByPrimaryKey(delParam);
            a10TbEquipmentCheckPlanService.deletePlanByFrom(CheckPlanSourceEnum.CheckExceptionCode.getName(),checkDetail.getTscdId());
            a09TbSpotCheckService.updateCheckStatusById(checkDetail.getTscId(),0);
            return true;
        } catch (Exception e) {
            log.info("{}", checkDetail);
            throw new RuntimeException("删除异常：" + e.getMessage());
        }
    }


    /**
     * 更新设备点检明细列表
     *
     * @param checkDetail
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public boolean updateData(A09TbSpotCheckDetail checkDetail) {
        try {
            a09TbSpotCheckDetailMapper.updateByPrimaryKey(checkDetail);
            Integer isFind = checkDetail.getIsFind()==null?0:checkDetail.getIsFind();
            if(isFind == 1){
                a09TbSpotCheckService.updateCheckStatusById(checkDetail.getTscId(),1);
                a10TbEquipmentCheckPlanService.generateCheckPlanByUpdateCheck(checkDetail);
            }else{
                a09TbSpotCheckService.updateCheckStatusById(checkDetail.getTscId(),0);
                a10TbEquipmentCheckPlanService.deletePlanByFrom(CheckPlanSourceEnum.CheckExceptionCode.getName(),checkDetail.getTscdId());
            }
            return true;
        } catch (Exception e) {
            log.info("{}", checkDetail);
            throw new RuntimeException("修改异常：" + e.getMessage());
        }
    }


    /**
     * 查询设备点检异常列表
     *
     * @param pageObject
     * @return
     */
    public A09TbSpotCheckDetail findDataList(String stcid) {

        Example example = new Example(A09TbSpotCheckDetail.class);
        Example.Criteria criteria = example.createCriteria();

        if (org.apache.commons.lang.StringUtils.isNotEmpty(stcid)) {
            criteria.andEqualTo("tscId", stcid);
        }
        criteria.andEqualTo("isDel", 0);

        List<A09TbSpotCheckDetail> a09TbSpotCheckDetailList = a09TbSpotCheckDetailMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(a09TbSpotCheckDetailList)){
            return a09TbSpotCheckDetailList.get(0);
        }
        return null;
    }


}
