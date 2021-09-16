package com.enercomn.utils;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.spec.AlgorithmParameterSpec;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Oliver
 * @description 微信工具类
 * @date   2019/6/6 10:20
 * @version 1.0
 */
@Component
@Slf4j
public class WechatUtils {


    private static final String AES = "AES";
    private static final String AES_CBC_PKC = "AES/CBC/PKCS5Padding";
    private static final String PHONE_NUMBER = "phoneNumber";
    public static final String HTTP_METHOD_GET = "GET";
    public static final String HTTP_METHOD_POST = "POST";
    public static final String REPLACE_APP_ID = "APPID";
    public static final String REPLACE_APP_SECRET = "APPSECRET";
    public static final String WX_URL_GET_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public static final String REPLACE_ACCESS_TOKEN = "ACCESS_TOKEN";

    //微信小程序

    public static final String WX_URL_CODE_2_SESSION = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=APPSECRET&js_code=CODE&grant_type=authorization_code";
    public static final String WX_URL_CREATE_WXAQR_CODE = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=ACCESS_TOKEN";
    public static final String APP_ID = "wx075d306988fc1b65";
    public static final String APP_SECRET = "d36db0987d3e0289d9f2a54d29ff2135";
    public static final String REPLACE_CODE = "CODE";
    public static final String RETURN_OPEN_ID = "openid";
    public static final String RETURN_SESSION_KEY = "session_key";
    public static final String RETURN_ACCESS_TOKEN = "access_token";
    public static String ACCESS_TOKEN = null;
    public static Date ACCESS_TOKEN_TIME = null;

    //微信公众号

    public static final String PUBLIC_APP_ID = "wxf11ce49f9139de7f";
    public static final String PUBLIC_APP_SECRET = "83d3c04b14b9674c850044430cf47b94";
    public static String PUBLIC_ACCESS_TOKEN = null;
    public static Date PUBLIC_ACCESS_TOKEN_TIME = null;
    public static final String WX_URL_BATCH_GET_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";

    public static JSONObject request(String url, String requestMethod){
        return request(url, requestMethod,null);
    }

    public static JSONObject request(String url, String requestMethod, String outPut){
        PrintWriter out = null;
        BufferedReader in = null;
        String result = StringUtils.EMPTY;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod(requestMethod);
            // 获取URLConnection对象对应的输出流
            OutputStreamWriter outWriter = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8);
            out = new PrintWriter(outWriter);
            if(outPut != null){
                out.write(outPut);
            }
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String line;
            StringBuilder resultBuilder = new StringBuilder();
            while ((line = in.readLine()) != null) {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                resultBuilder.append(line);
            }
            result = resultBuilder.toString();
        } catch (Exception e) {
            log.error("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        log.info(result);
        return JSONObject.fromObject(result);
    }

    public static String requestFile(String url, String requestMethod, String outPut, String uploadUrl){
        PrintWriter out = null;
        InputStream in = null;
        File file;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod(requestMethod);
            // 获取URLConnection对象对应的输出流
            OutputStreamWriter outWriter = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8);
            out = new PrintWriter(outWriter);
            if(outPut != null){
                out.write(outPut);
            }
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = conn.getInputStream();
            String path = uploadUrl+"WXAQRCode/";
            File dir = new File(path);
            if (!dir.exists()) {
                try {
                    boolean a = dir.mkdirs();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            //可以是任何图片格式.jpg,.png等
            file = new File( path, new SimpleDateFormat(DateTimeUtils.YYYYMMDDHHMMSS).format(new Date())+".png");
            FileOutputStream fos = new FileOutputStream(file);
            if(in != null){
                try {
                    byte[] b = new byte[1024];
                    int nRead;
                    while ((nRead = in.read(b)) != -1) {
                        fos.write(b, 0, nRead);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                } finally {
                    try {
                        fos.flush();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return file.getPath();

    }



    /**
     * @author Oliver
     * @description 解密获取手机号
     * @date 2019/5/30 11:21
     * @version 1.0
     *  @param sessionKey 用户sessionKey
     *  @param ivStr 小程序获取到的iv
     *  @param encryptedData 小程序获取到的encryptedData数据
     *  @return 手机号
     */
    public static String decrypt(String sessionKey, String ivStr, String encryptedData) throws Exception {
        byte[] key = Base64.decodeBase64(sessionKey);
        byte[] iv = Base64.decodeBase64(ivStr);
        byte[] encData = Base64.decodeBase64(encryptedData);
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance(AES_CBC_PKC);
        SecretKeySpec keySpec = new SecretKeySpec(key, AES);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        //解析解密后的字符串
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(new String(cipher.doFinal(encData), StandardCharsets.UTF_8));
        return jsonObject.get(PHONE_NUMBER).toString();
    }


}
