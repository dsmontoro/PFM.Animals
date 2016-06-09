package business.api.exceptions;

public class InvalidAnimalUserEception extends ApiException{


    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "Campo de Animal vacio o inexistente";

    public static final int CODE = 1;
    
    public InvalidAnimalUserEception() {
        this("");
    }

    public InvalidAnimalUserEception(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }
}
