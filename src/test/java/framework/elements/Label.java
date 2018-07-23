package framework.elements;

import org.openqa.selenium.By;

public class Label extends BaseElement {

    private String type;

    public Label(By locator) {
        super(locator);
        type = "label";
    }

    public String getType() {
        return this.type;
    }

}
