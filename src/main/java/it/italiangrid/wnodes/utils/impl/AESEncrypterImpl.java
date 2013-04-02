package it.italiangrid.wnodes.utils.impl;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import it.italiangrid.wnodes.utils.Encrypter;

public class AESEncrypterImpl implements Encrypter {

	byte[] buf = new byte[1024];
	Cipher ecipher;
	Cipher dcipher;
	
	public AESEncrypterImpl(String plainTextKey) throws Exception{
		
//		byte[] bytes = plainTextKey.getBytes(); 
//		BigInteger bigInt = new BigInteger(bytes);
//		String hexString = bigInt.toString(16);
//		
//		byte[] symKeyData = DatatypeConverter.parseHexBinary(hexString);
		byte[] r = new byte[] { 0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6,
				0x7, 0x8, 0x9, 0xA, 0xB, 0xC, 0xD, 0xE, 0xF };
		SecretKeySpec key = new SecretKeySpec(r, "AES");
		
		byte[] iv = new byte[] { (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A, (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A };
		IvParameterSpec paramSpec = new IvParameterSpec(iv);
		ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
		dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
	}
	
	
	public byte[] encrypt(byte[] in)  throws Exception{
		return ecipher.doFinal(in);
	}
	
	public byte[] decrypt(byte[] in)  throws Exception{
		return dcipher.doFinal(in);
	}
}
