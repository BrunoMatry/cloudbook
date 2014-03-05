/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.register;

import hmi.button.IconFlyWeight;
import hmi.content.AActivity;
import hmi.content.Activity;
import hmi.home.HomeView;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * View letting the user register an application
 * singleton
 * @author Gwendal
 */
public final class RegisterView extends Activity<BorderPane> {
    
    public static final RegisterView INSTANCE = new RegisterView(HomeView.INSTANCE);
    
    private VBox vBox;
    private ImageView logo;
    private TextField name;
    private ChoiceBox clouds;
    private TableView mesures;
    
    private RegisterView(AActivity p) {
        super(p, new BorderPane());
        title = "Register your application";
        this.pane.getChildren().remove(goBack);
        pane.setTop(goBack);
        initComponents();
    }
    
    private void initComponents() {
        logo = new ImageView(IconFlyWeight.INSTANCE.getDefaultLogo());
        ImageView l2 = new ImageView(IconFlyWeight.INSTANCE.getDefaultLogo());
        ImageView l3 = new ImageView(IconFlyWeight.INSTANCE.getDefaultLogo());
        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(logo);
        pane.setCenter(vBox);
        pane.setLeft(l2);
        pane.setRight(l3);
    }
}
