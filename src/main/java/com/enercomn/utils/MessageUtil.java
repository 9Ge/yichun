package com.enercomn.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Oliver
 * @description 短信工具
 * @date 2019/5/30 12:45
 * @version 1.0
 */
@Component
@Slf4j
public class MessageUtil {
    private static final String SYMBOLS = "0123456789";

    private static final Random RANDOM = new SecureRandom();

    private static final String URL = "http://un.fresenius-kabi.com.cn:5401/Api/SMSService/SendSMS";
    //验证码创建时间key
    public static final String CREATE_TIME = "createTime";
    //用于检验 验证码3分钟有效期
    public static final Long THREE_MINUTES = 600000L;
    //验证码保存容器 手机号为key
    public static final Map<String, JSONObject> MESSAGE_CODE_MAP = new HashMap<>();

    /**
     * @author Oliver
     * @description 发送验证码，发送成功时返回验证码，失败时返回"发送失败"字符串
     * @date 2019/5/30 12:45
     * @version 1.0
     * @param phone   电话号码
     * @return code or 发送失败
     */
    public static String sendCode(String phone) {

        String code = getNonce_str();
        JSONObject s = new JSONObject();
        Base64.Encoder encoder = Base64.getEncoder();
        s.put("code1", code); // 参数为code1
        String ParamName = encoder.encodeToString(s.toJSONString().getBytes());

        JSONObject param = new JSONObject();
        param.put("PhoneNumbers", phone); // 手机号
        param.put("CompanyCode", "4033"); // 公司代码固定4033
        param.put("TemplateId", "FKSMS0049"); // 模板固定FKSMS0049
        param.put("SystemId", "3"); // SystemId固定3
        param.put("SignName", "HESafe术后调研"); // 签名固定费卡中国
        param.put("ParamName", ParamName);
        JSONObject post = HttpPostUtil.post(URL, param.toJSONString());
        if ("99".equals(post.get("code"))) {
            return code; // 发送成功时返回验证码
        } else if ("OK".equals(post.get("Code"))) {
            return code; // 发送成功时返回验证码
        } else {
            log.error("短信发送失败",post);
            return "发送失败";
        }
    }

    /**
     * 获取长度为 4 的随机数字
     */
    private static String getNonce_str() {

        char[] nonceChars = new char[4];

        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }

        return new String(nonceChars);

    }
}
