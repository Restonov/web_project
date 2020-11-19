package model.pool;

import by.restonov.tyrent.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;

public class ConnectionPoolTest extends Assert {
    Logger logger;
    ConnectionPool pool;


    @BeforeClass
    public void setUp() {
        logger = LogManager.getLogger();
        pool = ConnectionPool.INSTANCE;
    }

    @AfterClass
    public void tierDown() {
        logger = null;
        pool = null;
    }

    @Test
    public void provideConnectionTest() throws InterruptedException {
        int clients = 20;
        logger.info("Max pool size = {}", ConnectionPool.MAX_POOL_SIZE);
        logger.info("Attempt to start {} clients at the same time", clients);

        Thread workEmulation = new Thread(() -> emulateClientWork(clients));
        workEmulation.start();
        workEmulation.join();
    }

    private void emulateClientWork(int clients){
        for (int i = 0; i < clients; i++) {
            Thread client = new Thread(() -> {
                Connection connection = pool.provideConnection();
                pool.takeBackConnection(connection);
                logger.info("Client performed his work");
            });
            client.start();
        }
    }
}
