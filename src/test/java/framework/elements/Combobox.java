package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;


public class Combobox extends BaseElement {

    private String type;
    private Select selection;

    public Combobox(By locator) {
        super(locator);
        type = "combobox";
    }

    public String getType() {
        return this.type;
    }

    public void selectByText(String text) {
        selection = new Select(getElement((this.getLocator())));
        selection.selectByVisibleText(text);
    }
}
