package by.restonov.tyrent.model.dao;

import by.restonov.tyrent.model.exception.DaoException;
import by.restonov.tyrent.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction {
    private Connection connection;

    public void initSingleQuery(AbstractDao dao) {
        if (connection == null) {
            connection = ConnectionPool.INSTANCE.receiveConnection();
            dao.setConnection(connection);
        }
    }

    public void endSingleQuery() {
        if (connection != null) {
            ConnectionPool.INSTANCE.releaseConnection(connection);
            connection = null;
        }
    }

    public void initMultipleQueries(AbstractDao dao, AbstractDao... daos) throws DaoException{
        if (connection == null) {
            try {
                connection = ConnectionPool.INSTANCE.receiveConnection();
                connection.setAutoCommit(false);
                dao.setConnection(connection);
                for (AbstractDao daoElement : daos) {
                    daoElement.setConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("Error during transaction init", e);
            }
        }
    }

    public void endMultipleQueries() throws DaoException {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                ConnectionPool.INSTANCE.releaseConnection(connection);
            } catch (SQLException e) {
                throw new DaoException("Error during transaction ending", e);
            }
            connection = null;
        }
    }

    public void commit() throws DaoException {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                throw new DaoException("Commit error", e);
            }
        }
    }

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
