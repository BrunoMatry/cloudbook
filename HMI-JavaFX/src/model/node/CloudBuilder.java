package model.node;

import java.io.IOException;
import java.net.InetAddress;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import model.engine.Engine;
import model.network.implementation.Network;

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
    
    public CloudBuilder() {
        logo = new SimpleObjectProperty<>();
        name = new SimpleStringProperty();
        platform = new SimpleObjectProperty<>();
        host = new SimpleStringProperty();
        serverPort = new SimpleStringProperty();
        nodePort = new SimpleStringProperty();
    }
    
    /**
     * sets the current cloudbooknode as the registered one
     * @throws IOException the state can't be saved
     */
    public void build() throws IOException {
        CloudBookNode cbn = new CloudBookNode(logo.get(),
                name.get(),
                platform.get(),
                host.get(),
                Integer.parseInt(serverPort.get()),
                Integer.parseInt(nodePort.get()),
                0, 0, 0);
        Engine.INSTANCE.initialize(cbn, nodePort.get());
    }
    
}
