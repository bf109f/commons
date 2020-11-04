package com.example.commoncustomizecore.api.encryption.factory;

import com.example.commoncustomizecore.api.constants.SecureConstant;
import com.example.commoncustomizecore.api.encryption.secure.*;

import java.security.NoSuchAlgorithmException;



public class SecureFactory
{

	public static BasicCodec getCodec(SecureConstant type, String key) throws NoSuchAlgorithmException
	{
		BasicCodec codec = null;
		switch(type)
		{
			case MD5:
				codec = new MD5Codec();
				break;
			case SHA:
				codec = new SHACodec();
				break;
			case DES:
				if(key != null && !"".equals(key))
				{
					codec = new DESCodec(key);
				}
				else
				{
					codec = new DESCodec();
				}
				break;
			case AES:
				if(key != null && !"".equals(key))
				{
					codec = new AESCodec(key);
				}
				else
				{
					codec = new AESCodec();
				}
				break;
			case RSA_PRIVATE:
				codec = new RSAForPrivateCodec();
				break;
			case RSA_PUBLIC:
				codec = new RSAForPublicCodec(key);
				break;
			default :
				codec = new NoSecureCodec();
		}
		return codec;
	}
}
