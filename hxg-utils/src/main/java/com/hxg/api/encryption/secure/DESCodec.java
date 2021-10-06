package com.hxg.api.encryption.secure;

import com.hxg.api.commons.CommonCoreUtils;
import com.hxg.api.exception.CommonsCoreException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * DES 对称加密类
 * @author linling
 *
 */
public class DESCodec extends BasicCodec
{

	private static final String ALGORITHM = "DES";
	
	/**
	 * 当为密钥生产者（甲方）调用该构造方法
	 * @throws NoSuchAlgorithmException
	 */
	public DESCodec() throws NoSuchAlgorithmException 
	{
		super();
		initKey();
	}
	
	/**
	 * 当为密钥接受者（乙方），调用该构造方法。
	 * @param secretKey 密钥
	 */
	public DESCodec(String secretKey)
	{
		super.secretKey = secretKey;
	}
	
	
	@Override
	public byte[] encrypt(byte[] data) throws Exception 
	{
		if(secretKey == null || "".equals(secretKey))
		{
			throw new Exception("scretKey need to exists");
		}
		
		SecretKey md5Key = getKey(secretKey);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, md5Key);
		return cipher.doFinal(data);
	}

	@Override
	public byte[] decrypt(byte[] data) throws Exception 
	{
		if(secretKey == null || "".equals(secretKey))
		{
			throw new Exception("scretKey need to exists");
		}
		
		SecretKey mesKey = getKey(secretKey);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, mesKey);
		return cipher.doFinal(data);
	}

	/**
	 * 生成DES对称秘钥，并对DES对称秘钥进行base64编码
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private void initKey() throws NoSuchAlgorithmException 
	{
		KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
		SecureRandom secureRandom = new SecureRandom();
		keyGenerator.init(secureRandom);
		SecretKey desKey = keyGenerator.generateKey();
		super.secretKey = encoder(desKey.getEncoded());
	}
	
	/**
	 * 获取对称密钥
	 * @param key base64编码后的密钥字符串
	 * @return
	 * @throws Exception
	 */
	private SecretKey getKey(String key) throws Exception
	{
		if (!CommonCoreUtils.isBase64(key))
		{
			throw new CommonsCoreException("key需要base64编码");
		}
		byte [] decodeKey = Base64.decodeBase64(key);
		if (decodeKey.length < 8)
		{
			throw new CommonsCoreException("des密钥字节长度小于8，传入长度为[" + decodeKey.length + "]");
		}
		DESKeySpec desKeySpec = new DESKeySpec(decoder(key));
		SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		return secretKeyFactory.generateSecret(desKeySpec);
	}
}
