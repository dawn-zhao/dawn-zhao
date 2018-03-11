package com.dawn.zhao.utils.httprequest;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dawn.zhao.utils.apivalidate.KeyValueBean;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientRequest {
	
	private static Logger logger = LoggerFactory.getLogger(HttpClientRequest.class);
	
	public static void main(String[] args) throws Exception {
		
	}
	
	@Deprecated
	public static String executeRequest(String url, List<KeyValueBean> params, List<KeyValueBean> headParams){
		
		String result = "";
		try{
			
			//建立HttpPost对象
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost httpPost=new HttpPost(url);
			
			//传递参数
			List<BasicNameValuePair> urlparams=new ArrayList<BasicNameValuePair>();
			for(KeyValueBean kvb:params){
				urlparams.add(new BasicNameValuePair(kvb.getKey(), kvb.getValue()));
			}
			
			//设置参数
			httpPost.setEntity(new UrlEncodedFormEntity(urlparams));
//			httpPost.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
			
			//传递head参数
			for(KeyValueBean kvb:headParams){
				httpPost.setHeader(kvb.getKey(), kvb.getValue());
			}
			
			CloseableHttpResponse response = httpclient.execute(httpPost);
			
			try {
			    HttpEntity rentity = response.getEntity();
//			    StatusLine statusLine = response.getStatusLine();
//			    if (statusLine.getStatusCode() != 200) {
//		            throw new HttpResponseException(
//		                    statusLine.getStatusCode(),
//		                    statusLine.getReasonPhrase());
//		        }
		        if (rentity == null) {
		            throw new ClientProtocolException("Response contains no content");
		        }
		        result=EntityUtils.toString(response.getEntity());
				logger.info(result);
		        
			} finally {
			    response.close();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error(ex+"");
		}
		return result;
	}
	
	/**
	 * 
	 * @param url
	 * @return String
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String httpDelete(String url) throws ClientProtocolException, IOException{
		
		String responseString = "";
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpDelete httpdelete = new HttpDelete(url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(3000)
		        .setConnectTimeout(3000)
		        .build();
		httpdelete.setConfig(requestConfig);
		
		CloseableHttpResponse response = httpclient.execute(httpdelete);
		try {
		    HttpEntity entity = response.getEntity();
//		    StatusLine statusLine = response.getStatusLine();
//		    if (statusLine.getStatusCode() != 200) {
//	            throw new HttpResponseException(
//	                    statusLine.getStatusCode(),
//	                    statusLine.getReasonPhrase());
//	        }
	        if (entity == null) {
	            throw new ClientProtocolException("Response contains no content");
	        }
	        
	        responseString = EntityUtils.toString(entity);
	        
		} finally {
		    response.close();
		}
		
		return responseString;
		
	}

	/**
	 * 
	 * @param url
	 * @return String
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String httpGet(String url) throws ClientProtocolException, IOException{
		
		String responseString = "";
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(3000)
		        .setConnectTimeout(3000)
		        .build();
		httpget.setConfig(requestConfig);
		
		CloseableHttpResponse response = httpclient.execute(httpget);
		try {
		    HttpEntity entity = response.getEntity();
//		    StatusLine statusLine = response.getStatusLine();
//		    if (statusLine.getStatusCode() != 200) {
//	            throw new HttpResponseException(
//	                    statusLine.getStatusCode(),
//	                    statusLine.getReasonPhrase());
//	        }
	        if (entity == null) {
	            throw new ClientProtocolException("Response contains no content");
	        }
	        
	        responseString = EntityUtils.toString(entity);
	        
		} finally {
		    response.close();
		}
		
		return responseString;
		
	}
	
	/**
	 * 
	 * @param String url
	 * @param List<NameValuePair> formparams
	 * @return String
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String httpPost(String url,List<NameValuePair> formparams) throws ClientProtocolException, IOException{
		
		String responseString = "";
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
		
		HttpPost httppost = new HttpPost(url);
		httppost.setEntity(entity);
		
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(3000)
		        .setConnectTimeout(3000)
		        .build();
		httppost.setConfig(requestConfig);
		
		CloseableHttpResponse response = httpclient.execute(httppost);
		try {
		    HttpEntity rentity = response.getEntity();
//		    StatusLine statusLine = response.getStatusLine();
//		    if (statusLine.getStatusCode() != 200) {
//	            throw new HttpResponseException(
//	                    statusLine.getStatusCode(),
//	                    statusLine.getReasonPhrase());
//	        }
	        if (rentity == null) {
	            throw new ClientProtocolException("Response contains no content");
	        }
	        
	        responseString = EntityUtils.toString(rentity);
	        
		} finally {
		    response.close();
		}
		
		return responseString;
		
	}
	
	/**
	 * 
	 * @param url
	 * @param jsonStr
	 * @return String
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String httpPostJson(String url,String jsonStr) throws ClientProtocolException, IOException{
		
		String responseString = "";
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		StringEntity stringEntity = new StringEntity(jsonStr,Consts.UTF_8);
		stringEntity.setContentType("application/json");
		
		HttpPost httppost = new HttpPost(url);
		httppost.setEntity(stringEntity);
		
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(5000)
		        .setConnectTimeout(5000)
		        .build();
		httppost.setConfig(requestConfig);
		
		CloseableHttpResponse response = httpclient.execute(httppost);
		try {
		    HttpEntity rentity = response.getEntity();
		    StatusLine statusLine = response.getStatusLine();
//		    logger.info(statusLine.getStatusCode()+""); 
//		    if (statusLine.getStatusCode() != 200) {
//	            throw new HttpResponseException(
//	                    statusLine.getStatusCode(),
//	                    statusLine.getReasonPhrase());
//	        }
	        if (rentity == null) {
	            throw new ClientProtocolException("Response contains no content");
	        }
	        
	        responseString = EntityUtils.toString(rentity);
	        
		} finally {
		    response.close();
		}
		
		return responseString;
		
	}
	
	/**
	 * 
	 * @param url
	 * @param jsonStr
	 * @return String
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String httpPutJson(String url,String jsonStr) throws ClientProtocolException, IOException{
		
		String responseString = "";
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		StringEntity stringEntity = new StringEntity(jsonStr,Consts.UTF_8);
		stringEntity.setContentType("application/json");
		
		HttpPut httpput = new HttpPut(url);
		httpput.setEntity(stringEntity);
		
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(3000)
		        .setConnectTimeout(3000)
		        .build();
		httpput.setConfig(requestConfig);
		
		CloseableHttpResponse response = httpclient.execute(httpput);
		try {
		    HttpEntity rentity = response.getEntity();
//		    StatusLine statusLine = response.getStatusLine();
//		    if (statusLine.getStatusCode() != 200) {
//	            throw new HttpResponseException(
//	                    statusLine.getStatusCode(),
//	                    statusLine.getReasonPhrase());
//	        }
	        if (rentity == null) {
	            throw new ClientProtocolException("Response contains no content");
	        }
	        
	        responseString = EntityUtils.toString(rentity);
	        
		} finally {
		    response.close();
		}
		
		return responseString;
		
	}
	
}
