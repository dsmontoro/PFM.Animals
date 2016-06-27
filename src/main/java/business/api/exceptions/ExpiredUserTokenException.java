package business.api.exceptions;

public class ExpiredUserTokenException extends ApiException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1851323466140910647L;

	public static final String DESCRIPTION = "Exception de token";

    public static final int CODE = 1;
    
    public ExpiredUserTokenException() {
        this("");
    }

    public ExpiredUserTokenException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
