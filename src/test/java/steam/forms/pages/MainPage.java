package steam.forms.pages;

import org.openqa.selenium.By;
import framework.BasePage;

import steam.menu.LanguageMenu;
import steam.menu.MainMenu;


public class MainPage extends BasePage {

    public  LanguageMenu languageMenu = new LanguageMenu();
    public  MainMenu gamesMenu = new MainMenu();

    public MainPage() {
        super(By.xpath("//div[@id='content_login']"), "Main Page");
    }

}



