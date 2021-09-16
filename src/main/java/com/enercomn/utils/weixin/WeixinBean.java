package com.enercomn.utils.weixin;
import lombok.Data;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;

import java.util.List;

/**
 * @author Oliver
 * @description emailBeam
 * @date   2019/6/4 16:38
 * @version 1.0
 */
@Data
public class WeixinBean {

    /**
     * 模板消息ID
     */
    private String templateId;
    /**
     * 点击跳转连接
     */
    private String url;
    /**
     * 发件人的用户名
     */
    private List<WxMpTemplateData> templateList;
    /**
     * 模板消息发送对象
     */
    private List<String> openIdList;


}