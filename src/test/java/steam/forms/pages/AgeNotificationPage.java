package steam.forms.pages;

import framework.BasePage;
import framework.elements.Button;
import org.openqa.selenium.By;

public class AgeNotificationPage extends BasePage {
    private static String btnViewPageLocator = "//div[@id='app_agegate']//span[contains(text(), '%s')]";
    private Button btnViewPage;

    public AgeNotificationPage() {
        super(By.xpath("//div[@class='agegate_text_container']"), "Age Notification Page");
    }

    public GamePage viewPage() {
        btnViewPage = new Button(By.xpath(String.format(btnViewPageLocator, languageProperties.getProperty("viewPage"))));
        btnViewPage.click();
        return new GamePage();
    }
}
