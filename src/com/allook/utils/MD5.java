package com.allook.utils;

import java.security.MessageDigest;

/**
 * 
 * <p>文件名称: MD5.java</p>
 * <p>文件描述: 提供md5加密的算�?/p>
 * <p>版权�?��: 版权�?��(C)2011-2016</p>
 * <p>�?  �? 山东广电新媒体中心技术部</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期�?016-1-12</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  zhengxi
 */
public class MD5 {
	/**
	 * 将字符串以md5方式加密的方�?
	 * 
	 * @param s
	 *            �?��加密的字符串
	 * @return 以md5加密后的�?
	 */
	public static String getMD5String(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void main(String[] args){
	    System.out.println(MD5.getMD5String("111111"));
	}
}
