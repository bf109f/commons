package com.hxg.api.encryption.secure;

import java.security.MessageDigest;

/**
 * MD5 单向加密
 * 加密的结果默认是32位小写
 * String md5_16 = md5_32.substring(8, 24);
 * @author linling
 *
 */
public class MD5Codec extends BasicCodec
{
	private static final String ALGORITHM = "MD5";

	@Override
	public byte[] encrypt(byte[] data) throws Exception 
	{
		MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
		return messageDigest.digest(data);
	}

	@Override
	public byte[] decrypt(byte[] data) throws Exception 
	{
		return null;
	}
	
	/**
	 * 返回MD5单向加密后的十六进制字符串
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public String getEncryptForHex(byte[] data) throws Exception
	{
		byte[] digestData = encrypt(data);
		return parseByteArray2HexStr(digestData);
	}

}
