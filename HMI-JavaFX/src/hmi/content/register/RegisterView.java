/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.register;

import hmi.Launcher;
import hmi.content.AActivity;
import hmi.content.Activity;
import java.io.IOException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.image.Image;
import model.node.Cloud;
import model.node.CloudBuilder;

/**
 * View letting the user register an application
 * @author Gwendal
 */
public class RegisterView extends Activity {
    
    //vertical box containing all the identificator components
    protected SettingsArea settings;
    
    //ok button
    protected Button ok;
    
    //constructing object
    protected CloudBuilder builder;

    /**
     * Getter
     * @return builder field
     */
    public CloudBuilder getBuilder() {
        return builder;
    }

    /**
     * Setter
     * @param builder builder field
     */
    public void setBuilder(CloudBuilder builder) {
        this.builder = builder;
    }
    
    /**
     * initialize the vertical box
     * @param p previous Activity
     */
    public RegisterView(AActivity p) {
        super(p);
        title = "Register your application";
        settings = new SettingsArea();
        setBottom(getOk());
        setCenter(settings);
    }
    
    /**
     * Getter
     * @return ok field
     */
    public final Button getOk() {
            if(ok == null) {
                ok = new Button("Ok");
                setAlignment(ok, Pos.CENTER);
                setMargin(ok, new Insets(0, 0, 20, 0));
                ok.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {
                        try {
                            builder.build();
                            Dialogs.showInformationDialog(Launcher.STAGE, "Success build", "OK");
                        } catch (IOException ex) {
                            Dialogs.showErrorDialog(Launcher.STAGE, "Error while building", "Error");
                        }
                    }
                    
                });
            }
            return ok;
        }

    /**
     * Getter
     * @return settings field
     */
    public SettingsArea getSettings() {
        return settings;
    }
    
    public ObjectProperty<Image> logoProperty() {
        return settings.getIconArea().getLogo().imageProperty();
    }
    
    public StringProperty nameProperty() {
        return settings.getFieldArea().getName().getHint().textProperty();
    }
    
    public StringProperty hostProperty() {
        return settings.getFieldArea().getHost().getHint().textProperty();
    }
    
    public StringProperty portProperty() {
        return settings.getFieldArea().getPort().getHint().textProperty();
    }
    
    public ObjectProperty<Cloud> cloudProperty() {
        return settings.clouds.valueProperty();
    }
    
 }
