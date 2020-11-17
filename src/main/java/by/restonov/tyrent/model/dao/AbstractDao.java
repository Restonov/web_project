package by.restonov.tyrent.model.dao;

import by.restonov.tyrent.model.exception.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Dao layer main class
 * works with DB
 *
 */
public abstract class AbstractDao <K, T>{
    protected Connection connection;

    /**
     * find all Entities in DB
     *
     * @return List of Entities
     * @throws DaoException - general Exception of Dao layer
     */
    public abstract List<T> findAll() throws DaoException;

    /**
     * find Entity by it's ID
     *
     * @param id Entity ID
     * @return Optional of Entity type
     * @throws DaoException - general Exception of Dao layer
     */
    public abstract Optional<T> findById(K id) throws DaoException;

    /**
     * find Entity by it's name
     *
     * @param name Entity name
     * @return Optional of Entity type
     * @throws DaoException - general Exception of Dao layer
     */
    public abstract Optional<T> findByName(String name) throws DaoException;

    /**
     * add Entity to DB
     *
     * @param entity Entity
     * @return result of operation
     * @throws DaoException - general Exception of Dao layer
     */
    public abstract boolean add(T entity) throws DaoException;

    /**
     * update exists Entity in DB
     *
     * @param entity Entity
     * @return result of operation
     * @throws DaoException - general Exception of Dao layer
     */
    public abstract boolean update(T entity) throws DaoException;

    /**
     * delete exists Entity from DB
     *
     * @param id Entity id
     * @return result of operation
     * @throws DaoException - general Exception of Dao layer
     */
    public abstract boolean delete(K id) throws DaoException;

    /**
     * Connection setting
     *
     * @param connection - connection with DB
     */
    void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Closing ResultSet in Dao methods
     *
     * @param set - ResultSet
     */
    protected void closeResultSet(ResultSet set) throws DaoException {
        try {
            set.close();
        } catch (SQLException e) {
            throw new DaoException("Error while closing Result Set", e);
        }
    }
}
