package com.enercomn.web.A04ToolAccount.service;

import com.enercomn.utils.PageBean;
import com.enercomn.utils.StringUtils;
import com.enercomn.web.A04ToolAccount.bean.A04ToolAccount;
import com.enercomn.web.A04ToolAccount.mapper.A04ToolAccountMapperTK;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Slf4j
@Service
public class A04ToolAccountService {


    @Autowired
    A04ToolAccountMapperTK mapperTK;

    public PageBean findDataList(A04ToolAccount bean, int currPage, int pageSize){
        try {
            Example example = new Example(A04ToolAccount.class);
            if(StringUtils.isNotEmpty(bean.getToolName())){
                example.createCriteria().andLike("equipmentName", "%" + bean.getToolName() + "%");
            }
            PageHelper.startPage(currPage,pageSize);
            List<A04ToolAccount> passageWayInfoList = mapperTK.selectByExample(example);
            PageInfo<A04ToolAccount> pageInfo = new PageInfo<>(passageWayInfoList);
            return new PageBean(pageInfo);
        }catch (Exception e){
            log.error("查询维修工具台账信息出错：" , e);
            return null;
        }

    }

    public int addData(A04ToolAccount bean){
        bean.setTtaId(StringUtils.getUUID());
        return mapperTK.insert(bean);

    }

    public A04ToolAccount findDataDetail(String id){
        return mapperTK.selectByPrimaryKey(id);

    }

    public int updateData(A04ToolAccount bean){
        return mapperTK.updateByPrimaryKeySelective(bean);
    }


    public List<A04ToolAccount> dataSelector(){
        return mapperTK.selectAll();
    }

    public Object deleteData(A04ToolAccount bean) {
        return mapperTK.deleteByPrimaryKey(bean);
    }
}
