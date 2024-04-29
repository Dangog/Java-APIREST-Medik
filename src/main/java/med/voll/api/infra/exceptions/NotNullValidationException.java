package med.voll.api.infra.exceptions;

public class NotNullValidationException extends RuntimeException{

    public NotNullValidationException(String s) {
        super(s);
    }
}
