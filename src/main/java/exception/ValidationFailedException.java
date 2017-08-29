package exception;

/**
 * Created by roshanalwis on 8/24/17.
 */
public class ValidationFailedException extends Exception {
    public ValidationFailedException(){

    }

    public ValidationFailedException(String message){
        super(message);
    }
}
