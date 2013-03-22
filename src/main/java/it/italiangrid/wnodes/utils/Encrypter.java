package it.italiangrid.wnodes.utils;

public interface Encrypter {
	
	public byte[] encrypt(byte[] in)  throws Exception;
	public byte[] decrypt(byte[] in)  throws Exception;
}
