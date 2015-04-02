package com.xinxi.util;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * Title: Ext JS 辅助类 Description: 该类用于转换java对象为XML文件格式或JSON文件格式,(JDK13H)
 * 
 * @author weijun,sigesoftware
 * @time: 2010.05.06
 * @version: 0.0.1.1
 */
public class ExtHelper {

	/**
	 * 通过List生成JSON对象
	 * 
	 * @param list
	 *            包含bean对象的list
	 * @return JSON对象
	 */
	public static JSONObject getJSONObject(List list) {
		return getJSONObjectByPage(list.size(), list);
	}

	/**
	 * 通过分页把List生成JSON对象
	 * 
	 * @param total
	 *            记录总数
	 * @param list
	 *            包含bean对象的list
	 * @return JSON对象
	 */
	public static JSONObject getJSONObjectByPage(int total, List list) {
		try {
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("total", total);
			jsonMap.put("rows", list);
			return notNullJSONObject(jsonMap);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 通过bean生成JSON数据
	 * 
	 * @param bean
	 *            bean对象
	 * @return 生成的JSON数据
	 */
	public static String getJSonFormBean(Object bean) {
		String jsonStr = "";
		try {
			if (bean != null) {
				JSONObject jsonArray = notNullJSONObject(bean);
				jsonStr = jsonArray.toString();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return jsonStr;
	}

	public static JSONObject getJSONObject(Object bean) {
		return JSONObject.fromObject(bean);
	}

	private static JSONObject notNullJSONObject(Object obj) {
		String result = JSONObject.fromObject(obj).toString();
		result = result.replaceAll("null", "''");
		return JSONObject.fromObject(result);
	}

	public static JSONObject getJSONObject(String jsonStr) {
		return JSONObject.fromObject(jsonStr);
	}
}
