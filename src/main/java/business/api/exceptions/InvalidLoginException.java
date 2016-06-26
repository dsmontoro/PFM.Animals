package business.api.exceptions;

public class InvalidLoginException extends ApiException{
    
    /**
     * 
     */
    private static final long serialVersionUID = 4644059700455109963L;

    public static final String DESCRIPTION = "Error en Login";

    public static final int CODE = 1;
    
    public InvalidLoginException() {
        this("");
    }

    public InvalidLoginException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
