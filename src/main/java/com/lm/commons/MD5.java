package com.lm.commons;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密处理
 * @author liangming.deng
 * @date   2017年6月19日e 
 *
 */
public final class MD5 {
	private static final String BEFORE_SALT = "dlm";
	private static final String AFTER_SALT = "@dlm.com";
    /**
     * 把字符串进行MD5加密
     *
     * @param string 字符串
     * @return MD5加密后的字符串
     */
    public static String encode(String string) {
        String encode = defaultEncode(string);
        StringBuilder sb = new StringBuilder();
        sb.append(BEFORE_SALT);
        for (int i = 0, length = encode.length() / 2; i < length; i++) {
            sb.append(encode.charAt(i * 2 + 1));
            sb.append(encode.charAt(i * 2));
        }
        sb.append(AFTER_SALT);
        return defaultEncode(sb.toString());
    }

    /**
     * 把字符串进行MD5加密
     *
     * @param string 字符串
     * @return MD5加密后的字符串
     */
    public static String defaultEncode(String string) {
        StringBuilder sb = new StringBuilder(32);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashValue = md.digest(string.getBytes());
            for (int i = 0; i < hashValue.length; i++) {
                sb.append(Integer.toHexString((hashValue[i] & 0xf0) >> 4));
                sb.append(Integer.toHexString(hashValue[i] & 0x0f));
            }
        } catch (NoSuchAlgorithmException e) {
        }
        return sb.toString();
    }
    
    public static void main(String[] args){
    	String origal = "dlm333";
    	System.out.println(defaultEncode(origal));
    	System.out.println(encode(origal));
    }

}
