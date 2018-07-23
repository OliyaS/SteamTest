package steam.forms.pages;

import framework.BasePage;
import framework.elements.Button;
import org.openqa.selenium.By;

public class ActionPage extends BasePage {

    private String btnSpecialsLocator = "//div[contains(@class,'specials_see_more')]//span[contains(text(), '%s')]";
    private Button btnSpecials;

    public ActionPage() {
        super(By.xpath(String.format("//h2[@class='pageheader'][contains(text(), '%s')]", languageProperties.getProperty("browsingAction"))), "Action Page");
    }

    public void getSpecials() {
        btnSpecials = new Button(By.xpath(String.format(btnSpecialsLocator, languageProperties.getProperty("moreSpecials"))));
        btnSpecials.click();
    }

}
