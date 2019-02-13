package com.artfire.ninedraw.artfire_code.utils;


import com.google.gson.Gson;

import java.util.Map;

public class GsonUtils {

	/**
	 * 将JSON转为对象
	 * @param result
	 * @param clazz
	 * @param <T>
     * @return
     */
	public static  <T> T  json2bean(String result , Class<T> clazz){
		Gson gson = new Gson();

		T t = gson.fromJson(result, clazz);
		return t;
	}

	/**
	 * 将对象转为json字符串
	 * @return
     */
	public static String bean2Json(Object data) {
		Gson jso = new Gson();
		String result = jso.toJson(data);
		return result;
	}

	/**
	 * map转json
	 * @param jsonMap
	 * @return
     */
	public static String mapToJson(Map<String, String> jsonMap) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");

		if (jsonMap.size() > 0) {
			for (String key : jsonMap.keySet()) {
				if(jsonMap.get(key).startsWith("[")) {
					buffer.append("\""+key  + "\":" + jsonMap.get(key) + ",");
				}else {
					buffer.append("\""+key  + "\":\"" + jsonMap.get(key) + "\",");
				}

			}
			// 去掉最后一个','
			buffer.deleteCharAt(buffer.length() - 1);
		}
		buffer.append("}");
		return buffer.toString();
	}
}
