package com.dawn.zhao.utils.apivalidate;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * MD5的算法在RFC1321 中定义
 * 在RFC 1321中，给出了Test suite用来检验你的实现是否正确： 
 * MD5 ("") = d41d8cd98f00b204e9800998ecf8427e 
 * MD5 ("a") = 0cc175b9c0f1b6a831c399e269772661 
 * MD5 ("abc") = 900150983cd24fb0d6963f7d28e17f72 
 * MD5 ("message digest") = f96b697d7cb7938d525a2f31aaf161d0 
 * MD5 ("abcdefghijklmnopqrstuvwxyz") = c3fcd3d76192e4007dfb496cca67e13b 
 * 
 * @author haogj
 *
 * 传入参数：一个字节数组
 * 传出参数：字节数组的 MD5 结果字符串
 */
public class MD5 {

	public static void main(String[] args) {
//		System.out.println(getMD5("1570613580899"));
		System.out.println(validateSign());
	}

	private static String validateSign() {

//		Map<String, String> params = new HashMap<>();
//		params.put("address", "四川省巴中市通江县铁佛中学");
//		params.put("deviceToken", "5803722cd781aa5a5f9c401b7f91938f");
//		params.put("clockStatus", "1");
//		params.put("access_token", "198e292a38f90ffdde80ec50180cb8e2");
//		params.put("isAmap", "1");
//		params.put("deviceName", "MuMu");
//		params.put("punchInStatus", "0");
//		params.put("uid", "6a49d393fbf13c9fbdbf5f86aa173db1");
//		params.put("lat", "31.761801595052083");
//		params.put("lng", "107.3205908203125");
//		params.put("clockDate", "2019.10.09");
//		params.put("traineeId", "2310964");
//		List<String> keys = new ArrayList<>(params.keySet());
//		Collections.sort(keys);
//
//		String linkString = "";
//		String timeKey = "timestamp";
//		for (String key : keys) {
//			if (!timeKey.equals(key)) {
//				// linkString += key + "=" + request.getParameter(key) + "&";
//			}
//			linkString += params.get(key);
//		}
//
//		String time = params.get(timeKey);
//		String sign = DigestUtils.md5Hex(linkString + time);
//
//		return sign;
		return null;
	}

	public static String getMD5(byte[] source) {
		String s = null;
		// 用来将字节转换成 16 进制表示的字符
		char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',  'e', 'f'};
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance( "MD5" );
			md.update( source );
			
			// MD5 的计算结果是一个 128 位的长整数
			byte tmp[] = md.digest();
            
			// 用字节表示就是 16 个字节
			// 每个字节用 16 进制表示的话，使用两个字符
			// 所以表示成 16 进制需要 32 个字符
			char str[] = new char[16 * 2];
                      
			// 表示转换结果中对应的字符位置
			int k = 0;
			
			// 从第一个字节开始，对 MD5 的每一个字节  转换成 16 进制字符的转换
			for (int i = 0; i < 16; i++) {
				// 取第 i 个字节
				byte byte0 = tmp[i];
				// 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				// 取字节中低 4 位的数字转换
				str[k++] = hexDigits[byte0 & 0xf];
			}
			// 换后的结果转换为字符串
			s = new String(str);			
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
		return s;	
	}
	
	public static String getMD5 (String raw) {
		if (raw == null || raw.length() == 0) {
			return "";
		}
		try {
			//return getMD5(raw.getBytes("utf-8"));
			return getMD5(raw.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	} 
	
}
