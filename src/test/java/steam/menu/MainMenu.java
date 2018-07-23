package steam.menu;

import org.openqa.selenium.By;
import framework.elements.Label;
import framework.BaseEntity;


public class MainMenu extends BaseEntity {
    public enum Menu {
        GAMES("Games"),
        SOFTWARE("Software"),
        HARDWARE("Hardware");

        private String menuCategory;

        Menu(String category) {
            this.menuCategory = category;
        }
    }

    public enum Games {
        ACTION("Action"),
        INDIE("Indie"),
        STRATEGY("Strategy");

        private String genre;

        Games(String genre) {
            this.genre = genre;
        }

    }

    private static String menuItemLocator = "//a[@class='pulldown_desktop'][contains(text(),'%s')]";
    private static String gameLocator = "//div[@id='genre_flyout']//a[contains(text(),'%s')]";
    private Label lblMenu;
    private Label lblGame;

    public void navigateMenu(Menu category, Games games) {
        lblMenu = new Label(By.xpath(String.format(menuItemLocator, languageProperties.getProperty(category.menuCategory))));
        lblGame = new Label(By.xpath(String.format(gameLocator, languageProperties.getProperty(games.genre))));
        lblMenu.moveToElement();
        lblGame.click();
    }

}





