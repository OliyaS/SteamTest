package framework.elements;

import framework.BaseEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BaseElement extends BaseEntity {

    private By locator;
    private WebElement element;
    private List<WebElement> list;
    private List<String> listOfContent = new ArrayList<>();


    public BaseElement(By locator) {
        this.locator = locator;
    }

    public By getLocator() {
        return this.locator;
    }

    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public List<String> getListOfContent() {
        for (WebElement element : getElements(this.getLocator())) {
            listOfContent.add(element.getText());
        }
        return listOfContent;
    }


    public String getText() {
        element = getElement(this.getLocator());
        if (element.isDisplayed())
            return element.getText();
        else return "could not return text";
    }

    public String getLink() {
        element = getElement(this.getLocator());
        if (element.isDisplayed())
            return element.getAttribute("href");
        else return "could not return link";
    }

    public void click() {
        element = getElement(this.getLocator());
        if (element.isEnabled())
            element.click();
    }


    public void clickAndWait() {
        element = getElement(this.getLocator());
        if (element.isEnabled())
            element.click();
        WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(configProperties.getProperty("timeOutMax")));
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    public void moveToElement() {
        if (assertIsVisible()) {
            Actions actions = new Actions(driver);
            actions.moveToElement(getElement(this.getLocator())).build().perform();
        }
    }

    public boolean assertIsVisible() {
        element = getElement(this.getLocator());
        return element.isDisplayed();
    }

}
