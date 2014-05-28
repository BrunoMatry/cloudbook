package model.node;

import java.io.File;
import java.io.IOException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Builder
 */
public class NodeBuilder {
    
    protected ObjectProperty<File> logo;
    public ObjectProperty<File> logoProperty() {
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
    
    private final StringProperty appType;
    public StringProperty appTypeProperty() {
        return appType;
    }
    
    private final StringProperty performance;
    public StringProperty performanceProperty() {
        return performance;
    }
    
    private final StringProperty speed;
    public StringProperty speedProperty() {
        return speed;
    }
    
    /**
     * Instanciate the node builder
     */
    public NodeBuilder() {
        logo = new SimpleObjectProperty<>();
        name = new SimpleStringProperty();
        platform = new SimpleObjectProperty<>();
        nodePort = new SimpleStringProperty();
        appType = new SimpleStringProperty();
        performance = new SimpleStringProperty();
        speed = new SimpleStringProperty();
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
                Integer.parseInt(appType.get()),
                Integer.parseInt(performance.get()),
                Integer.parseInt(speed.get())
        );
        return cbn;
    }   
}