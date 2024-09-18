package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    public static Properties load() {
        Properties configuration = new Properties();
        try (
                InputStream inputStream = PropertiesLoader.class
                        .getClassLoader()
                        .getResourceAsStream("application.properties");) {
            configuration.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("File application.properties is not found");
        }
        return configuration;
    }
}
