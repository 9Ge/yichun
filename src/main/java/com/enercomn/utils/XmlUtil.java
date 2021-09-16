package com.enercomn.utils;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.*;

/**
 * XML工具类
 * Created by kituser on 2018-04-27
 */
public class XmlUtil {
    private static final String HEAD = "head";
    private static final String BODY = "body";

    /**
     * 解析XML字符串
     *
     * @param xml
     * @return
     * @throws
     */
    public static Map<String, Object> parseXmlStr(String xml)
            throws DocumentException {
        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        return parseElement(root);
    }

    /**
     * 解析Element
     *
     * @param root
     * @return
     */
    @SuppressWarnings("unchecked")
    private static Map<String, Object> parseElement(Element root) {
        String rootName = root.getName();
        Iterator<Element> rootItor = root.elementIterator();
        Map<String, Object> rMap = new HashMap<>();
        List<Map<String, Object>> rList = new ArrayList<>();
        Map<String, Object> rsltMap = null;
        while (rootItor.hasNext()) {
            Element tmpElement = rootItor.next();
            String name = tmpElement.getName();
            if (rsltMap == null || (!HEAD.equals(name) && !BODY.equals(name)
                    && !tmpElement.isTextOnly())) {
                if (!HEAD.equals(name) && !BODY.equals(name)
                        && !tmpElement.isTextOnly() && rsltMap != null) {
                    rList.add(rsltMap);
                }
                rsltMap = new HashMap<>();
            }
            if (!tmpElement.isTextOnly()) {
                Iterator<Element> headItor = tmpElement.elementIterator();
                while (headItor.hasNext()) {
                    Element hElement = headItor.next();
                    if (hElement.isTextOnly()) {
                        rsltMap.put(hElement.getName(), hElement.getTextTrim());
                    } else {
                        rsltMap.putAll(parseElement(hElement));
                    }
                }
            }
        }
        rList.add(rsltMap);
        rMap.put(rootName, rList);
        return rMap;
    }

}
