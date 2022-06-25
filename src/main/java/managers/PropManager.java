package managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropManager {
    private static PropManager INSTANCE;
    private final Properties properties = new Properties();

    // пустой конструктор (singleton)
    private PropManager() {
        loadApplicationProperties();
    }

    // инициализация PropManager
    public static PropManager getPropManager() {
        if (INSTANCE == null) {
            INSTANCE = new PropManager();
        }
        return INSTANCE;
    }

    // считывает enviroment.properties
    private void loadApplicationProperties() {
        String propFileName = System.getProperty("propFile", "enviroment");
        try {
            properties.load(new FileInputStream(
                    new File("src/main/resources/" + propFileName + ".properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}