package steam.forms.pages;

import framework.BasePage;
import framework.CommonFunction;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class SpecialsPage extends BasePage {

    private Label lblDiscounts = new Label(By.xpath("//div[contains(@class,'search_discount')]//span[contains(text(),'%')]"));

    private static String gameNameLocator = "/../../preceding-sibling::div[contains(@class, 'col search_name ellipsis')]";
    private static String priceLocator = "/../following-sibling::div[contains(@class, 'search_price')]";
    private static String selectedDiscountPath;

    private static String regexTemplate = "-?(\\d+[.,]?\\d+)%?";

    private List<String> listOfDiscounts;
    private Button btnSelectedDiscount;
    private String selectedGameName;
    private String maxDiscount;
    private String selectedGamePrice;
    private double firstPrice;
    private double finalPrice;
    private int discount;


    public SpecialsPage() {
        super(By.xpath(String.format("//div[@class='page_content']//h2[contains(text(),'%s')]", languageProperties.getProperty("specials"))), "Specials Page");
    }


    public void selectGameMaxDiscount() {

        listOfDiscounts = lblDiscounts.getListOfContent();
        Collections.sort(listOfDiscounts);
        Collections.reverse(listOfDiscounts);

        maxDiscount = listOfDiscounts.get(0);

        selectedDiscountPath = String.format("//div[contains(@class,'search_discount')]//span[contains(text(),'%s')]", maxDiscount);
        btnSelectedDiscount = new Button(By.xpath(selectedDiscountPath));

        selectedGameName = new Label(By.xpath(String.format("%s%s", selectedDiscountPath, gameNameLocator))).getText();

        selectedGamePrice = new Label(By.xpath(String.format("%s%s", selectedDiscountPath, priceLocator))).getText();
        btnSelectedDiscount.click();
    }


    public boolean checkDiscount() {
        String[] prices = selectedGamePrice.split("\n");
        firstPrice = Double.parseDouble(CommonFunction.regexGetMatch(prices[0], regexTemplate));
        finalPrice = Double.parseDouble(CommonFunction.regexGetMatch(prices[1],regexTemplate));
        discount = Integer.parseInt(CommonFunction.regexGetMatch(maxDiscount, regexTemplate));
        return (100 - Math.round(finalPrice * 100 / firstPrice)) == discount;
    }

    public double getOriginalPrice() {
        return firstPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public String getGameName() {
        return selectedGameName;
    }
}
