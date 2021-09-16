package com.enercomn.utils;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/*
pom.xml
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-core</artifactId>
  <version>4.5.3</version>
</dependency>
*/
public class SendSms {
//    public static void main(String[] args) {
////        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",  "LTAIxaY6UCvKKeC1", "e2Z6uFGJPnSk9RrjQVG0uG2FOFQQo8");
////        IAcsClient client = new DefaultAcsClient(profile);
////
////        CommonRequest request = new CommonRequest();
////        request.setSysMethod(MethodType.POST);
////        request.setSysDomain("dysmsapi.aliyuncs.com");
////        request.setSysVersion("2017-05-25");
////        request.setSysAction("SendSms");
////        request.putQueryParameter("RegionId", "cn-hangzhou");
////        request.putQueryParameter("PhoneNumbers", "17601285706");
////        request.putQueryParameter("SignName", "能誉科技");
////        request.putQueryParameter("TemplateCode", "SMS_197625064");
////        request.putQueryParameter("TemplateParam", "{\"area\":\"主机房\",\"name\":\"东温湿度\",\"detail\":\"温度：100℃ 湿度：70%\"}");
////        try {
////            CommonResponse response = client.getCommonResponse(request);
////            System.out.println(response.getData());
////        } catch (ServerException e) {
////            e.printStackTrace();
////        } catch (ClientException e) {
////            e.printStackTrace();
////        }
//        SmsBean smsBean=new SmsBean();
//        smsBean.setArea("主机房");
//        smsBean.setName("东温湿度");
//        smsBean.setDetail("温度：100℃ 湿度：70%");
//        System.out.println(sendSms("18068335587",smsBean));
//    }

    public static String sendSms(String phone, SmsBean smsBean) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",  "LTAIxaY6UCvKKeC1", "e2Z6uFGJPnSk9RrjQVG0uG2FOFQQo8");
        IAcsClient client = new DefaultAcsClient(profile);
        String resultJson = JSONObject.toJSONString(smsBean);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "能誉科技");
        request.putQueryParameter("TemplateCode", "SMS_197625064");
        request.putQueryParameter("TemplateParam", resultJson);
        try {
            CommonResponse response = client.getCommonResponse(request);
            return response.getData();
        } catch (ServerException e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        } catch (ClientException e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
    }
}

