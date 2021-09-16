package com.enercomn.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.enercomn.Enum.StateCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
public class RestUtil {
 
    private static class SingletonRestTemplate {
        static final RestTemplate INSTANCE = new RestTemplate();
    }
 

 
 
    public static RestTemplate getInstance() {
        return SingletonRestTemplate.INSTANCE;
    }

    /**
     * post 请求
     * @param url 请求路径
     * @param data body数据为string 格式
     * @param token JWT所需的Token
     * @return
     */
    public static String post(String url, String data, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("ContentEncoding", "UTF-8");
        headers.add("ContentType", "application/json; charset=UTF-8");
        if (token != null) {
            headers.add("Authorization", token);
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(data, headers);
        return RestUtil.getInstance().postForObject(url, requestEntity, String.class);
    }

    /**
     * post 请求
     * @param url 请求路径
     * @param data body数据为 JSONObject 格式
     * @param token JWT所需的Token
     * @return
     */
    public static String post(String url, JSONObject data, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("ContentEncoding", "UTF-8");
        headers.add("ContentType", "application/json; charset=UTF-8");
        if (token != null) {
            headers.add("Authorization", token);
        }
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(data, headers);
        return RestUtil.getInstance().postForObject(url, requestEntity, String.class);
    }

    /**
     * post 请求
     * @param url 请求路径
     * @param data body数据为json格式
     * @return
     */
    public static String post(String url, JSONObject data) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("ContentEncoding", "UTF-8");
        headers.add("ContentType", "application/json; charset=UTF-8");
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(data, headers);
        return RestUtil.getInstance().postForObject(url, requestEntity, String.class);
    }

    /**
     * post 请求
     * @param url 请求路径
     * @param data body数据为 String 格式
     * @return
     */
    public static String post(String url, String data) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("ContentEncoding", "UTF-8");
        headers.add("ContentType", "application/json; charset=UTF-8");
        HttpEntity<String> requestEntity = new HttpEntity<>(data, headers);
        return RestUtil.getInstance().postForObject(url, requestEntity, String.class);
    }

    /**
     * get 请求
     * @param url 请求路径
     * @param token JWT所需的Token，不需要的可去掉
     * @return
     */
    public static  String get(String url, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("ContentEncoding", "UTF-8");
        headers.add("ContentType", "application/json; charset=UTF-8");
        if (token != null) {
            headers.add("Authorization", token);
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = RestUtil.getInstance().exchange(url, HttpMethod.GET, requestEntity, String.class);
        String responseBody = response.getBody();
        return responseBody;
    }

    /**
     * get 请求
     * @param url 请求路径
     * @return
     */
    public static  String get(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("ContentEncoding", "UTF-8");
        headers.add("ContentType", "application/json; charset=UTF-8");
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = RestUtil.getInstance().exchange(url, HttpMethod.GET, requestEntity, String.class);
        String responseBody = response.getBody();
        return responseBody;
    }

    /**
     * 发送文件请求
     * @param url
     * @param token
     * @return
     */
    public static String file(String url, MultipartFile file, String token) {
        // 生成临时文件
        String tempFilePath = System.getProperty("java.io.tmpdir") + file.getOriginalFilename();
        File tmpFile = new File(tempFilePath);
        // 结果，抛异常就是 null
        String result = null;
        try {
            // 保存为文件
            file.transferTo(tmpFile);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            headers.setContentType(MediaType.parseMediaType("multipart/form-data;charset=UTF-8"));
            if (token != null) {
                headers.add("Authorization", token);
            }
            MultiValueMap<String,Object> param = new LinkedMultiValueMap<>();
            // 把临时文件变成 FileSystemResource
            FileSystemResource resource = new FileSystemResource(tempFilePath);
            param.add("file",resource);
            HttpEntity<MultiValueMap<String,Object>> formEntity = new HttpEntity<>(param,headers);
            result = RestUtil.getInstance().postForObject(url, formEntity, String.class);
            //删除临时文件文件
            tmpFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送文件请求
     * @param url
     * @return
     */
    public static String file(String url, MultipartFile file) {
        // 生成临时文件
        String tempFilePath = System.getProperty("java.io.tmpdir") + file.getOriginalFilename();
        File tmpFile = new File(tempFilePath);
        // 结果，抛异常就是 null
        String result = null;
        try {
            // 保存为文件
            file.transferTo(tmpFile);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            headers.setContentType(MediaType.parseMediaType("multipart/form-data;charset=UTF-8"));
            MultiValueMap<String,Object> param = new LinkedMultiValueMap<>();
            // 把临时文件变成 FileSystemResource
            FileSystemResource resource = new FileSystemResource(tempFilePath);
            param.add("file",resource);
            HttpEntity<MultiValueMap<String,Object>> formEntity = new HttpEntity<>(param,headers);
            result = RestUtil.getInstance().postForObject(url, formEntity, String.class);
            //删除临时文件文件
            tmpFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] argus){
//        String get = RestUtil.get("http://192.168.3.21:8090/");
//        System.out.println("get===========  "+get);
//        JSONObject paramJson = new JSONObject();
//        paramJson.put("host","192.168.1.2");
//       // paramJson.put("port",controlBean.getCPort());
//        paramJson.put("addressCode","Date0001");
//        paramJson.put("value","999");
//        //String data = "{\"host\":\"192.168.1.2\",\"addressCode\":\"Date0001\",\"value\":\"999\"}";
//        String result = RestUtil.post("http://192.168.3.124:8090/writePlcPoint", paramJson);
//        JSONObject resultJson = JSONObject.parseObject(result);
//        System.out.println("post===========  result:"+resultJson.getString("result") + " errorMsg:"+resultJson.getString("errorMsg"));


//        String result = "{\"timeList\":[\"2020-11-26\",\"2020-11-27\",\"2020-11-28\",\"2020-11-30\",\"2020-12-01\",\"2020-12-02\",\"2020-12-03\",\"2020-12-04\",\"2020-12-05\",\"2020-12-06\",\"2020-12-07\",\"2020-12-08\",\"2020-12-09\",\"2020-12-10\",\"2020-12-14\",\"2020-12-15\"],\"attrAvgList\":[{\"dataList\":[{\"avgData\":\"200.95804195804195\",\"dataTime\":\"2020-11-26\"},{\"avgData\":\"248.5174825174825\",\"dataTime\":\"2020-11-27\"},{\"avgData\":\"288.85714285714283\",\"dataTime\":\"2020-11-28\"},{\"avgData\":\"271.3529411764706\",\"dataTime\":\"2020-11-30\"},{\"avgData\":\"200.84328358208955\",\"dataTime\":\"2020-12-01\"},{\"avgData\":\"210.2958579881657\",\"dataTime\":\"2020-12-02\"},{\"avgData\":\"247.52173913043478\",\"dataTime\":\"2020-12-03\"},{\"avgData\":\"178.74166666666667\",\"dataTime\":\"2020-12-04\"},{\"avgData\":\"183.41666666666666\",\"dataTime\":\"2020-12-05\"},{\"avgData\":\"97.71666666666667\",\"dataTime\":\"2020-12-06\"},{\"avgData\":\"136.0090909090909\",\"dataTime\":\"2020-12-07\"},{\"avgData\":\"146.32758620689654\",\"dataTime\":\"2020-12-08\"},{\"avgData\":\"165.99107142857142\",\"dataTime\":\"2020-12-09\"},{\"avgData\":\"196.26887661141805\",\"dataTime\":\"2020-12-10\"},{\"avgData\":\"150.7391304347826\",\"dataTime\":\"2020-12-14\"},{\"avgData\":\"121.92647058823529\",\"dataTime\":\"2020-12-15\"}],\"name\":\"南厂焊接环境监控\",\"temId\":\"6b8e50c2c42411eab52a00163e04e0df\"},{\"dataList\":[{\"avgData\":\"34.51048951048951\",\"dataTime\":\"2020-11-26\"},{\"avgData\":\"50.28671328671329\",\"dataTime\":\"2020-11-27\"},{\"avgData\":\"56.714285714285715\",\"dataTime\":\"2020-11-28\"},{\"avgData\":\"50.141176470588235\",\"dataTime\":\"2020-11-30\"},{\"avgData\":\"42.3134328358209\",\"dataTime\":\"2020-12-01\"},{\"avgData\":\"25.4792899408284\",\"dataTime\":\"2020-12-02\"},{\"avgData\":\"55.05797101449275\",\"dataTime\":\"2020-12-03\"},{\"avgData\":\"41.75833333333333\",\"dataTime\":\"2020-12-04\"},{\"avgData\":\"45.925\",\"dataTime\":\"2020-12-05\"},{\"avgData\":\"37.69166666666667\",\"dataTime\":\"2020-12-06\"},{\"avgData\":\"42.57272727272727\",\"dataTime\":\"2020-12-07\"},{\"avgData\":\"50.16379310344828\",\"dataTime\":\"2020-12-08\"},{\"avgData\":\"45.589285714285715\",\"dataTime\":\"2020-12-09\"},{\"avgData\":\"70.52302025782689\",\"dataTime\":\"2020-12-10\"},{\"avgData\":\"24.217391304347824\",\"dataTime\":\"2020-12-14\"},{\"avgData\":\"33.30882352941177\",\"dataTime\":\"2020-12-15\"}],\"name\":\"北厂环境监控\",\"temId\":\"c1807f97c36011eab52a00163e04e0df\"},{\"dataList\":[{\"avgData\":\"53.02797202797203\",\"dataTime\":\"2020-11-26\"},{\"avgData\":\"79.25174825174825\",\"dataTime\":\"2020-11-27\"},{\"avgData\":\"88.67857142857143\",\"dataTime\":\"2020-11-28\"},{\"avgData\":\"103.62352941176471\",\"dataTime\":\"2020-11-30\"},{\"avgData\":\"73.71641791044776\",\"dataTime\":\"2020-12-01\"},{\"avgData\":\"60.603550295857985\",\"dataTime\":\"2020-12-02\"},{\"avgData\":\"100.08695652173913\",\"dataTime\":\"2020-12-03\"},{\"avgData\":\"74.825\",\"dataTime\":\"2020-12-04\"},{\"avgData\":\"68.95\",\"dataTime\":\"2020-12-05\"},{\"avgData\":\"52.975\",\"dataTime\":\"2020-12-06\"},{\"avgData\":\"84.95454545454545\",\"dataTime\":\"2020-12-07\"},{\"avgData\":\"80.40517241379311\",\"dataTime\":\"2020-12-08\"},{\"avgData\":\"49.285714285714285\",\"dataTime\":\"2020-12-09\"},{\"avgData\":\"110.73848987108656\",\"dataTime\":\"2020-12-10\"},{\"avgData\":\"59.06521739130435\",\"dataTime\":\"2020-12-14\"},{\"avgData\":\"61.86764705882353\",\"dataTime\":\"2020-12-15\"}],\"name\":\"装配车间环境监控\",\"temId\":\"d9954f05c4c811eab52a00163e04e0df\"},{\"dataList\":[{\"avgData\":\"37.64335664335665\",\"dataTime\":\"2020-11-26\"},{\"avgData\":\"45.06293706293706\",\"dataTime\":\"2020-11-27\"},{\"avgData\":\"56.660714285714285\",\"dataTime\":\"2020-11-28\"},{\"avgData\":\"74.70588235294117\",\"dataTime\":\"2020-11-30\"},{\"avgData\":\"59.850746268656714\",\"dataTime\":\"2020-12-01\"},{\"avgData\":\"47.366863905325445\",\"dataTime\":\"2020-12-02\"},{\"avgData\":\"65.91304347826087\",\"dataTime\":\"2020-12-03\"},{\"avgData\":\"53.416666666666664\",\"dataTime\":\"2020-12-04\"},{\"avgData\":\"67.29166666666667\",\"dataTime\":\"2020-12-05\"},{\"avgData\":\"50.06666666666667\",\"dataTime\":\"2020-12-06\"},{\"avgData\":\"67.07272727272728\",\"dataTime\":\"2020-12-07\"},{\"avgData\":\"73.06896551724138\",\"dataTime\":\"2020-12-08\"},{\"avgData\":\"40.598214285714285\",\"dataTime\":\"2020-12-09\"},{\"avgData\":\"107.11049723756906\",\"dataTime\":\"2020-12-10\"},{\"avgData\":\"26.565217391304348\",\"dataTime\":\"2020-12-14\"},{\"avgData\":\"34.61029411764706\",\"dataTime\":\"2020-12-15\"}],\"name\":\"南厂电泳环境监控\",\"temId\":\"df1c91f8c4c811eab52a00163e04e0df\"}]}";
//        JSONObject resultJson = JSONObject.parseObject(result);
////        JSONArray timeArray = resultJson.getJSONArray("timeList");
////        timeArray.remove(0);
////        timeArray.add("2020-12-16");
//        System.out.println(resultJson.toJSONString());
//        System.out.println(StringUtils.getString(resultJson));
//        JSONObject addNodeCode = new JSONObject();
//        addNodeCode.put("count",1);
//        System.out.println(addNodeCode.getBigDecimal("count").toPlainString());

//        String message = "{\"len\":74,\"values\":[{\"objectId\":{\"type\":2,\"instance\":3009814},\"values\":[{\"id\":85,\"index\":4294967295,\"value\":[{\"value\":111,\"type\":4}]}]},{\"objectId\":{\"type\":2,\"instance\":3009815},\"values\":[{\"id\":85,\"index\":4294967295,\"value\":[{\"value\":999,\"type\":4}]}]},{\"objectId\":{\"type\":5,\"instance\":3009817},\"values\":[{\"id\":85,\"index\":4294967295,\"value\":[{\"value\":1,\"type\":9}]}]},{\"objectId\":{\"type\":5,\"instance\":3009818},\"values\":[{\"id\":85,\"index\":4294967295,\"value\":[{\"value\":0,\"type\":9}]}]},{\"objectId\":{\"type\":2,\"instance\":3009819},\"values\":[{\"id\":85,\"index\":4294967295,\"value\":[{\"value\":789,\"type\":4}]}]}]}";
//
//        JSONObject jsonObject = new JSONObject();
//        List<String> keyList = new ArrayList<>();
//        //处理消息体 格式 {"len":74,"values":[{"objectId":{"type":2,"instance":3009814},"values":[{"id":85,"index":4294967295,"value":[{"value":111,"type":4}]}]}]}
//        JSONObject messageJson = JSON.parseObject(message);
//        JSONArray valueJsonArray = messageJson.getJSONArray("values");
//        for(int i = 0 ; i < valueJsonArray.size() ; i++){
//            JSONObject valuesJson =  valueJsonArray.getJSONObject(i);
//            JSONObject idJson = valuesJson.getJSONObject("objectId");
//            JSONArray valueTwoJsonArray = valuesJson.getJSONArray("values");
//            JSONObject valueJson = valueTwoJsonArray.getJSONObject(0).getJSONArray("value").getJSONObject(0);
//
//            jsonObject.put(idJson.getString("instance"),valueJson.getString("value"));
//            keyList.add(idJson.getString("instance"));
//        }
//
//        System.out.println(jsonObject.toJSONString());
//        System.out.println(keyList.toString());
        /**
         {
         "var":"DB1,R200",
         "ip":"192.168.1.25",
         "value":777.444
         }
         */
        JSONObject requestData = new JSONObject();
        //String objectId = "{\"type\":2,\"instance\":3009814}"
        //BigDecimal value = new BigDecimal("5.210453687");
        requestData.put("deviceIPAddress","192.168.1.100");
        requestData.put("objectId",JSONObject.parse("{\"type\":2,\"instance\":3009814}"));
        requestData.put("propertyId",85);
        requestData.put("values", JSONArray.parse("[{\"type\":4,\"value\":190}]"));
        JSONObject response = JSONObject.parseObject(post("http://192.168.3.137:1880/writeBACnet", requestData));
        if(StateCode.SUCCESS.getCode().equals(response.getString("state"))){
           log.info("成功："+response.getString("state"));
        }else {
            log.info("失败："+response.getString("errorMsg"));

        }

    }
}