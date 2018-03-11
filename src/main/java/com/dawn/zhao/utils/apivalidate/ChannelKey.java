package com.dawn.zhao.utils.apivalidate;

public class ChannelKey {
	
	/**
	 * 统一平台卡码后台签名渠道ID
	 */
	public static final String CIAS_UNIFIEDPLATFORM_COUPON_BACKFUNCTION = "0";
	
	/**
	 * 抠电影官网渠道MD5KEY
	 */
	public enum CHANNEL_MD5_KEY{
		DEFAULT("9","mq3CwYZL"),
		KOMOVIE_IOS("1","abcdYzx"),
		KOMOVIE_ANDROID("2","abcdYzx"),
		KOMOVIE_HTML5("3","abcdYzx"),
		KOMOVIE_WEB("4","abcdYzx"),
		KOMOVIE_REDENVELOP("139","abcdYzx"),
		CIAS_UNIFIEDPLATFORM_COUPON_BACKFUNCTION("0","ciasplatform123"),
		
		;
		
		private String value;
		private String desc;
		CHANNEL_MD5_KEY(String value,String desc){
			this.value=value;
			this.desc=desc;
		}
		public String value(){
			return this.value;
		}
		public String desc(){
			return this.desc;
		}
	}
	
	/**
	 * 根据渠道ID取md5key
	 * @param channelId
	 * @return
	 */
	public static String getChannelMd5Key(String channelId){
		if(channelId == null || "".equals(channelId)){
			return CHANNEL_MD5_KEY.DEFAULT.desc();
		}
		
		if(CHANNEL_MD5_KEY.KOMOVIE_IOS.value().equals(channelId)){
			return CHANNEL_MD5_KEY.KOMOVIE_IOS.desc();
		}
		if(CHANNEL_MD5_KEY.KOMOVIE_ANDROID.value().equals(channelId)){
			return CHANNEL_MD5_KEY.KOMOVIE_ANDROID.desc();
		}
		if(CHANNEL_MD5_KEY.KOMOVIE_HTML5.value().equals(channelId)){
			return CHANNEL_MD5_KEY.KOMOVIE_HTML5.desc();
		}
		if(CHANNEL_MD5_KEY.KOMOVIE_WEB.value().equals(channelId)){
			return CHANNEL_MD5_KEY.KOMOVIE_WEB.desc();
		}
		if(CHANNEL_MD5_KEY.KOMOVIE_REDENVELOP.value().equals(channelId)){
			return CHANNEL_MD5_KEY.KOMOVIE_REDENVELOP.desc();
		}
		if(CHANNEL_MD5_KEY.CIAS_UNIFIEDPLATFORM_COUPON_BACKFUNCTION.value().equals(channelId)){
			return CHANNEL_MD5_KEY.CIAS_UNIFIEDPLATFORM_COUPON_BACKFUNCTION.desc();
		}
		return CHANNEL_MD5_KEY.DEFAULT.desc();
	}
}
