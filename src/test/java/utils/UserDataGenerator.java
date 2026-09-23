package utils;

import java.util.UUID;

/**
 * Генератор уникальных тестовых данных
 */
public final class UserDataGenerator {
    private UserDataGenerator() {

    }

    /**
     * Возвращает уникальный email
     */
    public static String uniqueEmail() {
        long timestamp = System.currentTimeMillis();
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        return "puh_" + timestamp + "_" + uuid + "@example.com";
    }

    /**
     * Возвращает уникальное имя
     */
    public static String uniqueName() {
        return "Puh" + System.currentTimeMillis();
    }

    /**
     * Валидный пароль (6+ символов, как требует сервер)
     */
    public static String validPassword() {
        return "pass" + System.currentTimeMillis();
    }

    /**
     * Невалидный (короткий) пароль для негативных тестов
     */
    public static String invalidPassword() {
        return "666";
    }
}
