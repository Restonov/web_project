package by.restonov.jspservlet.model.dao;

import by.restonov.jspservlet.exception.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao <K, T>{
    protected Connection connection;

    public abstract List<T> findAll() throws DaoException;
    public abstract Optional<T> findById(K id) throws DaoException;
    public abstract Optional<T> findByName(String name) throws DaoException;
    public abstract boolean add(T entity) throws DaoException;
    public abstract T update(T entity);
    public abstract boolean delete(K id) throws DaoException;

    /**
     * Connection setting
     *
     * @param connection - connection with database
     */
    void setConnection(Connection connection) {
        this.connection = connection;
    }

    protected void closeResultSet(ResultSet set) throws DaoException {
        try {
            set.close();
        } catch (SQLException e) {
            throw new DaoException("Error while closing Result Set", e);
        }
    }
}
