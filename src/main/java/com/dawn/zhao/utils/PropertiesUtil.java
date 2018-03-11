package com.dawn.zhao.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PropertiesUtil {
	private static ResourceBundle resource = ResourceBundle.getBundle("application");
	
	private static Map<String,String> mapProp=new HashMap<String,String>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	/**
	 * 获取application.properties文件属性值
	 * 
	 * @param key
	 * @return
	 */
	public static String getPropByKey(String key) {
		if (key != null && !"".equals(key)) {
			if(!mapProp.containsKey(key)){
				mapProp.put(key, resource.getString(key));
			}
			return mapProp.get(key);
		} else {
			return "";
		}
	}
	
}
