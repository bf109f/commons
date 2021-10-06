package com.hxg.api.encryption.secure;

import java.io.IOException;
import java.util.Base64;


/**
 * Base64 编解码工具类
 * @author linling
 *
 */
public class Base64Utils 
{

	private static Base64Utils base64Utils = new Base64Utils();

	
	private Base64Utils() 
	{

	}
	
	public static Base64Utils getInstance() 
	{
		return base64Utils;
	}
	
	/**
	 * base64 编码
	 * @param data
	 * @return
	 */
	public String encoder(byte[] data) 
	{
		return Base64.getEncoder().encodeToString(data);
	}
	
	/**
	 * base64 解码
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public byte[] decoderToByte(String data) throws IOException
	{
		return Base64.getDecoder().decode(data);
	}


	public String decoderToString(String data)
	{
		return new String(Base64.getDecoder().decode(data));
	}
}
