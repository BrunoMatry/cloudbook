package hmi.content;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import model.network.implementation.Network;
import model.node.ApplicationList;
import model.node.CloudBookNode;

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
    /*protected ImageView logo;
    public ObjectProperty<Image> logoProperty() {
        return logo.imageProperty();
    }*/
    
    //state of the connection
    protected ImageView connectionState;
    public ObjectProperty<Image> connectionStateProperty() {
        return connectionState.imageProperty();
    }
    
    /**
     * Constructor
     * @param p parent activity
     */
    public OneNodeActivity(AActivity p) {
        super(p);
        name = new Text();
        //logo = new ImageView();
        connectionState = new ImageView();
        HBox box = new HBox();
        box.getChildren().addAll(name, /*logo,*/ connectionState);
        menuPane.setRight(box);
    }
    
    /**
     * Bind the view with the current node
     */
    public void bindWithNode() {
        CloudBookNode node = ApplicationList.INSTANCE.getCurrentNode();
        name.textProperty().bind(node.nameProperty());
        //logo.imageProperty().bind(node.logoProperty());
        Network network = (Network)node.getEngine().getNetwork();
        connectionState.imageProperty().bind(network.getConnectionState().ledProperty());
    }
    
}
