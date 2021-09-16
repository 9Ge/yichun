package com.enercomn.web.baseManager.service;

import com.enercomn.utils.PageBean;
import com.enercomn.utils.PageUtil;
import com.enercomn.utils.StringUtils;

import com.enercomn.web.baseManager.bean.*;
import com.enercomn.web.baseManager.mapper.BaseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.github.pagehelper.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 *基础service层
 * 创建关联 2020-02-8
 * CooL
 */
@Service
@Transactional
@Slf4j
public class BaseService {

    //菜单关联Mapper层接口

   // private BaseMapper baseMapper;


    /**
     * 新增用户
     * @param baseAddBean
     * @return
     */
    public int add(BaseAddBean baseAddBean,BaseMapper baseMapper){

        return baseMapper.add(baseAddBean);
    }

    /**
     * 新增
     * @param baseAddBean
     * @return
     */
    public int addInfoAndDetail(BaseAddBean baseAddBean,BaseMapper baseMapper){
        try{
          //  baseAddBean.setId(StringUtils.getUUID());
            int addCount  = baseMapper.add(baseAddBean);
            if (addCount <= 0 ){
                throw new RuntimeException("插入主表异常！");
            }else{
                if(null != baseAddBean.getBaseDetailBeanList() && !baseAddBean.getBaseDetailBeanList().isEmpty()){
                    for(int i = 0 ; i< baseAddBean.getBaseDetailBeanList().size(); i++){
                        BaseDetailBean baseDetailBean= (BaseDetailBean)baseAddBean.getBaseDetailBeanList().get(i);
                        baseDetailBean.setParentId(baseAddBean.getId());
                    }
                    baseMapper.addDetail(baseAddBean.getBaseDetailBeanList());
                }
//                if (addDetailCount <= 0){
//                    throw new RuntimeException("插入明细表异常！");
//                }else{
                return addCount;
//                }
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public int addOrUpdate(BaseAddBean baseAddBean, BaseMapper baseMapper) {
        return baseMapper.addOrUpdate(baseAddBean);
    }

    public int addOrUpdateInfoAndDetail(BaseAddBean baseAddBean, BaseMapper baseMapper) {
        try{
           // baseAddBean.setId(StringUtils.getUUID());
            int addCount  = baseMapper.addOrUpdate(baseAddBean);
            if (addCount <= 0 ){
                throw new RuntimeException("插入主表异常！");
            }else{
                for(int i = 0 ; i< baseAddBean.getBaseDetailBeanList().size(); i++){
                    BaseDetailBean baseDetailBean= (BaseDetailBean)baseAddBean.getBaseDetailBeanList().get(i);
                    baseDetailBean.setParentId(baseAddBean.getId());
                    if (StringUtil.isEmpty(baseDetailBean.getId())){
                        baseDetailBean.setId(StringUtils.getUUID());
                    }
                }
                int addDetailCount = baseMapper.addOrUpdateInfoAndDetail(baseAddBean.getBaseDetailBeanList());
                if (addDetailCount <= 0){
                    throw new RuntimeException("插入明细表异常！");
                }else{
                    return addCount;
                }
            }

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 查下用户
     * @param baseSelectParamBean
     * @return
     */
    public PageBean getList(BaseSelectParamBean baseSelectParamBean,BaseMapper baseMapper){

        PageHelper.startPage(baseSelectParamBean.getCurrPage(), baseSelectParamBean.getPageSize());

        List<BaseSelectBean> list = baseMapper.getList(baseSelectParamBean);

        PageInfo<BaseSelectBean> pageInfo = new PageInfo<>(list);

        return new PageBean(pageInfo);
    }

    /**
     * 手动分页查询
     * @param baseSelectParamBean
     * @param baseMapper
     * @return
     */
    public PageBean getListBySelf(BaseSelectParamBean baseSelectParamBean,BaseMapper baseMapper) {
        List<BaseSelectBean> hostBeanList = baseMapper.getList(baseSelectParamBean);
        PageInfo<BaseSelectBean> pageInfo = new PageInfo<>();
        pageInfo.setTotal(hostBeanList.size());
        pageInfo.setPageSize(baseSelectParamBean.getPageSize());
        pageInfo.setPageNum(baseSelectParamBean.getCurrPage());
        PageUtil.pageInfoSetListBySelf(pageInfo, hostBeanList);
        return new PageBean(pageInfo);
    }

    /**
     * 获取所有作者列表
     * @param
     * @return
     */
    public List<BaseSelectBean> getAllList(BaseMapper baseMapper){

        BaseSelectParamBean a02D04SelectParamBean = new BaseSelectParamBean();
        //a02D04SelectParamBean.setId(id);
        List<BaseSelectBean> list = baseMapper.getList(a02D04SelectParamBean);
        return list;
    }

    /**
     * 修改用户
     * @param baseUpdateBean
     * @return
     */
    public int update(BaseUpdateBean baseUpdateBean,BaseMapper baseMapper){

        return baseMapper.update(baseUpdateBean);
    }

    /**
     * 修改用户
     * @param baseUpdateBean
     * @return
     */
    public int updateInfoAndDetail(BaseUpdateBean baseUpdateBean,BaseMapper baseMapper){

        try{
            BaseUpdateParamBean baseUpdateParamBean = new BaseUpdateParamBean();
            baseUpdateParamBean.setId(baseUpdateBean.getId());
            baseUpdateParamBean.setIsDel("1");
            baseUpdateParamBean.setCUpdateUser(baseUpdateBean.getCUpdateUser());
            deleteDetail(baseUpdateParamBean,baseMapper);
            if (null !=baseUpdateBean.getBaseDetailBeanList() && !baseUpdateBean.getBaseDetailBeanList().isEmpty()){
                baseMapper.addDetail(baseUpdateBean.getBaseDetailBeanList());
            }
//            if (addDetailCount <= 0){
//                throw new RuntimeException("插入明细表异常！");
//            }else{
                return  baseMapper.update(baseUpdateBean);
//            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 删除用户
     * @param baseUpdateParamBean
     * @return
     */
    public int delete(BaseUpdateParamBean baseUpdateParamBean,BaseMapper baseMapper){

        return baseMapper.delete(baseUpdateParamBean);
    }

    /**
     * 删除用户
     * @param baseUpdateParamBean
     * @return
     */
    public int deleteDetail(BaseUpdateParamBean baseUpdateParamBean,BaseMapper baseMapper){

        return baseMapper.deleteDetail(baseUpdateParamBean);
    }

    /**
     * 删除用户
     * @param baseUpdateParamBean
     * @return
     */
    public int deleteInfoAndDetail(BaseUpdateParamBean baseUpdateParamBean,BaseMapper baseMapper){
        baseMapper.deleteDetail(baseUpdateParamBean);
        return baseMapper.delete(baseUpdateParamBean);
    }

    /**
     * 根据ID查询
     * @param baseSelectParamBean
     * @return
     */
    public BaseSelectBean getInfo(BaseSelectParamBean baseSelectParamBean,BaseMapper baseMapper){

        return baseMapper.getInfo(baseSelectParamBean);
    }
}
