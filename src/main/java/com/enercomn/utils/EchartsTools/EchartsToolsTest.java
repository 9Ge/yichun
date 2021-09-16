//package com.enercomn.utils.EchartsTools;
//
//import com.alibaba.fastjson.JSON;
//import freemarker.template.TemplateException;
//import org.apache.http.client.ClientProtocolException;
//
//import java.io.IOException;
//import java.util.HashMap;
//
//public class EchartsToolsTest {
//    public static void main(String[] args) throws ClientProtocolException, IOException, TemplateException {
//        // 变量数据
//        String title = "测试表格";
//        String[] categories = new String[] { "空压机", "BS1电源", "温度" };
//        int[] values = new int[] { 100, 10, 50 };
//
//        //表格模板参数
//        HashMap<String, Object> datas = new HashMap<>();
//        //X轴数据
//        datas.put("categories", JSON.toJSONString(categories));
//        //数据
//        datas.put("values", JSON.toJSONString(values));
//        //标题
//        datas.put("title", title);
//
//        // 按照模板生成option字符串信息
//        String option = FreemarkerUtil.generateString("option.ftl", "/", datas);
//
//        // 根据option字符串信息参数渲染base64数据
//        String base64 = EchartsUtil.generateEchartsBase64(option);
//
//        System.out.println("BASE64:" + base64);
//        //base64转图片输出
//        Base64ToImageUtil.generateImage(base64, "C:\\Users\\Enercomn\\Desktop\\test2.png");
//    }
//
//
//}
