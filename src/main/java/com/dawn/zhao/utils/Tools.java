package com.dawn.zhao.utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tools {
	
	private static Logger logger = LoggerFactory.getLogger(Tools.class);
	
	public static boolean varifyTimeStamp(String timeStamp){
		try{
			if (timeStamp != null && !"".equals(timeStamp)) {
				long interval = System.currentTimeMillis() - Long.valueOf(timeStamp);
				//允许10分钟的误差
				if (interval > 10*60*1000) {
					return false;
				} else if (interval < -10*60*1000) {
					return false;
				}
			}else{
				return false;
			}
			return true;
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
	}
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//	
	
	public static Map<String,String> requestParams(HttpServletRequest request){
		
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
//			 try {
//	                byte[] bb = valueStr.getBytes("ISO-8859-1");
//	                String test = new String(bb, "UTF-8");
//	                logger.info(test+"测试编码utf-8-----------------------------------------");
//	            } catch (UnsupportedEncodingException e) {
//	                e.printStackTrace();
//	            }
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			try {
				logger.info("valueStr="+valueStr);
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				logger.info("valueStr="+valueStr+" ,测试编码gbk++++++++++++++++++++++++++++++++++++++++");
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
				logger.info("valueStr="+valueStr+" ,测试编码gbk++++++++++++++++++++++++++++++++++++++++");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("name="+name);
			logger.info("valueStr="+valueStr);
			params.put(name, valueStr);
		}
		
		
		return params;
	}
	
	public static String mapKeyValStr(Map<String,String> params){
		
		String urlParams="";
		for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String  values = (String) params.get(name);
			urlParams = urlParams+"&"+name+"="+values;
		}
		
		
		return urlParams;
	}
}
