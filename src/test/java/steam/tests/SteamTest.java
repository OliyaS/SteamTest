package steam.tests;

import framework.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import steam.forms.pages.*;
import steam.menu.MainMenu;

public class SteamTest extends BaseTest {


    @Test
    public void testStreamPowered() {

        MainPage mainPage = new MainPage();
        mainPage.languageMenu.chooseLanguage();
        mainPage.gamesMenu.navigateMenu(MainMenu.Menu.GAMES, MainMenu.Games.ACTION);

        ActionPage actionPage = new ActionPage();
        actionPage.getSpecials();

        SpecialsPage specialsPage = new SpecialsPage();
        specialsPage.selectGameMaxDiscount();

        Assert.assertTrue(specialsPage.checkDiscount());

        GamePage gamePage = new GamePage();

        Assert.assertEquals(gamePage.getTitle(), specialsPage.getGameName());
        Assert.assertEquals(gamePage.getOriginalPrice(), specialsPage.getOriginalPrice());
        Assert.assertEquals(gamePage.getFinalPrice(), specialsPage.getFinalPrice());
        Assert.assertEquals(gamePage.getDiscount(), specialsPage.getDiscount());

        gamePage.download();

        DownloadPage downloadPage = new DownloadPage();
        downloadPage.installGame();

        Assert.assertEquals(downloadPage.getDownloadedFileLenght(),downloadPage.getFileLength());
    }

}
