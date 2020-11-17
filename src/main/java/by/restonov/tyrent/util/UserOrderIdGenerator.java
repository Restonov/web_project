package by.restonov.tyrent.util;

import java.time.LocalDateTime;

public class UserOrderIdGenerator {
    private UserOrderIdGenerator() {}

    public static long generateId(long carId, long userId, LocalDateTime orderCreationDateTime) {
        StringBuilder stringId = new StringBuilder();
        stringId.append(userId).append(carId).append(orderCreationDateTime.getNano());
        return Long.parseLong(stringId.toString());
    }
}
