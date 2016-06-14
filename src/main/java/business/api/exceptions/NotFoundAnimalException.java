package business.api.exceptions;

public class NotFoundAnimalException extends ApiException {

	private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "No se encuentra el identificador de animal utilizado";

    public static final int CODE = 333;

    public NotFoundAnimalException() {
        this("");
    }

    public NotFoundAnimalException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }
    
}
