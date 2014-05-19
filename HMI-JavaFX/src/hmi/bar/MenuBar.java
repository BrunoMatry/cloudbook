package hmi.bar;

import hmi.button.ExitButton;
import javafx.scene.Parent;

public class MenuBar extends Parent {
    private ExitButton exitButton;
    
    public MenuBar() {
        this.exitButton = new ExitButton();
        this.getChildren().add(exitButton);
    }
}
