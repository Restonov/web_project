package by.restonov.tyrent.model.exception;

/**
 * Exception, that throws by Service layer
 */
public class ServiceException extends Exception {

    /**
     * default constructors
     */
    public ServiceException() { }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
