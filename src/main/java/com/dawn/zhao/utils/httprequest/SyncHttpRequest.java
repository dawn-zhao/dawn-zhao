package com.dawn.zhao.utils.httprequest;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncHttpRequest {
	private static Logger logger = LoggerFactory.getLogger(SyncHttpRequest.class);
	
	public static String getStringFromURL (String url, int timeoutSecs, String respEnc) {
		HttpURLConnection conn = null;
		
		logger.debug(url);

		try {
//			Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress("115.29.165.175", 60001));			
//			conn = (HttpURLConnection) new URL(url).openConnection(proxy);
			
			URL Url = new URL(url);
			conn = (HttpURLConnection)Url.openConnection();
			conn.setConnectTimeout(20*1000);
			conn.setReadTimeout(timeoutSecs*1000);
			
			if (conn.getResponseCode() == 200) {
				InputStream input = conn.getInputStream();
				byte[] a = new byte[1024];
				int length = 0;
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				while((length = input.read(a,0,1024))>0) {
					out.write(a,0,length);
				}
				String s = new String(out.toByteArray(), respEnc);
				input.close();
				out.close();
				return s.trim();
			} else {
				logger.info(
						"SyncHttpRequest", 
						"connect to " + url + " failed: " + conn.getResponseCode() + " " + conn.getResponseMessage());
			}
		} catch (Exception e) {
			logger.error("SyncHttpRequest", e);
		} finally {
			if (conn != null) { conn.disconnect(); }
		}
		return "";
	}
	
	public static byte[] getByteFromURL (String url, int timeoutSecs) {
		HttpURLConnection conn = null;
		
		logger.debug(url);
		
		try {
			URL Url = new URL(url);
			conn = (HttpURLConnection)Url.openConnection();
			conn.setConnectTimeout(20*1000);
			conn.setReadTimeout(timeoutSecs*1000);
			
			if (conn.getResponseCode() == 200) {
				InputStream input = conn.getInputStream();
				int offset = 1024;
				byte[] resp = new byte[offset];
				int length = 0;
				ByteArrayOutputStream out=new ByteArrayOutputStream();
				while((length = input.read(resp, 0, offset))>0) {
					out.write(resp, 0, length);
				}
				input.close();
				out.close();
				return out.toByteArray();
			} else {
				logger.info(
						"SyncHttpRequest", 
						"connect to " + url + " failed: " + conn.getResponseCode() + " " + conn.getResponseMessage());
			}
		} catch (Exception e) {
			logger.error("SyncHttpRequest", e);
		} finally {
			if (conn != null) { conn.disconnect(); }
		}
		return null;
	}
	
	/**
	 * 默认回复编码为utf-8
	 * */
	public static String getStringFromURL (String url, int timeoutSecs) {
		return getStringFromURL(url, timeoutSecs, "utf-8");
	}
	
	public static String postStringToURL (
			String url, String content, int timeoutSecs, String reqEnc, String respEnc) {
		HttpURLConnection conn = null;
		InputStream is = null;
		String str = null;
		if (reqEnc == null) reqEnc = "utf-8";
		if (respEnc == null) respEnc = "utf-8";
		
		logger.debug(url+"?"+content);
		try{
			URL Url = new URL(url);
			conn = (HttpURLConnection) Url.openConnection();
			conn.setConnectTimeout(20*1000);
			conn.setReadTimeout(timeoutSecs*1000);
			
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
//			conn.setRequestProperty("Proxy-Connection", "Keep-Alive");
//			conn.setUseCaches(false);
//			conn.setRequestProperty("Content-type","text/xml");
			conn.connect();
			
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			out.write(content.getBytes(reqEnc));
			out.flush();
			out.close();
			
			is = conn.getInputStream();	
			byte[] a = new byte[1024];
			int length = 0;
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			while ((length = is.read(a, 0, 1024)) > 0) {
				os.write(a, 0, length);
			}
			str = new String(os.toByteArray(), respEnc);
			is.close();
			os.close();
			
			return str.trim();
		} catch(Exception e) {
			logger.error("SyncHttpRequest", e);
		} finally {
			if (conn != null) { conn.disconnect(); }
		}
		return str;
	}
	
	/**
	 * 默认请求编码和回复编码都是utf-8
	 * */
	public static String postStringToURL (String url, String content, int timeoutSecs) {
		return postStringToURL(url, content, timeoutSecs, "utf-8", "utf-8");
	}
}
