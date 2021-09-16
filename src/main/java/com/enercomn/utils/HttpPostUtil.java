package com.enercomn.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * HttpPost
 */
public class HttpPostUtil {

    /**
     * 请求杏树林
     * @param content
     * @return
     */
    public static JSONObject post(String url, String content) {
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        Map<String, String> resultMap = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            // 设置请求的header
//            httpPost.addHeader("Content-Type", "application/json;charset=" + Constant.UTF8_STRING);
//            httpPost.setHeader("Accept", "application/json");
            StringEntity entityParams = new StringEntity(content, ContentType.APPLICATION_JSON);
            //StringEntity entityParams = new StringEntity(content, Constant.UTF8_STRING);
//            entityParams.setContentEncoding(Constant.UTF8_STRING);
//            entityParams.setContentType("application/json");
            httpPost.setEntity(entityParams);
            client = HttpClients.createDefault();
            response = client.execute(httpPost);
            if (response != null && response.getEntity() != null) {
                String jsonRs = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                return JSONObject.parseObject(jsonRs);
            } else {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", "99");
                jsonObject.put("msg", "链接失败");
                return jsonObject;
            }

        } catch (Exception e) {
            e.printStackTrace();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", "99");
            jsonObject.put("msg", "发生异常");
            return jsonObject;
        }
    }
}
