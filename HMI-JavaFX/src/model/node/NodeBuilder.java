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
    
    protected StringProperty host;
    public StringProperty hostProperty() {
        return host;
    }
    
    protected StringProperty serverPort;
    public StringProperty serverPortProperty() {
        return serverPort;
    }
    
    public NodeBuilder() {
        logo = new SimpleObjectProperty<>();
        name = new SimpleStringProperty();
        platform = new SimpleObjectProperty<>();
        host = new SimpleStringProperty();
        serverPort = new SimpleStringProperty();
        nodePort = new SimpleStringProperty();
    }
    
    /**
     * Build a node according to the parameters
     * @return the built node
     * @throws IOException the state can't be saved
     */
    public Node build() throws IOException {
        Node cbn = new Node(logo.get(),
                name.get(),
                platform.get(),
                host.get(),
                Integer.parseInt(serverPort.get()),
                Integer.parseInt(nodePort.get()),
                0, 0, 0);
        return cbn;
    }
    
}
