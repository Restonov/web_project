package by.restonov.tyrent.model.exception;

/**
 * Exception, that throws by Dao layer
 */
public class DaoException extends Exception{

    /**
     * default constructors
     */
    public DaoException() { }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
