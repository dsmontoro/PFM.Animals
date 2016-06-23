package business.api.exceptions;

public class InvalidTypeException extends ApiException{

	private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "Type no válido";

    public static final int CODE = 1;

    public InvalidTypeException() {
        this("");
    }

    public InvalidTypeException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }
}
