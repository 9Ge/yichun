package com.enercomn.utils;


import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 *     desc  : 自定义访问对象工具类—获取对象的IP地址等信息
 * </pre>
 */
public class CusAccessObjectUtil {
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (!checkIP(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!checkIP(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!checkIP(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    private static boolean checkIP(String ip) {
        if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip)
                || ip.split(".").length != 4) {
            return false;
        }
        return true;
    }

}
