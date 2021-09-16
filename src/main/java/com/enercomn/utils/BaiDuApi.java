package com.enercomn.utils;

import com.baidu.aip.ocr.AipOcr;
import com.enercomn.Enum.ResultStatusCode;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;


/**
 * @author Oliver
 * @description 百度OCR工具
 * @date   2019/6/5 9:16
 * @version 1.0
 */

@Slf4j
public class BaiDuApi {

    private static final String APP_ID = "11405494";
    private static final String API_KEY = "kw3fkr35tNUeSI9vgt1gLSDI";
    private static final String SECRET_KEY = "TRuDIuXy9aXE1VLlXOcP9RwNFl2jyLBw";


    public static ResultMsg ocr(HttpServletRequest request, String templateSign){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String path =  "C:/OCR/" +sdf.format(new Date());
        String oldFileName;
        String suffix;
        String fileName;
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            // 取出传过来的文件
            MultipartFile mFile = multipartRequest.getFile("file");

            assert mFile != null;
            oldFileName = mFile.getOriginalFilename();
           //获取文件扩展名
            assert oldFileName != null;
            suffix = oldFileName.substring(oldFileName.lastIndexOf('.')) ;
            //生成新的文件名
            fileName = UUID.randomUUID().toString().replace("-", "") + suffix;
            File file = new File(path, fileName);
            File pakageFile = new File(path);
            if (!pakageFile.exists() && !pakageFile.isDirectory()) {
                // 创建文件夹
                boolean a = pakageFile.mkdirs();
            }
            mFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultMsg(ResultStatusCode.OCR_ERROR.getResultCode(), ResultStatusCode.OCR_ERROR.getResultMessage());
        }
        String picPath = path + "/" + fileName;
        JSONObject jsonObject = reqBaiDuOcr(picPath,templateSign);
        //log.debug("BaiDu_OCR :" + jsonObject.toString());
        return new ResultMsg(ResultStatusCode.OK.getResultCode(), ResultStatusCode.OK.getResultMessage(),jsonObject.toString());

    }



    private static JSONObject reqBaiDuOcr(String imgPath, String templateSign){
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        //templateSign 您在自定义文字识别平台制作的模板的ID
        //classifierId 分类器Id。这个参数和templateSign至少存在一个，优先使用templateSign。存在templateSign时，表示使用指定模板；如果没有templateSign而有classifierId，表示使用分类器去判断使用哪个模板
        HashMap<String, String> options = new HashMap<>(16);
        options.put("templateSign", "636885c64426ffd04704147f58ca675a");
        //TODO
        //options.put("templateSign", templateSign);
        //options.put("classifierId", "31232");
        return client.custom(imgPath, options);
    }

//    public static void main(String[] args) {
//        // 初始化一个AipOcr
//        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
//
//        // 可选：设置网络连接参数
//        client.setConnectionTimeoutInMillis(2000);
//        client.setSocketTimeoutInMillis(60000);
//
//        //templateSign 您在自定义文字识别平台制作的模板的ID
//        //classifierId 分类器Id。这个参数和templateSign至少存在一个，优先使用templateSign。存在templateSign时，表示使用指定模板；如果没有templateSign而有classifierId，表示使用分类器去判断使用哪个模板
//        HashMap<String, String> options = new HashMap<>();
//        options.put("templateSign", "636885c64426ffd04704147f58ca675a");
////        options.put("classifierId", "31232");
//
//
//        // 参数为本地路径
//        String image = "C:\\Users\\Oliver\\Desktop\\addNodeCode.jpg";
//        JSONObject res = client.custom(image, options);
//        System.out.println(res.toString(2));
//
//
//    }

}
