package com.enercomn.web.A03EquipmentSuppler.service;

import com.enercomn.utils.ListPageUtil;
import com.enercomn.utils.PageBean;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A03EquipmentSuppler.bean.A03EquipmentSupplerBean;
import com.enercomn.web.A03EquipmentSuppler.mapper.A03EquipmentSupplerMapperTK;
import com.enercomn.web.baseManager.model.PageObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Slf4j
@Service
public class A03EquipmentSupplerService {


    @Autowired
    A03EquipmentSupplerMapperTK mapperTK;

    public PageBean findDataList(A03EquipmentSupplerBean bean, int currPage, int pageSize){
        try {
            PageObject<A03EquipmentSupplerBean> pageObj = new PageObject<>(currPage,pageSize);
            pageObj.setData(bean);
            if(bean!=null ){
                String equipmentCode = bean.getEquipmentCode();
                String equipmentName = bean.getEquipmentName();
                if(StringUtils.isNotEmpty(equipmentCode)){
                    bean.setEquipmentCode("%"+equipmentCode+"%");
                }
                if(StringUtils.isNotEmpty(equipmentName)){
                    bean.setEquipmentName("%"+equipmentName+"%");
                }
            }
            List<A03EquipmentSupplerBean> passageWayInfoList = mapperTK.querySupplerAll(bean);
            return ListPageUtil.pageInfo(pageObj,passageWayInfoList);
        }catch (Exception e){
            log.error("查询设备供应商信息出错：" , e);
            return null;
        }

    }

    public int addData(A03EquipmentSupplerBean bean){
        bean.setTesId(StringUtils.getUUID());
        return mapperTK.insert(bean);

    }

    public A03EquipmentSupplerBean findDataDetail(String id){
        return mapperTK.selectByPrimaryKey(id);

    }

    public int updateData(A03EquipmentSupplerBean bean){
        return mapperTK.updateByPrimaryKeySelective(bean);

    }

    public Object deleteData(A03EquipmentSupplerBean bean) {
        return mapperTK.deleteByPrimaryKey(bean);
    }

}
