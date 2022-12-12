package common;

import org.openqa.selenium.remote.RemoteWebElement;

public class UIElement extends RemoteWebElement {
    public String path = "";

    public UIElement(String xpath) {
        this.path = xpath;
    }
}
