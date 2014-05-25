package hmi.content;

import controller.StringImageRelation;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import model.engine.Engine;
import model.network.implementation.Network;
import model.node.FileEngineRelation;
import model.node.MyNode;

/**
 *
 * activity in which are displayed the node logo, name and connection state
 */
public class OneNodeActivity extends Activity {

    //name of the current node
    protected Text name;
    public StringProperty nameProperty() {
        return name.textProperty();
    }
    
    //logo of the current node
    protected ImageView logo;
    public ObjectProperty<Image> logoProperty() {
        return logo.imageProperty();
    }
    
    //state of the connection
    protected ImageView connectionState;
    public ObjectProperty<Image> connectionStateProperty() {
        return connectionState.imageProperty();
    }
    
    /**
     * Constructor
     * @param p parent activity
     */
    public OneNodeActivity(AbstractActivity p) {
        super(p);
        name = new Text();
        logo = new ImageView();
        connectionState = new ImageView();
        HBox box = new HBox();
        box.getChildren().addAll(name, logo, connectionState);
        menuPane.setRight(box);
    }
    
    /**
     * Bind the view with the current node
     */
    public void bindWithNode() {
        Engine engine = FileEngineRelation.INSTANCE.getCurrentEngine();
        MyNode node = engine.getNode();
        name.textProperty().bind(node.nameProperty());
        Network network = (Network) engine.getNetwork();
        connectionState.imageProperty().bind(network.getConnectionState().ledProperty());
        StringImageRelation logoBinder = new StringImageRelation();
        logoBinder.bind(node.logoProperty());
        logoBinder.drive(logo.imageProperty());
    }
    
}
