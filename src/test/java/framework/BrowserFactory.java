package framework;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BrowserFactory extends BaseEntity {
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static String driverPath="src/test/resources/drivers/";
    private static String win = "win";
    private static String path;
    private static WebDriver driver;
    private static String downloadPath;

    static {
        try {
            downloadPath = new File(configProperties.getProperty("tempFolder")).getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BrowserFactory() {
    }

    public static WebDriver initDriver() {

        if (driver == null) {
            switch (configProperties.getProperty("browser")) {
                case CHROME:
                    setProperty("webdriver.chrome.driver", "chromedriver");
                    driver = new ChromeDriver(setChromeCapabilities());

                    return driver;
                case FIREFOX:
                    setProperty("webdriver.gecko.driver", "geckodriver");
                    driver = new FirefoxDriver(setFirefoxCapabilities());
                    return driver;
                default:
                    throw new IllegalArgumentException("Browser not supported");
            }
        }
        return driver;
    }


    private static void setProperty(String driverType, String driverName) {
        path = String.format("%s%s", driverPath, driverName);

        if (System.getProperty("os.name").toLowerCase().contains(win))
            path = String.format("%s%s", path, ".exe");
        System.setProperty(driverType, path);
    }


    private static FirefoxOptions setFirefoxCapabilities() {
        FirefoxProfile profile = new FirefoxProfile();

        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.dir", downloadPath);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "application/octet-stream, application/zip ,application/x-rar-compressed, application/x-gzip, application/msword ");

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        return options;

    }

    private static ChromeOptions setChromeCapabilities() {
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.default_directory", downloadPath);
        prefs.put("safebrowsing.enabled", "true");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        return options;
    }
}









