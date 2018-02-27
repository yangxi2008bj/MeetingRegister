package com.lachesis.windranger.common.util;

import java.io.IOException;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.lachesis.windranger.common.constants.CommonConstant;

/**
 * 
 * @ClassName: AESUtil 
 * @Description: AES对称加密，并使用Base64编码 
 * @author Xi xi.yang@lachesis-mh.com
 * @date 2017年4月6日 下午2:42:44 
 *
 */
public class AESUtil {

	public static final String DEFAULT_CODING = "utf-8";

	public static String decrypt(String encrypted, String seed) throws Exception {
		byte[] keyb = seed.getBytes(DEFAULT_CODING);
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] thedigest = md.digest(keyb);
		SecretKeySpec skey = new SecretKeySpec(thedigest, "AES");
		Cipher dcipher = Cipher.getInstance("AES");
		dcipher.init(Cipher.DECRYPT_MODE, skey);

		byte[] clearbyte = dcipher.doFinal(decode(encrypted));
		return new String(clearbyte);
	}

	public static String encrypt(String content, String key) throws Exception {
		byte[] input = content.getBytes(DEFAULT_CODING);

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] thedigest = md.digest(key.getBytes(DEFAULT_CODING));
		SecretKeySpec skc = new SecretKeySpec(thedigest, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, skc);

		byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
		int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
		ctLength += cipher.doFinal(cipherText, ctLength);

		return encode(cipherText);
	}

//	/**
//	 * 字符串转字节数组
//	 * 
//	 * @return
//	 */
//	private static byte[] toByte(String hexString) {
//		int len = hexString.length() / 2;
//		byte[] result = new byte[len];
//		for (int i = 0; i < len; i++) {
//			result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
//		}
//		return result;
//	}

//	/**
//	 * 字节转16进制数组
//	 * 
//	 * @param buf
//	 * @return
//	 */
//	private static String parseByte2HexStr(byte buf[]) {
//		StringBuffer sb = new StringBuffer();
//		for (int i = 0; i < buf.length; i++) {
//			String hex = Integer.toHexString(buf[i] & 0xFF);
//			if (hex.length() == 1) {
//				hex = '0' + hex;
//			}
//			sb.append(hex);
//		}
//		return sb.toString();
//	}

	/**
	 * 
	 * @Title: encode 
	 * @Description: Base64 编码 
	 * @param @param bstr
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@SuppressWarnings("restriction")
	public static String encode(byte[] bstr) {
		return new sun.misc.BASE64Encoder().encode(bstr);
	}
	/**
	 * 
	 * @Title: decode 
	 * @Description: Base64 解编 
	 * @param @param str
	 * @param @return    设定文件 
	 * @return byte[]    返回类型 
	 * @throws
	 */
	@SuppressWarnings("restriction")
	public static byte[] decode(String str){    
		   byte[] bt = null;    
		   try {    
		       sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();    
		       bt = decoder.decodeBuffer( str );    
		   } catch (IOException e) {    
		       e.printStackTrace();    
		   }
		   return bt;
	}
    public static void main(String[] args) throws Exception {
		String key =CommonConstant.AES_SEED;
		String str = "uid==x123yang&ip=123.123.123.1&exp=1020029123";
		String mm = encrypt(str, key);
		System.out.println(mm);
		mm="QapisfCeKlFYEH/6/WttnPSlH5ktN60JMJzRzYsHNh8jZcVm+kO9u28L51YSYw7U";
		
		System.out.println(mm.length());
		System.out.println(decrypt(mm, key));
	}
}
