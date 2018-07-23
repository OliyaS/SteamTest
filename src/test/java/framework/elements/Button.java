package framework.elements;

import org.openqa.selenium.By;

public class Button extends BaseElement {

    private String type;

    public Button(By locator) {
        super(locator);
        type="button";
    }

    public String getType(){
        return this.type;
    }
}
