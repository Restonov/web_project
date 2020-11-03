package by.restonov.jspservlet.model.dao;

import by.restonov.jspservlet.exception.ConnectionPoolException;
import by.restonov.jspservlet.exception.DaoException;
import by.restonov.jspservlet.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction {
    private static final Logger logger = LogManager.getLogger();
    private Connection connection;
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public void initSingleQuery(AbstractDao dao) {
        if (connection == null) {
            try {
                connection = connectionPool.receiveConnection();
                dao.setConnection(connection);
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }
    }

    public void endSingleQuery() {
        if (connection != null) {
            try {
                connectionPool.releaseConnection(connection);
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
            connection = null;
        }
    }

    public void initMultipleQueries(AbstractDao dao, AbstractDao... daos) throws DaoException{
        if (connection == null) {
            try {
                connection = connectionPool.receiveConnection();
                connection.setAutoCommit(false);
            } catch (SQLException | ConnectionPoolException e) {
                logger.error("Error during transaction init", e);
                throw new DaoException("Error during transaction init", e);
            }
            dao.setConnection(connection);
            for (AbstractDao daoElement : daos) {
                daoElement.setConnection(connection);
            }
        }
    }

    public void endMultipleQueries() throws DaoException {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                connectionPool.releaseConnection(connection);
            } catch (SQLException | ConnectionPoolException e) {
                logger.error("Error during transaction ending", e);
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
                logger.error("Commit error", e);
                throw new DaoException("Commit error", e);
            }
        }
    }

    public void rollback() throws DaoException{
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.error("Error during rollback connection", e);
                throw new DaoException("Error during rollback connection", e);
            }
            connection = null;
        }
    }
}
