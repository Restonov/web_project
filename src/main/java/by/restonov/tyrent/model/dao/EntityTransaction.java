package by.restonov.tyrent.model.dao;

import by.restonov.tyrent.model.entity.ApplicationEntity;
import by.restonov.tyrent.model.exception.DaoException;
import by.restonov.tyrent.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Transaction with DB
 */
public class EntityTransaction {
    private Connection connection;

    /**
     * Get connection for single dao query
     *
     * @param dao App Dao
     */
    public void initSingleQuery(AbstractDao<Long, ? extends ApplicationEntity> dao) {
        if (connection == null) {
            connection = ConnectionPool.INSTANCE.provideConnection();
            dao.setConnection(connection);
        }
    }

    /**
     * End single dao query
     */
    public void endSingleQuery() {
        if (connection != null) {
            ConnectionPool.INSTANCE.takeBackConnection(connection);
            connection = null;
        }
    }

    /**
     * Get connection for multiple dao queries
     * set DB auto commit - to false
     * for manual commit
     *
     * @param dao App Dao
     * @param anotherDao one more App Dao
     * @throws DaoException - general Exception of Dao layer
     */
    @SafeVarargs
    public final void initMultipleQueries(AbstractDao<Long, ? extends ApplicationEntity> dao, AbstractDao<Long, ? extends ApplicationEntity>... anotherDao) throws DaoException{
        if (connection == null) {
            try {
                connection = ConnectionPool.INSTANCE.provideConnection();
                connection.setAutoCommit(false);
                dao.setConnection(connection);
                for (AbstractDao<Long, ? extends ApplicationEntity> daoElement : anotherDao) {
                    daoElement.setConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("Error during transaction init", e);
            }
        }
    }

    /**
     * End multiple dao queries
     * set DB commit back to auto
     * take back connection
     *
     * @throws DaoException - general Exception of Dao layer
     */
    public void endMultipleQueries() throws DaoException {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                ConnectionPool.INSTANCE.takeBackConnection(connection);
            } catch (SQLException e) {
                throw new DaoException("Error during transaction ending", e);
            }
            connection = null;
        }
    }

    /**
     * Manual commit dao multiple queries
     *
     * @throws DaoException - general Exception of Dao layer
     */
    public void commit() throws DaoException {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                throw new DaoException("Commit error", e);
            }
        }
    }

    /**
     * Rollback changes
     *
     * @throws DaoException - general Exception of Dao layer
     */
    public void rollback() throws DaoException{
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new DaoException("Error during rollback connection", e);
            }
            connection = null;
        }
    }
}
