package by.restonov.tyrent.model.pool;

import by.restonov.tyrent.exception.ConnectionPoolException;
import by.restonov.tyrent.manager.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionCreator {
    private static final Logger logger = LogManager.getLogger();

    private static final String DRIVER = ConfigurationManager.getProperty("database.driver");
    private static final String URL = ConfigurationManager.getProperty("database.url");
    private static final String USER = ConfigurationManager.getProperty("database.user");
    private static final String PASSWORD = ConfigurationManager.getProperty("database.password");

    ProxyConnection createConnection() throws ConnectionPoolException {
        Connection connection;
        ProxyConnection proxyConnection;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            proxyConnection = new ProxyConnection(connection);
            return proxyConnection;
        } catch (SQLException e) {
            logger.error("Error while getting connection", e);
            throw new ConnectionPoolException("Error while getting connection", e);
        }
    }

    void registerDriver() throws ConnectionPoolException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            logger.fatal("Driver error", e);
            throw new ConnectionPoolException("Driver error", e);
        }
    }

    void deregisterDriver() throws ConnectionPoolException {
        Driver driver;
        try {
            driver = DriverManager.getDriver(DRIVER);
            DriverManager.deregisterDriver(driver);
        } catch (SQLException e) {
            logger.error("Error while deregistering driver", e);
            throw new ConnectionPoolException("Error while deregistering driver", e);
        }
    }
}
