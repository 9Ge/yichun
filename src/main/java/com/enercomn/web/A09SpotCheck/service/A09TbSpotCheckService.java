package com.enercomn.web.A09SpotCheck.service;

import com.enercomn.Enum.CodePrefix;
import com.enercomn.utils.CodeUtil;
import com.enercomn.utils.ListPageUtil;
import com.enercomn.utils.PageBean;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A00_dict.service.A00TbDictService;
import com.enercomn.web.A01EquipmentManagement.bean.A01EquipmentAssetsBean;
import com.enercomn.web.A01EquipmentManagement.service.A01EquipmentAsstsService;
import com.enercomn.web.A04ToolAccount.service.A04ToolAccountService;
import com.enercomn.web.A07EquipmentMaintenance.bean.A07TbEquipmentMaintenance;
import com.enercomn.web.A08EquipmentParam.bean.A08TbEquipmentParam;
import com.enercomn.web.A08EquipmentParam.service.A08TbEquipmentParamService;
import com.enercomn.web.A09SpotCheck.bean.A09TbSpotCheck;
import com.enercomn.web.A09SpotCheck.mapper.A09TbSpotCheckMapper;
import com.enercomn.web.baseManager.model.PageObject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Date: 2021/8/13 14:32<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Service
@SuppressWarnings({"all"})
public class A09TbSpotCheckService {

    private final A09TbSpotCheckMapper a09TbSpotCheckMapper;
    private final A01EquipmentAsstsService a01EquipmentAsstsService;
    private final A04ToolAccountService a04ToolAccountService;
    private final A08TbEquipmentParamService a08TbEquipmentParamService;
    private final A00TbDictService a00TbDictService;

    public A09TbSpotCheckService(A09TbSpotCheckMapper a09TbSpotCheckMapper,
                                 A01EquipmentAsstsService a01EquipmentAsstsService,
                                 A04ToolAccountService a04ToolAccountService, A08TbEquipmentParamService a08TbEquipmentParamService, A00TbDictService a00TbDictService) {
        this.a09TbSpotCheckMapper = a09TbSpotCheckMapper;
        this.a01EquipmentAsstsService = a01EquipmentAsstsService;
        this.a04ToolAccountService = a04ToolAccountService;
        this.a08TbEquipmentParamService = a08TbEquipmentParamService;
        this.a00TbDictService = a00TbDictService;
    }


    /**
     * ?????????????????????????????????
     *
     * @param param
     * @return
     */
    public boolean addData(A09TbSpotCheck param) {
        try {
            param.setCheckCode(CodeUtil.generateCode(CodePrefix.DJ.getPrefix()));
            param.setTscId(StringUtils.getUUID());
            return a09TbSpotCheckMapper.insert(param) >= 0;
        } catch (Exception e) {
            log.info("{}", e);
            throw new RuntimeException("???????????????" + e.getMessage());
        }
    }

    public void updateCheckStatusById(String tscId,int status){
        if(StringUtils.isEmpty(tscId)){
            return ;
        }
        A09TbSpotCheck a09TbSpotCheck = this.findDataById(tscId);
        a09TbSpotCheck.setCheckStatus(String.valueOf(status));
        this.updateDataStatus(a09TbSpotCheck);
    }

    /**
     * ???????????????????????????
     *
     * @param param
     * @return
     */
    public boolean deleteData(A09TbSpotCheck param) {
        try {
            A09TbSpotCheck delParam = new A09TbSpotCheck();
            delParam.setTscId(param.getTscId());
            delParam.setIsDel(1);
            return a09TbSpotCheckMapper.updateByPrimaryKey(delParam) >= 0;
        } catch (Exception e) {
            log.info("{}", e);
            throw new RuntimeException("???????????????" + e.getMessage());
        }
    }

    /**
     * ???????????????????????????
     * @param param
     * @return
     */
    public boolean updateData(A09TbSpotCheck param) {
        try {
            return a09TbSpotCheckMapper.updateByPrimaryKey(param) >= 0;
        } catch (Exception e) {
            log.info("{}", e);
            throw new RuntimeException("???????????????" + e.getMessage());
        }
    }

    /**
     * ??????????????????????????? 0-????????? 1-????????? 2-????????? 3-????????????
     * @param param
     * @return
     */
    public boolean updateDataStatus(A09TbSpotCheck param) {
        try {

            return  a09TbSpotCheckMapper.updateByPrimaryKey(param)>= 0;
        } catch (Exception e) {
            log.info("{}", param);
            throw new RuntimeException("???????????????" + e.getMessage());
        }
    }

    /**
     * ???????????????????????????
     *
     * @param pageObject
     * @return
     */
    public PageBean findDataList(PageObject<A09TbSpotCheck> pageObject) {
        A09TbSpotCheck tbSpotCheck = pageObject.getData();
        if(tbSpotCheck!=null ){
            String equipmentCode = tbSpotCheck.getEquipmentCode();
            String equipmentName = tbSpotCheck.getEquipmentName();
            if(StringUtils.isNotEmpty(equipmentCode)){
                tbSpotCheck.setEquipmentCode("%"+equipmentCode+"%");
            }
            if(StringUtils.isNotEmpty(equipmentName)){
                tbSpotCheck.setEquipmentName("%"+equipmentName+"%");
            }
        }
        List<A09TbSpotCheck> a09TbSpotCheckList = a09TbSpotCheckMapper.querySpotCheck(tbSpotCheck);
        return ListPageUtil.pageInfo(pageObject,a09TbSpotCheckList);
    }

    /**
     * ????????????????????????
     * @param tbSpotCheck
     * @return
     */
    public Map groupDataByStatus(A09TbSpotCheck tbSpotCheck ){
        List<A09TbSpotCheck> a09TbSpotCheckList = a09TbSpotCheckMapper.querySpotCheck(tbSpotCheck);
        Map<String, List<A09TbSpotCheck>> collect = a09TbSpotCheckList.stream().collect(Collectors.groupingBy(t -> {
            if (t.getCheckStatus() == null) {
                return "0";
            }
            return t.getCheckStatus();
        }));
        return collect;
    }

    /**
     * ????????????????????????
     * @param tscId
     * @return
     */
    public A09TbSpotCheck findDataById(String tscId) {
        if(StringUtils.isEmpty(tscId)){
            return new A09TbSpotCheck();
        }
        A09TbSpotCheck a09TbSpotCheck = a09TbSpotCheckMapper.selectByPrimaryKey(tscId);
        return a09TbSpotCheck;
    }


    /**
     * ?????????????????????????????????????????????
     *
     * @param maintenance ??????????????????
     * @return
     */
    @Transactional
    public synchronized boolean batchGenerateCheckByMaintenance(@NonNull List<A07TbEquipmentMaintenance> maintenanceList) {
        List<A01EquipmentAssetsBean> assetList = a01EquipmentAsstsService.findAllAssets();
        List<A09TbSpotCheck> saveCheckList = new ArrayList<>();
        Iterator<A07TbEquipmentMaintenance> iterator = maintenanceList.iterator();
        while (iterator.hasNext()) {

            A07TbEquipmentMaintenance maintenance = iterator.next();
            A01EquipmentAssetsBean assetBean = this.getAssetBean(assetList, maintenance);
            if (assetBean == null) {
                continue;
            }
            saveCheckList.add(this.checkProperties(maintenance,assetBean));
        }
        return a09TbSpotCheckMapper.saveBatchCheck(saveCheckList) >= 0;
    }


    /**
     * ????????????????????????????????????Id??????????????????
     * @param assetList
     * @param maintenance
     * @return
     */
    private A01EquipmentAssetsBean getAssetBean(List<A01EquipmentAssetsBean> assetList, A07TbEquipmentMaintenance maintenance){
        A01EquipmentAssetsBean targetAsset = null;
        Iterator<A01EquipmentAssetsBean> iterator = assetList.iterator();
        while (iterator.hasNext()) {
            A01EquipmentAssetsBean ass =  iterator.next();
            if(ass.getTeaId().equals(maintenance.getTbEquipmentAssetsId())){
                targetAsset =ass;
                break;
            }
        }
        if(targetAsset == null){
            targetAsset = a01EquipmentAsstsService.findBeanById(maintenance.getTbEquipmentAssetsId());
        }
        return targetAsset;
    }
    /**
     * ?????????????????????
     * @param maintenance
     * @param assetBean
     * @return
     */
    private A09TbSpotCheck checkProperties(A07TbEquipmentMaintenance maintenance, A01EquipmentAssetsBean assetBean){
        A08TbEquipmentParam equipmentParam = a08TbEquipmentParamService.findDataById(maintenance.getTbEquipmentParamId());
        A09TbSpotCheck check = new A09TbSpotCheck();
        check.setTscId(StringUtils.getUUID());
        check.setCheckCode(CodeUtil.generateCode(CodePrefix.DJ.getPrefix()));
        check.setSourceCode(maintenance.getMaintenanceCode());
        check.setSourceId(maintenance.getMaintenanceId());
        check.setDate(new Date());
        if(assetBean != null){
            check.setUseDepartment(assetBean.getDepartment());
        }
        if(maintenance != null){
            check.setTbEquipmentAssetsId(maintenance.getTbEquipmentAssetsId());
            check.setCheckRequirements(maintenance.getStandard());
            String selectorVal2 =maintenance.getTool();
            check.setMethodsTool( maintenance.getMethod()+ "/" +selectorVal2);
        }

        if(equipmentParam != null){
            check.setPosition(equipmentParam.getMaintenanceParts());
        }
        return check;
    }


}

