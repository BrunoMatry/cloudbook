package model.node;

import java.io.IOException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import model.engine.Engine;

/**
 * builder
 * @author Gwendal
 */
public class CloudBuilder {
    
    protected SimpleObjectProperty<Image> logo;
    public SimpleObjectProperty<Image> logoProperty() {
        return logo;
    }
    
    protected SimpleStringProperty name;
    public SimpleStringProperty nameProperty() {
        return name;
    }
    
    protected SimpleObjectProperty<Cloud> platform;
    public SimpleObjectProperty<Cloud> platformProperty() {
        return platform;
    }
    
    protected StringProperty host;
    public StringProperty hostProperty() {
        return host;
    }
    
    protected IntegerProperty port;
    public IntegerProperty portProperty() {
        return port;
    }
    
    public CloudBuilder() {
        logo = new SimpleObjectProperty<>();
        name = new SimpleStringProperty();
        platform = new SimpleObjectProperty<>();
        host = new SimpleStringProperty();
        port = new SimpleIntegerProperty();
    }
    
    /**
     * set the current cloudbooknode as the registered one
     * @throws IOException the state can't be saved
     */
    public void build() throws IOException {
        Engine.INSTANCE.setNode(new CloudBookNode(logo.get(), name.get(), platform.get(), 0, 0, 0));
        Engine.INSTANCE.save();
    }
    
}
