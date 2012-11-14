package it.italiangrid.wnodes.exception;

public class WnodesPortletException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4554038922263117486L;
	private String message;
	
	public WnodesPortletException(String message){
		super();
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}
	
}
