package com.dawn.zhao.utils.apivalidate;

import com.dawn.zhao.utils.PropertiesUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* 
*	@owner: zhangkaixiang@kokozu.net, @create: 2015-01-13 
*	@desc: 过滤不需签名的访问请求(按层次到方法或类)
*/ 

public class FilterUtils {
	
	/**
	 * 校验当前请求是否需要签名
	 * @param requestURI
	 * @return boolean
	 */
	public static boolean isSignNotVerifyMethod(String requestURI) {
		
		String signNotVerifyMethod = PropertiesUtil.getPropByKey("sign_not_verify_method");
//		String signNotVerifyMethod = "downCoupon,downAddCoupon,couponcard_jiahe";
		if(signNotVerifyMethod == null || "".equals(signNotVerifyMethod)){
			return false;
		}
		
		String requestURIMethodClassStr = requestURI;
		StringBuilder urlFilter = new StringBuilder("");
		String method = requestURIMethodClassStr.substring(requestURIMethodClassStr.lastIndexOf("/")+1, requestURIMethodClassStr.length());
		urlFilter.append(method);
		requestURIMethodClassStr = requestURIMethodClassStr.substring(0, requestURIMethodClassStr.lastIndexOf("/"));
		String urlClass = requestURIMethodClassStr.substring(requestURIMethodClassStr.lastIndexOf("/")+1, requestURIMethodClassStr.length());
		urlFilter.append(","+urlClass);
		
		Set<String> notVerifySet = new HashSet<String>(Arrays.asList(signNotVerifyMethod.split(",")));
		Set<String> urlSet = new HashSet<String>(Arrays.asList(urlFilter.toString().split(",")));
		urlSet.retainAll(notVerifySet);
		
		if(urlSet != null && urlSet.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		String requestURI = "http://localhost:8080/moviecouponcard/couponcard_jiahe/downCoupon";
		System.out.println(isSignNotVerifyMethod(requestURI));
	}

}
