package com.enercomn.utils.dingTalk;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.enercomn.utils.StringUtils;
import com.taobao.api.ApiException;

/**
 * 钉钉推送工具类
 * lyh
 */
public class DingTalkUtils {
    //钉钉请求密钥
    private static final String TOKEN_KEY = "5cf24e1fad387d4dddf8dad8560c2597b2d5f7115897338caf68c26652e469e2";

//    public static void main(String[] args) throws ApiException, Exception {
//
//        DingTalkBean dingTalkBean = new DingTalkBean();
//        dingTalkBean.setMsgType(2);
//        dingTalkBean.setLinkOrMarkDownTitle("#### 报警消息推送");
//        dingTalkBean.setMarkDownText("#### 报警消息推送\n" +
//                "> 报警区域: 一楼南门机房\n\n" +
//                "> 报警元件: 温湿度传感器\n\n" +
//                "> 报警模块: 温度\n\n" +
//                "> 报警设定值: 25℃\n\n" +
//                "> 实时值: 39℃ \n\n" +
//                "###### 2020年5月22日10点20分 [点击查看](http://enercomnsys.enercomn.com/) \n");
//
//        DingTalkUtils.dingTalkMess(dingTalkBean);
////
////        Long timestamp = System.currentTimeMillis();
////        String secret = "this is secret";
////
////        String stringToSign = timestamp + "\n" + secret;
////        Mac mac = Mac.getInstance("HmacSHA256");
////        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
////        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
////        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
////        System.out.println(sign);
//    }

    public static void dingTalkMess(DingTalkBean dingTalkBean) throws ApiException {
        //https://oapi.dingtalk.com/robot/send?access_token
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token="+TOKEN_KEY);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        //信息类型推送
        if (dingTalkBean.getMsgType()==0) {
            //设置信息类型
            request.setMsgtype("text");
            //创建信息
            OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
            //文本信息
            text.setContent(StringUtils.isNotEmpty(dingTalkBean.getTextContent()) ? dingTalkBean.getTextContent() : "请填写text文本信息");
            //添加进信息
            request.setText(text);
        } else if (dingTalkBean.getMsgType()==1) {
            request.setMsgtype("link");
            OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
            //链接地址
            link.setMessageUrl(StringUtils.isNotEmpty(dingTalkBean.getLinkMessageUrl()) ? dingTalkBean.getLinkMessageUrl() : "https://www.baidu.com/");
            //图片地址
            link.setPicUrl(StringUtils.isNotEmpty(dingTalkBean.getLinkPicUrl()) ? dingTalkBean.getLinkPicUrl() : "");
            //标题名称
            link.setTitle(StringUtils.isNotEmpty(dingTalkBean.getLinkOrMarkDownTitle()) ? dingTalkBean.getLinkOrMarkDownTitle() : "请填写link标题名称");
            //信息文本
            link.setText(StringUtils.isNotEmpty(dingTalkBean.getMarkDownText()) ? dingTalkBean.getMarkDownText() : "请填写link信息文本");
            //添加进信息
            request.setLink(link);
        } else if (dingTalkBean.getMsgType()==2) {
            request.setMsgtype("markdown");
            OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
            //标题
            markdown.setTitle(StringUtils.isNotEmpty(dingTalkBean.getLinkOrMarkDownTitle()) ? dingTalkBean.getLinkOrMarkDownTitle() : "请填写markdown标题");
            //文本信息
//            markdown.setText("#### 杭州天气 @156xxxx8827\n" +
//                    "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
//                    "> ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\n" +
//                    "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n");
            //文本信息
            markdown.setText(StringUtils.isNotEmpty(dingTalkBean.getMarkDownText()) ? dingTalkBean.getMarkDownText() : "请填写markdown文本信息");

            request.setMarkdown(markdown);
        }

        //判断是否有艾特人员
        if (dingTalkBean.getAllAtList() != null && dingTalkBean.getAllAtList().size() > 0) {
            //艾特人员类
            OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
            //添加进艾特集合
            at.setAtMobiles(dingTalkBean.getAllAtList());
            //添加进信息
            request.setAt(at);
        }

        OapiRobotSendResponse response = client.execute(request);
        System.out.println(response.getErrcode());
    }
}
