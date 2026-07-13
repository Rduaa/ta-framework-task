package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {

  private static final String DEFAULT_ENVIRONMENT = "local";
  private static final Properties PROPERTIES = loadProperties();

  private ConfigManager() {
  }

  private static Properties loadProperties() {
    String environment = System.getProperty(
        "environment",
        DEFAULT_ENVIRONMENT
    );

    String resourcePath = "config/" + environment + ".properties";
    Properties properties = new Properties();

    try (InputStream inputStream =
        ConfigManager.class
            .getClassLoader()
            .getResourceAsStream(resourcePath)) {

      if (inputStream == null) {
        throw new IllegalStateException(
            "Configuration file was not found: " + resourcePath
        );
      }

      properties.load(inputStream);
      return properties;

    } catch (IOException exception) {
      throw new IllegalStateException(
          "Unable to load configuration file: " + resourcePath,
          exception
      );
    }
  }

  public static String get(String key) {
    String systemValue = System.getProperty(key);

    if (systemValue != null && !systemValue.isBlank()) {
      return systemValue.trim();
    }

    String propertyValue = PROPERTIES.getProperty(key);

    if (propertyValue == null || propertyValue.isBlank()) {
      throw new IllegalStateException(
          "Configuration property is missing: " + key
      );
    }

    return propertyValue.trim();
  }

  public static String get(String key, String defaultValue) {
    String systemValue = System.getProperty(key);

    if (systemValue != null && !systemValue.isBlank()) {
      return systemValue.trim();
    }

    return PROPERTIES.getProperty(key, defaultValue).trim();
  }

  public static int getInt(String key) {
    String value = get(key);

    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException exception) {
      throw new IllegalStateException(
          "Configuration property must be an integer: "
              + key + "=" + value,
          exception
      );
    }
  }

  public static boolean getBoolean(String key) {
    return Boolean.parseBoolean(get(key));
  }

  public static String getEnvironment() {
    return System.getProperty(
        "environment",
        DEFAULT_ENVIRONMENT
    );
  }
}