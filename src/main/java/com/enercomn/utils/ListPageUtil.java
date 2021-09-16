package com.enercomn.utils;

import com.enercomn.web.A11Failure.bean.A11TbEquipmentFailureDetail;
import com.enercomn.web.baseManager.model.PageObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Date: 2021/9/1 14:45<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
public class ListPageUtil {

    public static PageBean pageInfo(PageObject pageObject, List list){
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(list.size());
        pageInfo.setPageNum( pageObject.getCurrPage());
        pageInfo.setPageSize(pageObject.getPageSize());
        return  new PageBean<PageInfo>(PageUtil.pageInfoSetListBySelf(pageInfo,list));
    }
}
