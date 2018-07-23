package framework;


import framework.elements.BaseElement;
import org.openqa.selenium.By;
import org.testng.Assert;

public class BasePage extends BaseEntity {
    private By pageLocator;
    private String pageTitle;
    private BaseElement element;

    public BasePage(final By locator, final String pageTitle) {
        init(locator, pageTitle);
        Assert.assertTrue(isOpen());
    }

    private void init(final By locator, final String pageTitle) {
        this.pageLocator = locator;
        this.pageTitle = pageTitle;
    }

    private boolean isOpen() {
        element=new BaseElement(pageLocator);
        return element.assertIsVisible();
    }
}
