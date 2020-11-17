package by.restonov.tyrent.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Pool of connections for DB
 */
public enum ConnectionPool {
    /**
     * Thread-safe Singleton instance
     */
    INSTANCE;

    private static final Logger logger = LogManager.getLogger();
    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> activeConnections;
    private ConnectionCreator connectionCreator;
    public static final int MAX_POOL_SIZE = 10;

    /**
     * ConnectionPool initialization
     *
     */
    ConnectionPool() {
        freeConnections = new LinkedBlockingQueue<>(MAX_POOL_SIZE);
        activeConnections = new LinkedBlockingQueue<>(MAX_POOL_SIZE);
        connectionCreator = ConnectionCreator.INSTANCE;
        connectionCreator.registerDriver();
        for (int i = 0; i < MAX_POOL_SIZE; i++) {
            ProxyConnection connection = connectionCreator.createConnection();
            freeConnections.add(connection);
        }
    }

    /**
     * Provide connection to the DAO
     * if it's available in the Pool
     * otherwise wait until new connection
     * become available
     *
     * @return Proxy connection
     */
    public Connection provideConnection() {
            ProxyConnection connection = null;
            try {
                connection = freeConnections.take();
                activeConnections.put(connection);
            } catch (InterruptedException e) {
                logger.error("Provide connection operation - error", e);
                Thread.currentThread().interrupt();
            }
            return connection;
        }

    /**
     * Take back connection to the pool
     *
     * @param connection the connection
     */
    public void takeBackConnection(Connection connection) {
            if (connection != null) {
                if (connection instanceof ProxyConnection && activeConnections.remove(connection)) {
                    try {
                        freeConnections.put((ProxyConnection) connection);
                    } catch (InterruptedException e) {
                        logger.error("Take back connection operation - error", e);
                        Thread.currentThread().interrupt();
                    }
                } else {
                    logger.error("Received Connection is not ProxyConnection");
                }
            } else {
                logger.error("Method received null");
            }
        }

    /**
     * Shutdown pool when application stops
     * wait for connection become available for closing
     */
    public void shutdown() {
            for (int i = 0; i < freeConnections.size(); i++) {
                try {
                    ProxyConnection connection = freeConnections.take();
                    connection.closeConnection();
                } catch (InterruptedException | SQLException e) {
                    logger.error("Exception during shutdown poll", e);
                    Thread.currentThread().interrupt();
                } finally {
                    connectionCreator.deregisterDriver();
                }
            }
        }
}
