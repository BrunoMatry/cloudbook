package hmi.content.register;

import hmi.Launcher;
import hmi.content.AbstractActivity;
import hmi.content.Activity;
import hmi.home.MainView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import model.node.MyNode;
import model.node.NodeBuilder;

/**
 * View letting the user register an application
 */
public class RegisterView extends Activity {
    
    //vertical box containing all the identificator components
    protected SettingsArea settings;
    
    //ok button
    protected Button ok;
    
    //constructing object
    protected NodeBuilder builder;

    /**
     * Getter
     * @return builder field
     */
    public NodeBuilder getBuilder() {
        return builder;
    }

    /**
     * Setter
     * @param builder builder field
     */
    public void setBuilder(NodeBuilder builder) {
        this.builder = builder;
    }
    
    /**
     * initialize the vertical box
     * @param p previous Activity
     */
    public RegisterView(AbstractActivity p) {
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
                            MyNode node = builder.build();
                            node.save();
                            Dialogs.showInformationDialog(Launcher.STAGE, "Success build", "OK");
                            MainView.INSTANCE.launch();
                        } catch (IOException | ClassNotFoundException ex) {
                            ex.printStackTrace();
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
    
    public StringProperty nodePortProperty() {
        return settings.getFieldArea().getNodePort().getHint().textProperty();
    }
    
    public StringProperty hostProperty() {
        return settings.getFieldArea().getHost().getHint().textProperty();
    }
    
    public StringProperty serverPortProperty() {
        return settings.getFieldArea().getServerPort().getHint().textProperty();
    }
    
    public ObjectProperty<Cloud> cloudProperty() {
        return settings.clouds.valueProperty();
    }
    
 }
