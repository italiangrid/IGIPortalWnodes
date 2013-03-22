package it.italiangrid.wnodes.utils.impl;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import it.italiangrid.wnodes.utils.Encrypter;

public class DESEncrypterImpl implements Encrypter {

	byte[] buf = new byte[1024];
	Cipher ecipher;
	Cipher dcipher;
	
	public DESEncrypterImpl(String plainTextKey) throws Exception{
		
		byte[] bytes = plainTextKey.getBytes(); // you didn't say what charset you wanted
		BigInteger bigInt = new BigInteger(bytes);
		String hexString = bigInt.toString(16);
		
		byte[] symKeyData = DatatypeConverter.parseHexBinary(hexString);
		SecretKeySpec key = new SecretKeySpec(symKeyData, "AES");
		
		byte[] iv = new byte[] { (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A };
		AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
		
		ecipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		dcipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		
		ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
		dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
	}
	
	
	public byte[] encrypt(byte[] in)  throws Exception{
		return ecipher.doFinal();
	}
	
	public byte[] decrypt(byte[] in)  throws Exception{
		return dcipher.doFinal();
	}
}
