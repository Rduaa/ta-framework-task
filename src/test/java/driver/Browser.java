package driver;

public enum Browser {
  CHROME,
  FIREFOX;

  public static Browser from(String value) {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException("Browser value must not be empty");
    }

    try {
      return Browser.valueOf(value.trim().toUpperCase());
    } catch (IllegalArgumentException exception) {
      throw new IllegalArgumentException(
          "Unsupported browser: " + value
              + ". Supported browsers: CHROME, FIREFOX",
          exception
      );
    }
  }
}