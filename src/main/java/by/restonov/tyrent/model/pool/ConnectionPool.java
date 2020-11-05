package by.restonov.tyrent.model.pool;

import by.restonov.tyrent.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    static Logger logger = LogManager.getLogger();

    private static ConnectionPool instance;
    private static Lock lock = new ReentrantLock();
    private LinkedBlockingQueue<ProxyConnection> freeConnections;
    private LinkedBlockingQueue<ProxyConnection> activeConnections;
    private ConnectionCreator connectionCreator;
    private static AtomicBoolean flag = new AtomicBoolean(false);

    public static final int MIN_POOL_SIZE = 10;

    public ConnectionPool() throws ConnectionPoolException {
        freeConnections = new LinkedBlockingQueue<>(MIN_POOL_SIZE);
        activeConnections = new LinkedBlockingQueue<>(MIN_POOL_SIZE);
        connectionCreator = new ConnectionCreator();
        connectionCreator.registerDriver();
        for (int i = 0; i < MIN_POOL_SIZE; i++) {
            freeConnections.offer(connectionCreator.createConnection());
        }
    }

    public static ConnectionPool getInstance() {
        if (flag.get() == false) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    flag.set(true);
                }
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

        public Connection receiveConnection() throws ConnectionPoolException{
            ProxyConnection connection = null;
            try {
                connection = freeConnections.take();
                activeConnections.put(connection);
            } catch (InterruptedException e) {
                logger.error("Error while taking connection", e);
                throw new ConnectionPoolException("Connection not found", e);
            }
            return connection;
        }

        public void releaseConnection(Connection connection) throws ConnectionPoolException {
            if (connection != null) {
                if (connection instanceof ProxyConnection && activeConnections.remove(connection)) {
                    try {
                        freeConnections.put((ProxyConnection) connection);
                    } catch (InterruptedException e) {
                        logger.error("Error while releasing connection", e);
                        throw new ConnectionPoolException("Error while releasing connection", e);
                    }
                } else {
                    logger.error("Received Connection is not ProxyConnection");
                    throw new ConnectionPoolException("Received Connection is not ProxyConnection");
                }
            }
        }

        public void shutdownPoll() throws ConnectionPoolException {
            for (int i = 0; i < freeConnections.size(); i++) {
                try {
                    ProxyConnection connection = freeConnections.take();
                    connection.closeConnection();
                } catch (Exception e) {
                    logger.error("Error while poll destroying", e);
                    throw new ConnectionPoolException("Error while poll destroying", e);
                }
            }
            connectionCreator.deregisterDriver();
        }
}
