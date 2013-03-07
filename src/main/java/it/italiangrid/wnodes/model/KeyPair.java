/**
 * 
 */
package it.italiangrid.wnodes.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author dmichelotto
 *
 */
public class KeyPair {

	private CommonsMultipartFile privateKey;
	private CommonsMultipartFile publicKey;
	
	public KeyPair() {
	}
	
	/**
	 * @param privateKey
	 * @param publicKey
	 */
	public KeyPair(CommonsMultipartFile privateKey,
			CommonsMultipartFile publicKey) {
		super();
		this.privateKey = privateKey;
		this.publicKey = publicKey;
	}
	/**
	 * @return the privateKey
	 */
	public CommonsMultipartFile getPrivateKey() {
		return privateKey;
	}
	/**
	 * @param privateKey the privateKey to set
	 */
	public void setPrivateKey(CommonsMultipartFile privateKey) {
		this.privateKey = privateKey;
	}
	/**
	 * @return the publicKey
	 */
	public CommonsMultipartFile getPublicKey() {
		return publicKey;
	}
	/**
	 * @param publicKey the publicKey to set
	 */
	public void setPublicKey(CommonsMultipartFile publicKey) {
		this.publicKey = publicKey;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KeyPair [privateKey=" + privateKey.getName() + ", publicKey=" + publicKey.getName()
				+ "]";
	}
	
}
