package by.restonov.tyrent.model.exception;


/**
 * Exception, that throws by Pool layer
 */
public class ConnectionPoolException extends RuntimeException {

    /**
     * default constructors
     */
    public ConnectionPoolException() { }

    public ConnectionPoolException(String message) {
        super(message);
    }


    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }


    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
