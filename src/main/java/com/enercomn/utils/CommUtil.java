package com.enercomn.utils;


import com.alibaba.fastjson.JSONObject;
import com.serotonin.modbus4j.code.DataType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Oliver
 * @description 工具类
 * @date   2019/6/4 16:39
 * @version 1.0
 */
@Slf4j
public class CommUtil {

	/**
	 * 默认地球半径
	 */
	private static final double EARTH_RADIUS = 6371;

	/**
	 * 计算经纬度点对应正方形的四个边界经纬度
	 * @param longitude 精度
	 * @param latitude	纬度
	 * @param distance  距离
	 * @return  Map<String, Double>
	 */
	public static Map<String, Double> returnLLSquareBoundary(double longitude, double latitude, double distance) {
		Map<String, Double> squareMap = new HashMap<>();

		// 计算经度弧度,从弧度转换为角度
		double dLongitude = 2 * (Math.asin(Math.sin(distance/ (2 * EARTH_RADIUS))/ Math.cos(Math.toRadians(latitude))));
		dLongitude = Math.toDegrees(dLongitude);

		// 计算纬度角度
		double dLatitude = distance / EARTH_RADIUS;
		dLatitude = Math.toDegrees(dLatitude);

		//四个边界
		double topLat = latitude + dLatitude;
		double bottomLat = latitude - dLatitude;
		double rightLong = longitude + dLongitude;
		double leftLong = longitude - dLongitude;

		squareMap.put("topLat", topLat);
		squareMap.put("bottomLat", bottomLat);
		squareMap.put("rightLong", rightLong);
		squareMap.put("leftLong", leftLong);

		return squareMap;
	}

	public static String first2low(String str) {
		String s;
		s = str.substring(0, 1).toLowerCase() + str.substring(1);
		return s;
	}

	public static String first2upper(String str) {
		String s;
		s = str.substring(0, 1).toUpperCase() + str.substring(1);
		return s;
	}

	public static List<String> str2list(String s) throws IOException {
		List<String> list = new ArrayList<>();
		if (s != null && !s.equals(StringUtils.EMPTY)) {
			StringReader fr = new StringReader(s);
			BufferedReader br = new BufferedReader(fr);
			String aline;
			while ((aline = br.readLine()) != null) {
				list.add(aline);
			}
		}
		return list;
	}

	public static String decode(String s) throws UnsupportedEncodingException {
		String ret;
		ret = URLDecoder.decode(s.trim(), StandardCharsets.UTF_8.toString());
		return ret;
	}

	public static String encode(String s) throws UnsupportedEncodingException {
		String ret;
		ret = URLEncoder.encode(s.trim(), StandardCharsets.UTF_8.toString());
		return ret;
	}

	public static String convert(String str, String coding) {
		String newStr = StringUtils.EMPTY;
		if (str != null) {
			try {
				newStr = new String(str.getBytes(StandardCharsets.ISO_8859_1), coding);
			} catch (Exception e) {
				return newStr;
			}
		}
		return newStr;
	}


	public static boolean isNotNull(Object obj) {
		return obj != null && !obj.toString().equals(StringUtils.EMPTY);
	}


	public static int null2Int(Object s) {
		int v = 0;
		if (s != null){
			try {
				v = Integer.parseInt(s.toString());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
			
		return v;
	}

	public static float null2Float(Object s) {
		float v = 0.0f;
		if (s != null){
			try {
				v = Float.parseFloat(s.toString());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return v;
	}

	public static double null2Double(Object s) {
		double v = 0.0;
		if (s != null){
			try {
				v = Double.parseDouble(null2String(s));
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return v;
	}

	public static boolean null2Boolean(Object s) {
		boolean v = false;
		if (s != null){
			try {
				v = Boolean.parseBoolean(s.toString());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return v;
	}

	public static String null2String(Object s) {
		return s == null ? StringUtils.EMPTY : s.toString().trim();
	}

	public static Long null2Long(Object s) {
		long v = -1L;
		if (s != null){
			try {
				v = Long.parseLong(s.toString());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return v;
	}


	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.equals("0:0:0:0:0:0:0:1")) {
			java.net.InetAddress addr = null;
			try {
				addr = java.net.InetAddress.getLocalHost();
			} catch (java.net.UnknownHostException e) {
				e.printStackTrace();
			}
			// 获得本机IP
			ip = CommUtil.null2String(addr.getHostAddress());
		}
		return ip;
	}

	public static int indexOf(String s, String sub) {
		return s.trim().indexOf(sub.trim());
	}


	/**
	 * java将字符串转换成可执行代码 工具类
	 *
	 * @param jexlExp
	 * @param map
	 * @return
	 */
	public static String convertToCode (String jexlExp, Map<String, Object> map){
		JexlEngine jexl = new JexlEngine();
		Object obj = 0;
		try{
			Expression expression = jexl.createExpression(jexlExp);
			JexlContext jc = new MapContext();
			for (String key : map.keySet()) {
				jc.set(key, new BigDecimal(map.get(key)==null?"0":map.get(key).toString()));
			}
			obj = (Object) expression.evaluate(jc);
		}catch (Exception e){
			log.error(jexlExp + " 计算异常");
			return String.valueOf(obj);
		}

		if (null == obj) {
			return "";
		}

		if (obj.getClass().toString().indexOf("Double") != -1) {
			Double result = (Double) obj;
			return BigDecimal.valueOf(result).toString();
		}else {
			return String.valueOf(obj);
		}

	}

	/**
	 * java将字符串转换成可执行代码 工具类
	 *
	 * @param jexlExp
	 * @return
	 */
	public static String convertToCode (String jexlExp) {
		JexlEngine jexl = new JexlEngine();
		Object obj = 0;
		try{
			Expression expression = jexl.createExpression(jexlExp);
			JexlContext jc = new MapContext();
			obj = (Object) expression.evaluate(jc);
		}catch (Exception e){
			log.error(jexlExp + " 计算异常");
			return String.valueOf(obj);
		}

		if (null == obj) {
			return "";
		}

		if (obj.getClass().toString().indexOf("Double") != -1) {
			Double result = (Double) obj;
			return BigDecimal.valueOf(result).toString();
		}else {
			return String.valueOf(obj);
		}

	}

	/**
	 * java将字符串转换成可执行代码 工具类
	 *
	 * @param jexlExp
	 * @return
	 */
	public static String convertToCode (String jexlExp, int accuracy){
		JexlEngine jexl = new JexlEngine();
		Expression expression = jexl.createExpression(jexlExp);
		JexlContext jc = new MapContext();

		Object obj = (Object) expression.evaluate(jc);
		if (null == obj) {
			return "";
		}

		if (obj.getClass().toString().indexOf("Double") != -1) {
			Double result = (Double) obj;
			return BigDecimal.valueOf(result).setScale(accuracy, BigDecimal.ROUND_HALF_UP).toString();
		}else {
			return new BigDecimal(String.valueOf(obj)).setScale(accuracy, BigDecimal.ROUND_HALF_UP).toString();
		}

	}

	/**
	 * java将字符串转换成可执行代码 工具类
	 * @param jexlExp
	 * @return
	 */
	public static Boolean convertToCodeForBoolean (String jexlExp){
		try{
			JexlEngine jexl = new JexlEngine();
			Expression expression = jexl.createExpression(jexlExp);
			JexlContext jc = new MapContext();
			Boolean obj = (Boolean) expression.evaluate(jc);
			return obj;
		}catch (Exception e){
			log.error("convertToCodeForBoolean===="+jexlExp,e);
			return false;
		}


	}

	/**
	 * 获取精度
	 * @param json
	 * @param key
	 */
	public static void getAccuracy(JSONObject json, String numberType, String accuracy, String key) {
		try{
			if("1".equals(numberType) || "2".equals(numberType)){
				json.put(key, json.getBigDecimal(key).setScale(0, BigDecimal.ROUND_HALF_UP).longValue());
			}else if ("3".equals(numberType) || "4".equals(numberType)){
				if(StringUtils.isNotEmpty(accuracy)){
					json.put(key, json.getBigDecimal(key).setScale(Integer.parseInt(accuracy), BigDecimal.ROUND_HALF_UP));
				}else{
					//默认保留两位小数
					if(StringUtils.isNotEmpty(json.getString(key))){
						if(null == json.getBigDecimal(key)){
							json.put(key, null);
						}else{
							json.put(key, json.getBigDecimal(key).setScale(2, BigDecimal.ROUND_HALF_UP));
						}
					}

				}
			}else if ("8".equals(numberType)){

				if("true".equals(json.getString(key).toLowerCase()) || "on".equals(json.getString(key).toLowerCase())){
					json.put(key,"1");
				}else if("false".equals(json.getString(key).toLowerCase()) || "off".equals(json.getString(key).toLowerCase())){
					json.put(key,"0");
				}

			}
		}catch (Exception e){
			log.error("转换精度异常",e);
		}
	}

	/**
	 * 获取精度
	 * @param data
	 */
	public static String getAccuracy(String data, String accuracy) {
		try {
			if (StringUtils.isEmpty(data)){
				return "0";
			}
			if(StringUtils.isNotEmpty(accuracy)){
				if("0".equals(accuracy)){
					data = StringUtils.getString(new BigDecimal(data).setScale(0, BigDecimal.ROUND_HALF_UP).longValue());
				}else if ("1".equals(accuracy)){
					data = StringUtils.getString(new BigDecimal(data).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
				}else if ("2".equals(accuracy)){
					data = StringUtils.getString(new BigDecimal(data).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				}else if ("3".equals(accuracy)){
					data = StringUtils.getString(new BigDecimal(data).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
				}else if ("4".equals(accuracy)){
					data = StringUtils.getString(new BigDecimal(data).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
				}else if ("5".equals(accuracy)){
					data = StringUtils.getString(new BigDecimal(data).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue());
				}else if ("8".equals(accuracy)){
					if("true".equals(data.toLowerCase()) || "on".equals(data.toLowerCase().toLowerCase()) ){
						data = "1";
					}else if("false".equals(data.toLowerCase()) || "off".equals(data.toLowerCase())){
						data = "0";
					}
				}
			}else{
				if("true".equals(data.toLowerCase()) || "on".equals(data.toLowerCase().toLowerCase()) ){
					data = "1";
				}else if("false".equals(data.toLowerCase()) || "off".equals(data.toLowerCase())){
					data = "0";
				}
			}
		}catch (Exception e){
			throw  new RuntimeException("调整数据精度异常，accuracy："+accuracy+" ,data:"+data+" ,异常原因：",e);
		}

		return data;
	}



	/**
	 * 获取精度
	 * @param data
	 */
	public static String getAccuracy(String data, String accuracy,String numberType) {
		try {
			if (StringUtils.isEmpty(data)){
				return "0";
			}
			if("8".equals(numberType)){
				if("true".equals(data.toLowerCase()) || "on".equals(data.toLowerCase())){
					return  "1";
				}else if("false".equals(data.toLowerCase()) || "off".equals(data.toLowerCase())){
					return  "0";
				}
			}

			if(StringUtils.isNotEmpty(accuracy)){
				if("0".equals(accuracy)){
					data = StringUtils.getString(new BigDecimal(data).setScale(0, BigDecimal.ROUND_HALF_UP).longValue());
				}else if ("1".equals(accuracy)){
					data = StringUtils.getString(new BigDecimal(data).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
				}else if ("2".equals(accuracy)){
					data = StringUtils.getString(new BigDecimal(data).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				}else if ("3".equals(accuracy)){
					data = StringUtils.getString(new BigDecimal(data).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
				}else if ("4".equals(accuracy)){
					data = StringUtils.getString(new BigDecimal(data).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
				}else if ("5".equals(accuracy)){
					data = StringUtils.getString(new BigDecimal(data).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue());
				}
			}else{
				data = StringUtils.getString(new BigDecimal(data).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
		}catch (Exception e){
			log.error("调整数据精度异常，accuracy："+accuracy+" ,data:"+data+" ,异常原因：",e);
		}

		return data;
	}

	public static Integer selectModBusNumberType(Integer cTransId, Integer numberType){
		if(null == cTransId){
			cTransId = 0;
		}
		Integer dataType = 0;
		if(0 == cTransId) {//不反转
			switch (numberType) {
				case 1://双字节有符号
					dataType = DataType.TWO_BYTE_INT_SIGNED;
					break;
				case 2://双字节无符号
					dataType = DataType.TWO_BYTE_INT_UNSIGNED;
					break;
				case 3://四字节浮点
					dataType = DataType.FOUR_BYTE_FLOAT;
					break;
				case 4://四字节无符号
					dataType = DataType.FOUR_BYTE_INT_UNSIGNED;
					break;
				case 5://四字节有符号
					dataType = DataType.FOUR_BYTE_INT_SIGNED;
					break;
				default://四字节浮点
					dataType = DataType.FOUR_BYTE_FLOAT;
					break;
			}
			log.debug("不反转:》》》dataType："+dataType+">>cTransId:"+cTransId+"numberType:"+numberType);

		}else if(1 == cTransId){//反转

			switch (numberType) {
				case 1://双字节有符号的交换
					dataType = DataType.TWO_BYTE_INT_SIGNED_SWAPPED;
					break;
				case 2://四字节无符号交换
					dataType = DataType.FOUR_BYTE_INT_UNSIGNED_SWAPPED;
					break;
				case 3://双字节无符号交换
					dataType =  DataType.FOUR_BYTE_FLOAT_SWAPPED;
					break;
				case 4://四字节有符号交换
					dataType = DataType.TWO_BYTE_INT_UNSIGNED_SWAPPED;
					break;
				case 5://四字节浮点交换
					dataType = DataType.FOUR_BYTE_INT_SIGNED_SWAPPED;
					break;
			}
			log.debug("反转:》》》dataType："+dataType+">>cTransId:"+cTransId+"numberType:"+numberType);
		}else  if (3 == cTransId){
			dataType = numberType;

			log.debug("dataType = numberType:》》》dataType："+dataType+">>cTransId:"+cTransId+"numberType:"+numberType);
		}

		log.debug("return:dataType："+dataType+">>cTransId:"+cTransId+"numberType:"+numberType);

		return dataType;
	}


	/**
	 * 根据正则表达式获取文本中的变量名列表
	 * @param pattern
	 * @param content
	 * @return
	 */
	public static List<String> getParams(String pattern, String content) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(content);

		List<String> result = new ArrayList<String>();
		while (m.find()) {
			result.add(m.group(1));
		}
		return result;
	}

	/**
	 * 根据正则表达式将文本中的变量使用实际的数据替换成无变量的文本
	 * @param pattern
	 * @param content
	 * @param data
	 * @return
	 */
	public static String parse(String pattern, String content, Map<String, String> data) {
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(content);

		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			String key = m.group(1);
			String value = data.get(key);
			m.appendReplacement(sb, value == null ? "" : value);
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 获取区域树形结构数据列表
	 * @param allAreaList
	 * @param selectAreaList
	 * @param resultAreaList
	 * @return
	 */
	public static List<AreaTreeBean> getAreaTree(List<AreaTreeBean> allAreaList, List<AreaTreeBean> selectAreaList, List<AreaTreeBean> resultAreaList){
		List<String> parentIdList = new ArrayList<>();

		for (AreaTreeBean areaBean: selectAreaList) {
			if(!parentIdList.contains(areaBean.getCParentId())){
				parentIdList.add(areaBean.getCParentId());
			}
		}
		selectAreaList.clear();
		for (AreaTreeBean areaBean: allAreaList) {
			if (parentIdList.contains(areaBean.getCTamId())){
				if (!resultAreaList.contains(areaBean)){
					resultAreaList.add(areaBean);
				}
				selectAreaList.add(areaBean);
			}
		}

		if(parentIdList.size() > 1){
			getAreaTree(allAreaList,selectAreaList,resultAreaList);
		}
		return resultAreaList;
	}

	/**
	 * 四位聚合 函数
	 * @param data1
	 * @param data2
	 * @param data3
	 * @param data4
	 * @return
	 */
	public static String polymerize(String data1,String data2,String data3,String data4){
		StringBuffer buf=new StringBuffer();
		try {
			buf.append(binaryToDecimal(data1)).append(binaryToDecimal(data2)).append(binaryToDecimal(data3)).append(binaryToDecimal(data4));

			return String.valueOf(Long.parseLong(buf.toString(), 2));
		}catch (Exception e ){
			log.error("data1:"+ data1+",data2:"+ data2+",data3:"+ data3+",data4:"+ data4+",传入参数有误：",e);
			return null;
		}
	}

	/**
	 *  四位聚合 函数
	 * @param key1
	 * @param key2
	 * @param key3
	 * @param key4
	 * @param dataMap
	 * @return
	 */
	public static String polymerize(String key1,String key2,String key3,String key4,Map<String, Object> dataMap){
		StringBuffer buf=new StringBuffer();
		try {
			buf.append(binaryToDecimal(StringUtils.getString(dataMap.get(key1)))).append(binaryToDecimal(StringUtils.getString(dataMap.get(key2)))).append(binaryToDecimal(StringUtils.getString(dataMap.get(key3)))).append(binaryToDecimal(StringUtils.getString(dataMap.get(key4))));

			return String.valueOf(Long.parseLong(buf.toString(), 2));
		}catch (Exception e ){
			log.error("data1:"+ StringUtils.getString(dataMap.get(key1))+",data2:"+ StringUtils.getString(dataMap.get(key2))+",data3:"+ StringUtils.getString(dataMap.get(key3))+",data4:"+ StringUtils.getString(dataMap.get(key4))+",传入参数有误：",e);
			return null;
		}
	}

	public static String binaryToDecimal(String n) {
		long data = 0;
		try {
			if(StringUtils.isNotEmpty(n)){
				data = Long.parseLong(n);
			}
		}catch (Exception e){
			log.error("转换类型异常：",e);
			throw new RuntimeException(e);
		}
		return String.format("%016d",Long.valueOf(Long.toBinaryString(data)));
	}

	public static String getSubResult(String data,int subStart,int subEnd){
		log.info("截取字符方法开始 ，传入数字：" + data);
		String s = IntgobinaryUtil.binaryTo16Decimal(Long.parseLong(data));
		data = s.substring(s.length()-subEnd-1,s.length()-subStart);
		log.info("转成二进制：" + s+"截取区间：[" +subStart+","+subEnd+"]" +",截取结果："+data);
		data = StringUtils.getString(Integer.parseInt(data, 2));
		log.info("转换十进制结果："+data);
		return data;
	}

	public static String getBitValue(String value,int bit){
		String  data = StringUtils.getString(Double.valueOf(value).intValue());
		log.info("字符转换二进制方法开始 ，传入数字：" + data);
		String s = IntgobinaryUtil.binaryTo16Decimal( Long.parseLong(data));
		String result = s.substring(s.length()-bit-1,s.length()-bit);
		log.info("转成二进制：" + s+"取位数：[" +bit+"]" +",截取结果："+result);
		return result;
	}


	/**
	 * 把JavaBean转化为map
	 * @param bean
	 * @return
	 */
	public static Map<String,Object> bean2map(Object bean){
		Map<String,Object> map = new HashMap<>();
		try {

			//获取JavaBean的描述器
			BeanInfo b = Introspector.getBeanInfo(bean.getClass(),Object.class);
			//获取属性描述器
			PropertyDescriptor[] pds = b.getPropertyDescriptors();
			//对属性迭代
			for (PropertyDescriptor pd : pds) {
				//属性名称
				String propertyName = pd.getName();
				//属性值,用getter方法获取
				Method m = pd.getReadMethod();
				Object properValue = m.invoke(bean);//用对象执行getter方法获得属性值

				//把属性名-属性值 存到Map中
				map.put(propertyName, properValue);
			}
		}catch (Exception e){
			log.error("bean -> map 异常",e);
		}
		return map;
	}

	/**
	 * 把JavaBean转化为map 属性大写
	 * @param bean
	 * @return
	 */
	public static Map<String,Object> bean2mapUpperCase(Object bean){
		Map<String,Object> map = new HashMap<>();
		try {

			//获取JavaBean的描述器
			BeanInfo b = Introspector.getBeanInfo(bean.getClass(),Object.class);
			//获取属性描述器
			PropertyDescriptor[] pds = b.getPropertyDescriptors();
			//对属性迭代
			for (PropertyDescriptor pd : pds) {
				//属性名称
				String propertyName = pd.getName();
				//属性值,用getter方法获取
				Method m = pd.getReadMethod();
				Object properValue = m.invoke(bean);//用对象执行getter方法获得属性值

				//把属性名-属性值 存到Map中
				map.put(propertyName.toUpperCase(), properValue);
			}
		}catch (Exception e){
			log.error("bean -> map 异常",e);
		}
		return map;
	}

	public static void main (String[] argus){

//		String a = "(({4abee93d86fa11eb854470b5e83c0dba.00f37d3986d411eb80c970b5e83c0dba}+{5d4a8cfe86fa11eb854470b5e83c0dba.00f37d3986d411eb80c970b5e83c0dba})*4.18*({3bb2148d54a411eb9d0f00163e04e0df.a850df4c593d11eb9d0f00163e04e0df}-{28b4b00c54a411eb9d0f00163e04e0df.a850df4c593d11eb9d0f00163e04e0df})/3.6)/({46a34c60c9d311eb9c65b8cb29afc88a.c429339ec8fe11eb9c65b8cb29afc88a}+{9d29f29dc9d311eb9c65b8cb29afc88a.c429339ec8fe11eb9c65b8cb29afc88a}+{82f5da23c9d311eb9c65b8cb29afc88a.c429339ec8fe11eb9c65b8cb29afc88a}+{e2bc0bfdc9d311eb9c65b8cb29afc88a.c429339ec8fe11eb9c65b8cb29afc88a}+{2ea740c1c9c611eb9c65b8cb29afc88a.c429339ec8fe11eb9c65b8cb29afc88a}+{d2fe66c1c9c611eb9c65b8cb29afc88a.c429339ec8fe11eb9c65b8cb29afc88a}+{771262bdc9d311eb9c65b8cb29afc88a.c429339ec8fe11eb9c65b8cb29afc88a}+{70c38255c9d311eb9c65b8cb29afc88a.c429339ec8fe11eb9c65b8cb29afc88a}+{c26d198dc9c511eb9c65b8cb29afc88a.c429339ec8fe11eb9c65b8cb29afc88a}+{1a4f6bc0c8ff11eb9c65b8cb29afc88a.c429339ec8fe11eb9c65b8cb29afc88a}+{5dfdb9bac9d311eb9c65b8cb29afc88a.c429339ec8fe11eb9c65b8cb29afc88a}+{cccf26ddc9d311eb9c65b8cb29afc88a.c429339ec8fe11eb9c65b8cb29afc88a}+{7fa92f31c9c611eb9c65b8cb29afc88a.c429339ec8fe11eb9c65b8cb29afc88a}+{d77e3a19c9c411eb9c65b8cb29afc88a.c429339ec8fe11eb9c65b8cb29afc88a}+{5deab043c8ff11eb9c65b8cb29afc88a.c429339ec8fe11eb9c65b8cb29afc88a}+{081d9c8ac9c611eb9c65b8cb29afc88a.c429339ec8fe11eb9c65b8cb29afc88a}+{c93ab00fc9c811eb9c65b8cb29afc88a.c429339ec8fe11eb9c65b8cb29afc88a}+{263079bfc9d311eb9c65b8cb29afc88a.c429339ec8fe11eb9c65b8cb29afc88a}+{b21d45acc9d211eb9c65b8cb29afc88a.c429339ec8fe11eb9c65b8cb29afc88a}+{6e924770c9c611eb9c65b8cb29afc88a.c429339ec8fe11eb9c65b8cb29afc88a}+{3dca5f62c9c611eb9c65b8cb29afc88a.c429339ec8fe11eb9c65b8cb29afc88a}+{f2700111c9c811eb9c65b8cb29afc88a.c429339ec8fe11eb9c65b8cb29afc88a}+{1cafc8cdc9c611eb9c65b8cb29afc88a.c429339ec8fe11eb9c65b8cb29afc88a})";
//log.info(getParams("\\{(.+?)\\}",a).toString());
		//log.info(convertToCode("(0>=1?1: 0)+1").toString());
		//log.info(convertToCodeForBoolean("0<=1&&1<=2").toString());
//1号冷冻泵.频率>=35？30：0+3号冷冻泵.频率>=35？30：0
		log.info("结果："+convertToCode("(0>=1?1: 0)+(0<=1?1: 0)").toString());

	}
}
