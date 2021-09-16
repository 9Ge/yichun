package com.enercomn.web.A02EquipmentInfomation.service;

import com.enercomn.utils.PageBean;
import com.enercomn.utils.StringUtils;

import com.enercomn.web.A02EquipmentInfomation.bean.A02EquipmentInfomationBean;
import com.enercomn.web.A02EquipmentInfomation.mapper.A02EquipmentInfomationMapperTK;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class A02EquipmentInfomationService {

    @Autowired
    A02EquipmentInfomationMapperTK mapperTK;

    public PageBean findDataList(A02EquipmentInfomationBean bean, int currPage, int pageSize){
        try {
            Example example = new Example(A02EquipmentInfomationBean.class);
            Example.Criteria criteria = example.createCriteria();
            if(StringUtils.isNotEmpty(bean.getName())){
                criteria.andLike("equipmentName", "%" + bean.getName() + "%");
            }
            if(StringUtils.isNotEmpty(bean.getJobNo())){
                criteria.andLike("jobNo", "%" + bean.getJobNo() + "%");
            }
            PageHelper.startPage(currPage,pageSize);
            List<A02EquipmentInfomationBean> passageWayInfoList = mapperTK.selectByExample(example);
            PageInfo<A02EquipmentInfomationBean> pageInfo = new PageInfo<>(passageWayInfoList);
            return new PageBean(pageInfo);
        }catch (Exception e){
            log.error("查询设备管理人员信息出错：" , e);
            return null;
        }

    }

    public A02EquipmentInfomationBean findByJobNo(String jobNo){
        try {
            Example example = new Example(A02EquipmentInfomationBean.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("jobNo", jobNo);
            List<A02EquipmentInfomationBean> list = mapperTK.selectByExample(example);
            if(list == null&&list.size()<=0){
                return null ;
            }
            return list.get(0);
        }catch (Exception e){
            log.error("查询设备管理人员信息出错：" , e);
            return null;
        }

    }

    /**
     * @author yangcheng
     * @return
     */
    public List<Map<String,String>>  query(){
        List<A02EquipmentInfomationBean> passageWayInfoList = mapperTK.selectAll();
        List<Map<String,String>> results = new ArrayList< Map<String,String>>();
        passageWayInfoList.forEach(way->{
            Map<String,String> map = new HashMap<>();
            String jobNo = way.getJobNo();
            String name = way.getName();
            map.put("jobNo",jobNo);
            map.put("name",name);
            results.add(map);
        });
        return results;

    }
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public int addData(A02EquipmentInfomationBean bean){
        bean.setUserPwd( bCryptPasswordEncoder.encode(bean.getUserPwd()));
        bean.setRole( "ROLE_USER");
        bean.setTeiId(StringUtils.getUUID());
        return mapperTK.insert(bean);

    }

    public A02EquipmentInfomationBean findDataDetail(String id){
        return mapperTK.selectByPrimaryKey(id);

    }

    public int updateData(A02EquipmentInfomationBean bean){
        A02EquipmentInfomationBean dataDetail = this.findDataDetail(bean.getTeiId());
        boolean matches = bCryptPasswordEncoder.matches(bean.getUserPwd(), dataDetail.getUserPwd());
        if(!matches){
            bean.setUserPwd(bCryptPasswordEncoder.encode(bean.getUserPwd()));
        }
        return mapperTK.updateByPrimaryKeySelective(bean);

    }


    public Object deleteData(A02EquipmentInfomationBean bean) {
        return mapperTK.deleteByPrimaryKey(bean);
    }
}
