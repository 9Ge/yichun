package com.enercomn.utils.weixin;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 微信工具类

 */
@Component
@Slf4j
public class WeixinUtils {

    @Resource
    protected  WxMpService wxMpService;

    @Resource
    protected  WxMpUserService wxMpUserService;





    public  boolean pushAlarm(WeixinBean weixinBean){
        for(String openId:weixinBean.getOpenIdList()){
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                    .toUser(openId)
                    .templateId(weixinBean.getTemplateId())
                    .url(weixinBean.getUrl())
                    .build();
            for(WxMpTemplateData wxMpTemplateData:weixinBean.getTemplateList()){
                templateMessage.addData(wxMpTemplateData);
            }
            try{
                log.info(wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage));
            }catch (WxErrorException e){
                log.error(e.getMessage());
                return false;
            }

        }

        return true;
    }

    public  boolean getUserList(){
        try {
            WxMpUserList wxMpUserList= wxMpUserService.userList(null);
            for(String openId:wxMpUserList.getOpenids()){
                log.info(openId+"======");
            }
            List<WxMpUser> list2 =wxMpUserService.userInfoList(wxMpUserList.getOpenids());

            for(WxMpUser wxMpUser:list2){
                log.info(wxMpUser.toString()+"======");
            }

        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return true;
    }

}
