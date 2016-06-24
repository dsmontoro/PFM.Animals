package business.api.exceptions;

public class InvalidAnimalTypeException extends ApiException{

	 private static final long serialVersionUID = -1344640670884805385L;

	    public static final String DESCRIPTION = "Tipo animal no valido";

	    public static final int CODE = 1;
	    
	    public InvalidAnimalTypeException() {
	        this("");
	    }

	    public InvalidAnimalTypeException(String detail) {
	        super(DESCRIPTION + ". " + detail, CODE);
	    }
}
