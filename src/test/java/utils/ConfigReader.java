package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Читает настройки из файла config.properties
 * Все настройки хранятся в одном месте — легко менять без правки кода
 */
public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        // Загружаем файл из classpath (src/test/resources/)
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IllegalStateException("config.properties не найден в src/test/resources/");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Ошибка чтения config.properties", e);
        }
    }

    /** Возвращает значение по ключу */
    public static String get(String key) {
        return properties.getProperty(key);
    }

    /** Возвращает значение по ключу или значение по умолчанию */
    public static String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}