package it.italiangrid.wnodes.exception;

/**
 * The specific WNoDeS portlet exception class.
 * 
 * @author dmichelotto
 * 
 */
public class WnodesPortletException extends Exception {

	/**
	 * Serial identifier
	 */
	private static final long serialVersionUID = 4554038922263117486L;

	/**
	 * The exception message.
	 */
	private String message;

	/**
	 * The exception constructor.
	 * 
	 * @param message
	 *            - The exception message.
	 */
	public WnodesPortletException(String message) {
		super();
		this.message = message;
	}

	/**
	 * Get the exception message.
	 * 
	 * @return Return the exception message.
	 */
	public String getMessage() {
		return this.message;
	}

}
