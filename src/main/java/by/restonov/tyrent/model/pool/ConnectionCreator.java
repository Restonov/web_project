package by.restonov.tyrent.model.pool;

import by.restonov.tyrent.model.exception.ConnectionPoolException;
import by.restonov.tyrent.manager.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

enum ConnectionCreator {
    INSTANCE;

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
            throw new ConnectionPoolException("Error while getting connection", e);
        }
    }

    void registerDriver() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            logger.fatal("Driver error", e);
        }
    }

    void deregisterDriver() throws ConnectionPoolException {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Error while deregister drivers", e);
            }
        }
    }
}
