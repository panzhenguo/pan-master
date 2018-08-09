package com.pan.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	/**
	 * 默认的密码字符串组合，用来将字节转换�? 16 进制表示的字�?,apache校验下载的文件的正确性用的就是默认的这个组合
	 */
	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	protected static MessageDigest messagedigest = null;
	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsaex) {
			System.err.println(MD5Utils.class.getName() + "初始化失败，MessageDigest不支持MD5Util�?");
			nsaex.printStackTrace();
		}
	}
	/**
	 * 生成字符串的md5校验�?
	 * 
	 * @param s
	 * @return
	 */
	public static String getMD5String(String s) {
		return getMD5String(s.getBytes());
	}

	/**
	 * 判断字符串的md5校验码是否与�?个已知的md5码相匹配
	 * 
	 * @param password
	 *            要校验的字符�?
	 * @param md5PwdStr
	 *            已知的md5校验�?
	 * @return
	 */
	public static boolean isEqualsToMd5(String password, String md5PwdStr) {
		String s = getMD5String(password);
		return s.equals(md5PwdStr);
	}

	/**
	 * 生成文件的md5校验�?
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String getFileMD5String(File file) throws IOException {
		InputStream fis;
		fis = new FileInputStream(file);
		byte[] buffer = new byte[1024];
		int numRead = 0;
		while ((numRead = fis.read(buffer)) > 0) {
			messagedigest.update(buffer, 0, numRead);
		}
		fis.close();
		return bufferToHex(messagedigest.digest());
	}

	/**
	 * 生成字节数组的md5校验�?
	 * 
	 * @param s
	 * @return
	 */
	public static String getMD5String(byte[] bytes) {
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest());
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];// 取字节中�? 4 位的数字转换, >>>
		// 为�?�辑右移，将符号位一起右�?,此处未发现两种符号有何不�?
		char c1 = hexDigits[bt & 0xf];// 取字节中�? 4 位的数字转换
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

	/**
	 * 将源字符串使用MD5加密为字节数�?
	 * 
	 * @param source
	 * @return
	 */
	public static byte[] encode2bytes(String source) {
		byte[] result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(source.getBytes("UTF-8"));
			result = md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 将源字符串使用MD5加密�?32�?16进制�?
	 * 
	 * @param source
	 * @return
	 */
	public static String encode2hex(String source) {
		byte[] data = encode2bytes(source);
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			String hex = Integer.toHexString(0xff & data[i]);

			if (hex.length() == 1) {
				hexString.append('0');
			}

			hexString.append(hex);
		}

		return hexString.toString();
	}

	/**
	 * 验证字符串是否匹�?
	 * 
	 * @param unknown
	 *            待验证的字符�?
	 * @param okHex
	 *            使用MD5加密过的16进制字符�?
	 * @return 匹配返回true，不匹配返回false
	 */
	public static boolean validate(String unknown, String okHex) {
		return okHex.equals(encode2hex(unknown));
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(getMD5String("123"+"ib5tbfkuh2mq"));
		
		
		
	}
	
}
