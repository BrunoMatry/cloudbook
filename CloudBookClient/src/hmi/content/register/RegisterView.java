package hmi.content.register;

import hmi.Launcher;
import hmi.content.AbstractActivity;
import hmi.content.Activity;
import hmi.home.MainView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
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
    
    //File containing the logo of the application
    private final ObjectProperty<File> imageFile;

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
        imageFile = new SimpleObjectProperty<>();
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
                            File logo = settings.getIconArea().imageFileProperty().get();
                            File target = new File("./res/" + logo.getName());
                            Files.copy(logo.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            imageFile.set(target);
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
    
    /**
     * File of the application icon.
     * @return File of the application icon.
     */
    public ObjectProperty<File> imageFileProperty() {
        return imageFile;
    }
    
    /**
     * Name of the application.
     * @return Name of the application.
     */
    public StringProperty nameProperty() {
        return settings.getFieldArea().getName().getHint().textProperty();
    }
    
    /**
     * Port used by this application to communicate on the network.
     * @return Port used by this application to communicate on the network.
     */
    public StringProperty nodePortProperty() {
        return settings.getFieldArea().getNodePort().getHint().textProperty();
    }
    
    /**
     * Cloud on which the application runs.
     * @return Cloud on which the application runs.
     */
    public ObjectProperty<Cloud> cloudProperty() {
        return settings.clouds.valueProperty();
    }
    
    /**
     * Application type.
     * @return Application type.
     */
    public StringProperty appTypeProperty() {
        return settings.getFieldArea().getAppType().getHint().textProperty();
    }
    
    /**
     * Application performance need.
     * @return Application performance need.
     */
    public StringProperty PerformanceProperty() {
        return settings.getFieldArea().getPerformance().getHint().textProperty();
    }
    
    /**
     * Application speed need.
     * @return Application speed need.
     */
    public StringProperty SpeedProperty() {
        return settings.getFieldArea().getSpeed().getHint().textProperty();
    }
    
 }
