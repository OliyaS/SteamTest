package steam.menu;

import framework.BaseEntity;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;


public class LanguageMenu extends BaseEntity {
    private enum Language {
        RU("russian"),
        EN("english");

        private String language;

        Language(String lang) {
            this.language = lang;
        }
    }

    private Button btnLanguage = new Button(By.xpath("//span[@id='language_pulldown']"));
    private String languageLocator = "//div[@id='language_dropdown']//a[contains(@href, '%s')]";
    private Label lblLanguage;
    private static String propertyLanguage;
    private static String pageLanguage;


    public LanguageMenu() {
    }

    public void chooseLanguage() {
        propertyLanguage = languageProperties.getProperty("language");
        pageLanguage = btnLanguage.getText();

        if (!propertyLanguage.equals(pageLanguage)) {
            btnLanguage.click();
            lblLanguage = new Label(By.xpath((String.format(languageLocator, Language.valueOf(configProperties.getProperty("language").toUpperCase()).language))));
            lblLanguage.clickAndWait();
        }
    }
}
