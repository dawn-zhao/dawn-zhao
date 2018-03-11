package com.dawn.zhao.utils.apivalidate;

public class JiaheChannelKey {

	/**
	 * 嘉禾cias渠道MD5KEY
	 */
	public enum CHANNEL_MD5_KEY{
		REMOTE("2","123456");
		
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
			return CHANNEL_MD5_KEY.REMOTE.desc();
		}
		
		return CHANNEL_MD5_KEY.REMOTE.desc();
	}
}
