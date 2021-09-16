package com.enercomn.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@SuppressWarnings("unused")
@Component
public class FileUtils {

    private final static Logger logger = LoggerFactory.getLogger(FileUtils.class);


    public static String getContextRootPath(HttpServletRequest request) {

        String contextPath = request.getServletContext().getRealPath("/");
        logger.info("获取得到Context原始根目录为:[{}]", contextPath);
        contextPath = contextPath.replace("\\", "/");
        File file = new File(contextPath);
        if (!file.isAbsolute()) {
            file = new File(file.getAbsolutePath());
        }
        contextPath = file.getParent();
        contextPath += contextPath.endsWith("/") ? "" : "/";
        logger.info("获取得到FileUpload根目录为:[{}]", contextPath);

        return contextPath;
    }

    public static String fileUpload(MultipartFile uploadFile, String uploadUrl) {

        if (uploadFile != null && !uploadFile.isEmpty()) {
            //从配置文件获取图片上传物理根路径
            String fileUploadRootPath = uploadUrl;

            if (!fileUploadRootPath.endsWith("/")) {
                fileUploadRootPath = fileUploadRootPath + "/";
            }

            String configFileDirName = new SimpleDateFormat("yyyyMMdd").format(new Date());

            //根路径+上传目录=最终路径
            String companyAbsolutePath = fileUploadRootPath + "/" + new SimpleDateFormat("yyyyMMdd").format(new Date());
            logger.info("从配置文件获取上传文件路径为[" + companyAbsolutePath + "]");
            logger.info("创建文件夹[" + companyAbsolutePath + "]结果为[" + FileUtils.getFolder(companyAbsolutePath) + "]");
            //根据上传文件获取文件后缀
            String subFix = uploadFile.getOriginalFilename().substring(Objects.requireNonNull(uploadFile.getOriginalFilename()).lastIndexOf(".")).toLowerCase();
            //生成文件保存名称为
            String fileName = configFileDirName + "/"  + FileUtils.getFileNameByDateTime() + subFix;//customer.getUsername() + "_" +
            //获取文件上传路径
            String filePath = fileUploadRootPath + fileName;
            logger.info("开始上传并保存文件资料至[" + filePath + "]\r\n");

            //开始上传并保存文件资料
            try {
                uploadFile.transferTo(new File(filePath));
            } catch (IOException ex) {
                logger.error("上传文件出错！错误原因[" + ex.toString() + "]", ex);
                return null;
            }

            return fileName;
        }
        return null;
    }

    /**
     * 根据字符串创建本地目录 并按照日期建立子目录返回
     *
     * @param path 需要创建的文件目录
     * @return 创建是否成功的文件目录
     */
    private static boolean getFolder(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            try {
                return dir.mkdirs();
            } catch (Exception e) {
                logger.error("生成文件夹报错！", e);
            }
        }
        return true;
    }

    /**
     * 文件重命名
     *
     * @param oldFile    需要重命名的文件【需完整路径】
     * @param renamePath 重命名后的名称【需完整路径】
     * @return 重命名情况
     */
    private static boolean renameOldFile(String oldFile, String renamePath) {
        File file = new File(oldFile);
        if (file.exists()) {
            try {
                if (file.renameTo(new File(renamePath))) {
                    logger.info("修改原文件名称为[" + renamePath + "]");
                    return true;
                } else {
                    logger.debug("修改失败，原文件不存在！");
                    return false;
                }
            } catch (Exception ex) {
                logger.error("修改文件名称失败!", ex);
                return false;
            }
        } else {
            logger.debug("原文件不存在，无法重命名");
            return false;
        }
    }

    /**
     * 通过日期时间生成一个时间戳
     *
     * @return 返回固定格式的时间戳
     */
    private static String getFileNameByDateTime() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
                + "_"
                + new Random().nextInt(1000);
    }

}
