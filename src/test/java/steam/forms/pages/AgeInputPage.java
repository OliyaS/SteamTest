package steam.forms.pages;

import framework.BasePage;
import framework.elements.Button;
import framework.elements.Combobox;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class AgeInputPage extends BasePage {
    private Combobox cmbAgeYear = new Combobox(By.xpath("//select[@id='ageYear']//option[@value='2000']"));
    private static String btnEnterLocator = "//span[contains(text(),'%s')]";
    private static String age = "2000";
    private Button btnEnter;


    public AgeInputPage() {
        super(By.xpath("//form[@id='agecheck_form']"), "Age Input Page");
    }

    public GamePage fillAgeForm() {
        cmbAgeYear.selectByText(age);

        btnEnter = new Button(By.xpath(String.format(btnEnterLocator, languageProperties.getProperty("enter"))));
        btnEnter.click();
        return new GamePage();
    }

}
