package com.example.commoncustomizecore.api.encryption.secure;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA 非对称加密，持有公钥的乙方
 * @author linling
 *
 */
public class RSAForPublicCodec extends BasicCodec
{

	private static final String ALGORITHM = "RSA";
	private static final String SIGN_ALGORITHM = "SHA1withRSA";
	/**
	 * RSA最大解密密文大小
	 */
	private static final int    MAX_DECRYPT_BLOCK   = 128;
	
	public RSAForPublicCodec(String publicKey)
	{
		super();
		super.publicKey = publicKey;
	}
	
	@Override
	public byte[] encrypt(byte[] data) throws Exception
	{
		if(publicKey == null || "".equals(publicKey))
		{
			throw new Exception("publicKey is need exists");
		}
		
		PublicKey rsaPublicKey = getRSAPublicKey(publicKey);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
		return cipher.doFinal(data);
	}

	@Override
	public byte[] decrypt(byte[] data) throws Exception
	{
		if(publicKey == null || "".equals(publicKey))
		{
			throw new Exception("publicKey is need exists");
		}

		byte[] keyBytes = Base64.decodeBase64(publicKey);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();

		return decryptedData;
	}
	
	private PublicKey getRSAPublicKey(String key) throws Exception
	{
		X509EncodedKeySpec x509EncoderKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(key));
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		return keyFactory.generatePublic(x509EncoderKeySpec);
	}
	
	/**
	 * 使用公钥校验签名
	 * @param data
	 * @param sign
	 * @return
	 * @throws Exception
	 */
	public boolean verifySign(byte[] data, String sign) throws Exception
	{
		if(publicKey == null || "".equals(publicKey))
		{
			throw new Exception("publicKey is need exists");
		}
		
		PublicKey rsaPublicKey = getRSAPublicKey(publicKey);
		Signature signature = Signature.getInstance(SIGN_ALGORITHM);
		signature.initVerify(rsaPublicKey);
		signature.update(data);
		return signature.verify(Base64.decodeBase64(sign));
	}

}
