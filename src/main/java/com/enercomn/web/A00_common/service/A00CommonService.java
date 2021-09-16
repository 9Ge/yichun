package com.enercomn.web.A00_common.service;

import com.enercomn.utils.PageBean;
import com.enercomn.web.A00_common.bean.A00DictionariesSelectBean;
import com.enercomn.web.A00_common.bean.A00DictionariesSelectParamBean;
import com.enercomn.web.A00_common.mapper.A00CommonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@Slf4j
@Transactional
public class A00CommonService {

    @Autowired
    private A00CommonMapper a00CommonMapper;

    public List<A00DictionariesSelectBean > findDictionariesInfo(A00DictionariesSelectParamBean dictionariesSelectParamBean) {
        return a00CommonMapper.findDictionariesInfo(dictionariesSelectParamBean);
    }


    /**
     * 生成随机文件名称
     *
     * @param fileType 文件类型
     * @return 返回的文件名
     */
    public String createFileName(String fileType) {
        String fileName = this.getRandomNum(10) + "." + fileType;
        return fileName;
    }


    /**
     * 生成随机数
     *
     * @param count 生成的位数
     * @return 返回的随机数字符串
     */

    private String getRandomNum(int count) {
        String str = "0123456789";
        StringBuilder sb = new StringBuilder();
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < count; i++) {
            int d = r.nextInt(10);
            sb.append(str.charAt(d));
        }
        return sb.toString();
    }
}
