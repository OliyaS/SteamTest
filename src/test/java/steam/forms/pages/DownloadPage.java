package steam.forms.pages;

import framework.BasePage;
import framework.elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.NoSuchElementException;

import static java.util.concurrent.TimeUnit.SECONDS;

public class DownloadPage extends BasePage {
    private Button btnInstall = new Button(By.xpath("//div[@id='about_greeting_ctn']//a[contains(@id,'install_steam_link')]"));
    private static String tempFolder = configProperties.getProperty("tempFolder");
    private static String winFileName="SteamSetup.exe";
    private static String linuxFileName="steam_latest.deb";
    private static File tempDir;
    private static String installFile;
    private URL url;
    private URLConnection urlCon;
    private File fileToCheck;

    static {
        try {
            tempDir = new File(tempFolder).getCanonicalFile();
        } catch (IOException e) {
            System.out.println("Could not get canonical path");
        }
    }

    public DownloadPage() {
        super(By.xpath("//div[contains(@id,'about_greeting')]"), "Download Page");
    }

    public void installGame() {
        btnInstall.click();

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).
                withTimeout(Integer.valueOf(configProperties.getProperty("timeOutMax")), SECONDS).
                pollingEvery(2, SECONDS).
                ignoring(NoSuchElementException.class);

        String filePath=configProperties.getProperty("tempFolder");
        if (System.getProperty("os.name").toLowerCase().contains("win"))
            installFile=winFileName;
        else installFile=linuxFileName;

        fileToCheck = new File(String.format("%s%s",filePath, installFile));//tempDir.listFiles()[0];
        wait.until((WebDriver wd) -> fileToCheck.exists());
    }


    public long getDownloadedFileLenght() {
        return fileToCheck.length();
    }

    public long getFileLength() {
        try {
            url = new URL(btnInstall.getLink());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            urlCon = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long fileLengh = urlCon.getContentLengthLong();
        return fileLengh;
    }

}



