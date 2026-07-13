package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtils {

  private static final Logger LOGGER =
      LogManager.getLogger(ScreenshotUtils.class);

  private static final Path SCREENSHOTS_DIRECTORY =
      Path.of("target", "screenshots");

  private static final DateTimeFormatter DATE_FORMATTER =
      DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

  private ScreenshotUtils() {
  }

  public static void takeScreenshot(
      WebDriver driver,
      String testName
  ) {
    try {
      Files.createDirectories(SCREENSHOTS_DIRECTORY);

      String safeTestName = testName.replaceAll(
          "[^a-zA-Z0-9._-]",
          "_"
      );

      String fileName = safeTestName
          + "_"
          + LocalDateTime.now().format(DATE_FORMATTER)
          + ".png";

      Path screenshotPath =
          SCREENSHOTS_DIRECTORY.resolve(fileName);

      byte[] screenshot = ((TakesScreenshot) driver)
          .getScreenshotAs(OutputType.BYTES);

      Files.write(screenshotPath, screenshot);

      LOGGER.info(
          "Screenshot saved: {}",
          screenshotPath.toAbsolutePath()
      );

    } catch (IOException | RuntimeException exception) {
      LOGGER.error(
          "Unable to save screenshot for test: {}",
          testName,
          exception
      );
    }
  }
}