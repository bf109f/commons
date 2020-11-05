package com.example.commoncustomizecore.api.encryption.secure;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
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
		
		PublicKey rsaPublicKey = getRSAPublicKey(publicKey);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, rsaPublicKey);
		return cipher.doFinal(data);
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