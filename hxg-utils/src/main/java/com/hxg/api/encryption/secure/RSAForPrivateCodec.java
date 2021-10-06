package com.hxg.api.encryption.secure;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * RSA 非对称加密，持有私钥的甲方
 * @author linling
 *
 */
public class RSAForPrivateCodec extends BasicCodec
{
	private static final String ALGORITHM = "RSA";


	/**
	 * RSA最大解密密文大小
	 */
	private static final int    MAX_DECRYPT_BLOCK   = 128;
	
	//rsa，签名算法可以是 md5withrsa 、 sha1withrsa 、 sha256withrsa 、 sha384withrsa 、 sha512withrsa
//	private static final String SIGN_ALGORITHM = "MD5withRSA";
	private static final String SIGN_ALGORITHM = "SHA1withRSA";
	private static final int KEY_SIZE = 1024;


	public RSAForPrivateCodec(String key) throws NoSuchAlgorithmException
	{
		this.privateKey = key;
	}

	@Override
	public byte[] encrypt(byte[] data) throws Exception 
	{
		PrivateKey rsaPrivateKey = getRSAPrivateKey();
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, rsaPrivateKey);
		return cipher.doFinal(data);
	}

	@Override
	public byte[] decrypt(byte[] data) throws Exception 
	{

//		PrivateKey rsaPrivateKey = getRSAPrivateKey();
//		Cipher cipher = Cipher.getInstance(ALGORITHM);

		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(super.privateKey));
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		PrivateKey rsaPrivateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
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


	/**
	 * 初始化私钥和公钥
	 * @throws NoSuchAlgorithmException
	 */
	private void initKey() throws NoSuchAlgorithmException
	{
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
		keyPairGenerator.initialize(KEY_SIZE);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		PrivateKey rsaPrivateKey = keyPair.getPrivate();
		PublicKey rsaPublicKey = keyPair.getPublic();
		super.privateKey = encoder(rsaPrivateKey.getEncoded());
		super.publicKey = encoder(rsaPublicKey.getEncoded());
	}
	
	private PrivateKey getRSAPrivateKey() throws Exception
	{
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(super.privateKey));
		KeyFactory keyFacotry = KeyFactory.getInstance(ALGORITHM);
		return keyFacotry.generatePrivate(pkcs8EncodedKeySpec);
	}
	
	/*private PublicKey getRSAPublicKey() throws Exception
	{
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decoder(super.publicKey));
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		return keyFactory.generatePublic(x509EncodedKeySpec);
	}*/
	
	/**
	 * 使用私钥 对数据进行签名
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public String sign(byte[] data) throws Exception
	{
		PrivateKey rsaPrivateKey = getRSAPrivateKey();
		Signature signature = Signature.getInstance(SIGN_ALGORITHM);
		signature.initSign(rsaPrivateKey);
		signature.update(data);
		return Base64.encodeBase64String(signature.sign());
	}
}
