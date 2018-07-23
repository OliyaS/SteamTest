package steam.forms.pages;

import framework.BasePage;
import framework.CommonFunction;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;

public class GamePage extends BasePage {

    private Button btnViewPage = new Button(By.xpath(String.format("//div[@id='app_agegate']//span[contains(text(), '%s')]", languageProperties.getProperty("viewPage"))));
    private Label lblAgeCheckForm = new Label(By.xpath("//form[@id='agecheck_form']"));

    private Label lblDiscount = new Label(By.xpath("//div[@class='discount_pct']"));
    private Label lblOriginalPrice = new Label(By.xpath("//div[@class='discount_original_price']"));
    private Label lblFinalPrice = new Label(By.xpath("//div[@class='discount_final_price']"));
    private Label lblGameName = new Label(By.xpath("//div[contains(@class,'AppName')]"));

    private Button btnInstallSteam = new Button(By.xpath("//div[contains(@class,'installsteam')]"));

    private static String regexTemplate = "-?(\\d+[.,]?\\d+)%?";

    public GamePage() {
        super(By.xpath("//div[@class='content']"),"Game Page");
        if (btnViewPage.assertIsVisible())
          new AgeNotificationPage().viewPage();
        else if (lblAgeCheckForm.assertIsVisible())
           new AgeInputPage().fillAgeForm();
       }

    public double getOriginalPrice() {
        return Double.parseDouble(CommonFunction.regexGetMatch(lblOriginalPrice.getText(), regexTemplate));
    }

    public double getFinalPrice() {
        return Double.parseDouble(CommonFunction.regexGetMatch(lblFinalPrice.getText(), regexTemplate));
    }

    public int getDiscount() {
        return Integer.parseInt(CommonFunction.regexGetMatch(lblDiscount.getText(), regexTemplate));
    }

    public String getTitle() {
        return lblGameName.getText();
    }

    public void download() {
        btnInstallSteam.click();
    }
}
