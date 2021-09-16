package com.enercomn.web.A00_common.controller;

import com.enercomn.jwt.TokenResolver;
import com.enercomn.utils.ResultMsg;
import com.enercomn.Enum.ResultStatusCode;
import com.enercomn.web.A00_common.bean.A00WenxinBean;
import com.enercomn.web.A00_common.bean.A01LoginBean;
import com.enercomn.web.A00_common.service.A00WeixinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户登录controller层
 * 创建时间2020-02-25
 * cj
 */
@Slf4j
@Api(value = "a00微信相关")
@RestController
@RequestMapping("/A00WeixinController")
public class A00WeixinController {

    @Resource
    protected WxMpService wxMpService;
    
    //获取token值
    @Resource
    TokenResolver tokenResolver;

    @Resource
    protected WxMpUserService wxMpUserService;

    @Resource
    protected A00WeixinService a00WeixinService;




    /**
     * @author Oliver
     * @param a01D01SelectParamBean
     * @return A00DictionaryBean
     */
    @ApiOperation(value = "根据Code获取相关微信信息", notes = "根据Code获取相关微信信息", response = ResultMsg.class)
    @PostMapping(value = "/getWeixinUserInfo")
    public ResultMsg getWeixinUserInfo(@RequestBody A00WenxinBean a01D01SelectParamBean
    ) {
        log.info("a01D01SelectParamBean===="+a01D01SelectParamBean.toString());
        WxMpUser wxMpUser = null;
        ResultMsg resultMsg =null;
        try {
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = this.wxMpService.oauth2getAccessToken(a01D01SelectParamBean.getCode());

            wxMpUser = this.wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
            log.info("wxMpUser===="+wxMpUser.toString());


        } catch (WxErrorException e) {
            e.printStackTrace();
            log.info("WxErrorException===="+e.toString());
            resultMsg = new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(), e.toString());
        }
        log.info("resultMsg===="+resultMsg.toString());
        return resultMsg;
    }

    /**
     * @author Oliver
     * @return A00DictionaryBean
     */
    @ApiOperation(value = "checkSignature", notes = "checkSignature", response = ResultMsg.class)
    @GetMapping(value = "/checkSignature", produces = {"application/json;charset=UTF-8"})
    public String checkSignature(@RequestParam(value = "signature") String signature
            ,@RequestParam(value = "timestamp") String timestamp
            ,@RequestParam(value = "nonce") String nonce
            ,@RequestParam(value = "echostr") String echostr
    ) {

        if (!this.wxMpService.checkSignature(timestamp, nonce, signature)) {
            // 消息签名不正确，说明不是公众平台发过来的消息
            return "\"非法请求\"";
        }


        if (org.apache.commons.lang3.StringUtils.isNotBlank(echostr)) {
            // 说明是一个仅仅用来验证的请求，回显echostr
            log.info("echostr===="+echostr);
            return echostr;
        }
        return "";
    }


    /**
     * @author Oliver
     * @return A00DictionaryBean
     */
    @ApiOperation(value = "checkSignature", notes = "checkSignature", response = ResultMsg.class)
    @PostMapping(value = "/checkSignature", produces = {"application/json;charset=UTF-8"})
    public String checkSignaturePost(@RequestBody String body,HttpServletRequest req, HttpServletResponse resp) {

        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        resp.setCharacterEncoding("UTF-8");
        String message = "success";
        log.info(body.toString());
        try {
            WxMpXmlMessage wxMessage = WxMpXmlMessage.fromXml(body);
            log.info(wxMessage.toString());
            switch (wxMessage.getEvent()){
                case "subscribe":
                    WxMpUser wxMpUser =wxMpUserService.userInfo(wxMessage.getFromUser());
                    log.info(wxMpUser.toString());
                    a00WeixinService.insertUser(wxMpUser.getOpenId(),wxMpUser.getNickname(),wxMpUser.getHeadImgUrl());
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("关注微信公众号自动回复的消息内容为:"+message);

        return "";
    }





    public Object userlogin(@RequestBody A01LoginBean a01LoginBean){
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser("oIKuXws4DOsSP_eQjqDYFKH4wV1s")
                .templateId("nv4_N9krDrren7iSKYJtsMzibMt6MdRUjOpncYA1kjI")
                .url("http://enercomnsys.enercomn.com/?#/login")
                .build();
        templateMessage.addData(new WxMpTemplateData("area", "2号楼机房", "#168cf7"));
        templateMessage.addData(new WxMpTemplateData("element", "温湿度传感器", "#168cf7"));
        templateMessage.addData(new WxMpTemplateData("module", "温度", "#168cf7"));
        templateMessage.addData(new WxMpTemplateData("tag", "30", "#168cf7"));
        templateMessage.addData(new WxMpTemplateData("live", "50", "#ff461f"));

        List<WxMpTemplateData> list =new ArrayList<>();
        list.add(new WxMpTemplateData("area", "2号楼机房", "#168cf7"));
        list.add(new WxMpTemplateData("element", "温湿度传感器", "#168cf7"));
        list.add(new WxMpTemplateData("module", "温度", "#168cf7"));
        list.add(new WxMpTemplateData("tag", "30", "#168cf7"));
        list.add(new WxMpTemplateData("live", "50", "#ff461f"));


        try{
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        }catch (WxErrorException e){
            log.error(e.getMessage());
        }

        return "";
    }

}
