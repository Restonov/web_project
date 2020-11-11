package by.restonov.tyrent.util;

import java.time.LocalDateTime;

public class UserOrderIdGenerator {
    private UserOrderIdGenerator() {}

    public static long generateId(long carId, long userId, LocalDateTime orderCreationDateTime) {
        int nano = orderCreationDateTime.getNano();
        return carId + userId + nano;
    }
}
