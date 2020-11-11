package by.restonov.tyrent.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public enum ConnectionPool {
    INSTANCE;

    private static final Logger logger = LogManager.getLogger();

    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> activeConnections;
    private ConnectionCreator connectionCreator;
    public static final int MAX_POOL_SIZE = 10;

    ConnectionPool() {
        freeConnections = new LinkedBlockingQueue<>(MAX_POOL_SIZE);
        activeConnections = new LinkedBlockingQueue<>(MAX_POOL_SIZE);
        connectionCreator = ConnectionCreator.INSTANCE;
        connectionCreator.registerDriver();
        for (int i = 0; i < MAX_POOL_SIZE; i++) {
            freeConnections.offer(connectionCreator.createConnection());
        }
    }

    public Connection receiveConnection() {
            ProxyConnection connection = null;
            try {
                connection = freeConnections.take();
                activeConnections.put(connection);
            } catch (InterruptedException e) {
                logger.error("Connection not found", e);
                Thread.currentThread().interrupt();
            }
            return connection;
        }

        public void releaseConnection(Connection connection) {
            if (connection != null) {
                if (connection instanceof ProxyConnection && activeConnections.remove(connection)) {
                    try {
                        freeConnections.put((ProxyConnection) connection);
                    } catch (InterruptedException e) {
                        logger.error("Error while releasing connection", e);
                        Thread.currentThread().interrupt();
                    }
                } else {
                    logger.error("Received Connection is not ProxyConnection");
                }
            } else {
                logger.error("Method received null");
            }
        }

        public void shutdownPoll() {
            for (int i = 0; i < freeConnections.size(); i++) {
                try {
                    ProxyConnection connection = freeConnections.take();
                    connection.closeConnection();
                } catch (InterruptedException | SQLException e) {
                    logger.error("Exception during shutdown poll", e);
                    Thread.currentThread().interrupt();
                }
                connectionCreator.deregisterDriver();
            }
        }
}
