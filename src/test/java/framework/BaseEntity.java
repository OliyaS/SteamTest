package framework;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseEntity {
    protected static WebDriver driver;
    protected static PropertyReader configProperties;
    protected static PropertyReader languageProperties;

    @BeforeClass
    public void setup() {
        initProperties();
        driver = BrowserFactory.initDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(configProperties.getProperty("implicityTime")), TimeUnit.SECONDS);

        driver.get(configProperties.getProperty("url"));
        deleteTempFiles();

    }

    public static void initProperties() {
        configProperties = new PropertyReader("config");
        languageProperties = new PropertyReader(configProperties.getProperty("language"));
    }

    public static void deleteTempFiles() {
        String dirPath = null;
        try {
            dirPath = new File(configProperties.getProperty("tempFolder")).getCanonicalPath();
        } catch (IOException e) {
            System.out.println("Could not get canonical path");
        }

        File dir = new File(dirPath);
        for (File f : dir.listFiles()) {
            f.delete();
        }
    }

    @AfterClass
    protected void tearDown() {
      if (driver != null)
          driver.quit();
    }

}
