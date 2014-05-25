package model.node;

import java.io.IOException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

/**
 * Builder
 */
public class NodeBuilder {
    
    protected SimpleObjectProperty<Image> logo;
    public SimpleObjectProperty<Image> logoProperty() {
        return logo;
    }
    
    protected SimpleStringProperty name;
    public SimpleStringProperty nameProperty() {
        return name;
    }
    
    protected StringProperty nodePort;
    public StringProperty nodePortProperty() {
        return nodePort;
    }
    
    protected SimpleObjectProperty<Cloud> platform;
    public SimpleObjectProperty<Cloud> platformProperty() {
        return platform;
    }
    
    public NodeBuilder() {
        logo = new SimpleObjectProperty<>();
        name = new SimpleStringProperty();
        platform = new SimpleObjectProperty<>();
        nodePort = new SimpleStringProperty();
    }
    
    /**
     * Build a node according to the parameters
     * @return the built node
     * @throws IOException the state can't be saved
     */
    public MyNode build() throws IOException {
        MyNode cbn = new MyNode(logo.get(),
                name.get(),
                platform.get(),
                Integer.parseInt(nodePort.get()),
                0, 0, 0);
        return cbn;
    }
    
}
