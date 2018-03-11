package com.dawn.zhao.utils.apivalidate;

import com.dawn.zhao.utils.ComUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Service
public class ParameterValidate {
	
	private static Logger logger = LoggerFactory.getLogger(ParameterValidate.class);
	
//	@Autowired
//	private ChannelService channelService;
	
	@SuppressWarnings("unchecked")
	public boolean validate(List<KeyValueBean> parameters,String enc,String md5Key){
		//按参数排序操作  
        Collections.sort(parameters, new ParameterOrder());
		
        StringBuilder paramValues=new StringBuilder("");
        
        for(KeyValueBean kv:parameters){
        	paramValues.append(kv.getValue());
        }
        
        paramValues.append(md5Key);
        
        logger.debug("md5Key="+md5Key);
        logger.debug("paramValues="+paramValues.toString());
        
        String valiMd5 = MD5.getMD5(paramValues.toString()).toLowerCase();
        logger.debug("valiMd5="+valiMd5);
    	if(valiMd5.equals(enc)){
    		return true;
    	}
        
        String finalToMD5 = urlEncode(paramValues.toString(),"utf-8");
        logger.debug("finalToMD5="+finalToMD5);
        String paramValuesMD5=MD5.getMD5(finalToMD5).toLowerCase();
        
        logger.debug("paramValuesMD5="+ paramValuesMD5);
        if(paramValuesMD5.equals(enc)){
        	return true;
        }else{
        	return false;
        }
	}
	
	@SuppressWarnings("unchecked")
	public String signature(List<KeyValueBean> parameters,String signatureType,String md5Key){
		//按参数排序操作  
        Collections.sort(parameters, new ParameterOrder());
		
        StringBuilder paramValues=new StringBuilder("");
        
        for(KeyValueBean kv:parameters){
        	paramValues.append(kv.getValue());
        }
        
        paramValues.append(md5Key);
        
        logger.debug("md5Key="+md5Key);
        logger.debug("paramValues="+paramValues.toString());
        
        String enc = "";
        if(signatureType.equals(ComUtil.SIGNATURE_TYPE.MOVIE_JIAHE_OFFICIAL.value())){
	        String valiMd5 = MD5.getMD5(paramValues.toString()).toLowerCase();
	        logger.debug("valiMd5="+valiMd5);
	        enc = valiMd5;
        }
      
        if(signatureType.equals(ComUtil.SIGNATURE_TYPE.MOVIE_OFFICIAL.value())){
	        String finalToMD5 = urlEncode(paramValues.toString(),"utf-8");
	        logger.debug("finalToMD5="+finalToMD5);
	        String paramValuesMD5=MD5.getMD5(finalToMD5).toLowerCase();
	        logger.debug("paramValuesMD5="+ paramValuesMD5);
	        enc = paramValuesMD5;
        }
        return enc;
	}
	
	@SuppressWarnings("unchecked")
	public boolean validate(HttpServletRequest request,String enc){
		
		List<KeyValueBean> parameters=new ArrayList<KeyValueBean>();
		
		Enumeration<String> pns=request.getParameterNames();
		while(pns.hasMoreElements())
		{
			String parameterKey = (String)pns.nextElement();
			
			if(parameterKey!=null && "enc".equalsIgnoreCase(parameterKey)){
				continue;
			}

			String parameterValue = request.getParameter(parameterKey);
			logger.debug("parameterKey="+parameterKey+" parameterValue="+parameterValue);
			
			parameters.add(new KeyValueBean(parameterKey, parameterValue));
		}
		
		String channelId = request.getHeader("channel_id");
		if(channelId == null || "".equals(channelId)){
			channelId = request.getParameter("channel_id");
		}
		String md5Key =  "";
		/**
		 * cias-core
		 
		if(channelId != null && !"".equals(channelId)){
			if(ChannelKey.CIAS_UNIFIEDPLATFORM_COUPON_BACKFUNCTION.equals(channelId)){
				md5Key = ChannelKey.getChannelMd5Key(channelId);
			}else{
				WebApplicationContext wac =WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
				ChannelServiceInterface channelServiceInterface = (ChannelServiceInterface)wac.getBean("cinemaChannelService");
				md5Key =  channelServiceInterface.getChannelMd5Key(channelId);
			}
		}
		*/
		/**
		 * movie-core
		 */
		md5Key = ChannelKey.getChannelMd5Key(channelId);
//		String md5Key = JiaheChannelKey.getChannelMd5Key(channelId);
		
		if(md5Key == null || "".equals(md5Key)){
			return false;
		}
		
		return validate(parameters,enc,md5Key);
		
		
	}
	
	class ParameterOrder implements Comparator<Object> {  
	    @Override  
	    public int compare(Object arg0, Object arg1) {  
	    	KeyValueBean a = (KeyValueBean) arg0;  
	    	KeyValueBean b = (KeyValueBean) arg1; 
	    	return a.getKey().compareToIgnoreCase(b.getKey());
	    }  
	}
	
	private  String urlEncode (String source, String enc) {
		if (source == null || enc == null) {
			return source;
		}
		String encode = null;
		try {
			encode = URLEncoder.encode(source, enc);
			encode = replace(encode, "+", "%20");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			
		}
		return encode;
	}
	
	private String replace(String strSource, String strFrom, String strTo) {
		if (strSource == null) { 
			return null;
		}
		
		int i = 0;
		if ((i = strSource.indexOf(strFrom, i)) >= 0) {
			char[] cSrc = strSource.toCharArray();
			char[] cTo = strTo.toCharArray();
			int len = strFrom.length();
			StringBuffer buf = new StringBuffer(cSrc.length);
			buf.append(cSrc, 0, i).append(cTo);
			i += len;
			int j = i;
			while ((i = strSource.indexOf(strFrom, i)) > 0) {
				buf.append(cSrc, j, i - j).append(cTo);
				i += len;
				j = i;
			}
			buf.append(cSrc, j, cSrc.length - j);
			return buf.toString(); 
	    } 
	    return strSource;
	}
	/*
	public String paramToEnc(){
		ParameterValidate pv=new ParameterValidate();
	}
	*/

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<KeyValueBean> parameters=new ArrayList<KeyValueBean>();
		
//		parameters.add(new KeyValueBean("userId", "187108"));
//		parameters.add(new KeyValueBean("param_json", monitoringReportParamJsonStrs()));
//		parameters.add(new KeyValueBean("time_stamp", "1418156842823.45"));
		//019804079248584,4013523981128067
		
		parameters.add(new KeyValueBean("plan_id", "1418312400000010302"));
		parameters.add(new KeyValueBean("coupon_code", "4019804079248584,4013523981128067"));
		parameters.add(new KeyValueBean("time_stamp", "1418264476039"));
		
		//http://115.29.165.175:8089/moviecouponcard/cardverify/rule?
//		coupon_code=4019804079248584%2C4013523981128067&plan_id=1418312400000010302&
//				time_stamp=1418264476039&enc=9817a9289e18b5697201381d60e4b86c

		ParameterValidate pv=new ParameterValidate();
		System.out.println(pv.validate(parameters, "2afde99aaed2a266005b1b985b79975a","123456"));
	}

	public static String monitoringReportParamJsonStrs() {
		String jsons="{\"cinemaLineId\":\"\",\"cinemaLineName\":\"\",\"cinemaId\":\"100\",\"cinemaName\":\"测试影院\",\"deviceId\":\"\",\"mac\":\"74:D4:35:51:53:42\"}";
		return jsons;
	}
}
