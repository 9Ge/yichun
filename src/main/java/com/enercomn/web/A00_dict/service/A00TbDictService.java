package com.enercomn.web.A00_dict.service;

import com.enercomn.utils.StringUtils;
import com.enercomn.web.A00_dict.bean.A00TbDict;
import com.enercomn.web.A00_dict.bean.A00TbDictResult;
import com.enercomn.web.A00_dict.mapper.A00TbDictMapper;
import com.enercomn.web.A99TableExcelCommon.handle.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date: 2021/8/10 14:01<br/>
 * @Author: yangcheng
 * @Version: 1.0
 */
@Slf4j
@Service
public class A00TbDictService {

    @Autowired
    A00TbDictMapper a00TbDictMapper;

    /**
     * 新增数据字典
     *
     * @param dicts
     */
    public boolean addData(List<A00TbDict> dicts) {
        dicts.forEach(dict -> {
            log.info(dict.getBaseCode() + "-" + dict.getBaseName());
            dict.setDictId(StringUtils.getUUID());
            a00TbDictMapper.saveDict(dict);
        });

        return true;
    }

    /**
     * 数据字典查询
     *
     * @param code
     * @return
     */
    public List<A00TbDictResult> findDataList(String code) {
        return a00TbDictMapper.queryByParam(code);
    }

    /**
     * 根据key 查询数据字典
     *
     * @param code
     * @return
     */
    public A00TbDictResult findDataByKey(String code) {
        return a00TbDictMapper.queryByKey(code);
    }

    public String getSelectorVal(String keys) {
        if(StringUtils.isEmpty(keys)){
            return  "";
        }
        String[] keyArray = keys.split(",");
        String val = "";
        for (int i = 0; i < keyArray.length; i++) {
            A00TbDictResult result = this.findDataByKey(keyArray[i]);
            if(result != null){
                val += result.getValue();
                if (i < keyArray.length - 2) {
                    val += ",";
                }
            }
        }
        return val;
    }

    public String getSelectorKey(String value) {
        A00TbDictResult result = a00TbDictMapper.queryByValue("%" + value + "%");
        if (result != null) {
            return result.getKey();
        }
        return null;
    }
}
