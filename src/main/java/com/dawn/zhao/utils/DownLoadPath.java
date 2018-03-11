package com.dawn.zhao.utils;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class DownLoadPath {
	
	private static String contextPath = null;
	private static String serverURL = null;
	
	/**
	 * 获取工程路径
	 * 
	 * @return
	 */
	public static String getContextPath() {
		if (contextPath == null) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
			contextPath = request.getSession().getServletContext()
					.getRealPath("");
		}
		return contextPath;
	}
	
	/**
	 * 获取下载文件URL路径
	 * 
	 * @return
	 */
	public static String getFileDownURL() {
		if (serverURL == null) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
			serverURL =request.getScheme()+"://"+request.getServerName()+":"+
					request.getServerPort()+request.getServletContext().getContextPath()+"/fileResource/fileDown/";
		}
		return serverURL;
	}
	
	/**
	 * 获取下载文件路径
	 * 
	 * @return
	 */
	public static String getFileDownPath() {
		return getContextPath() + File.separator + "fileResource"
				+ File.separator + "fileDown" + File.separator;
	}
}
