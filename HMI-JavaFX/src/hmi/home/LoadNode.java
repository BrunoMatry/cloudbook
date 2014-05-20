package hmi.home;

import hmi.content.node.NodeView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.node.ApplicationList;
import model.node.CloudBookNode;

/**
 * command
 * command dedecated to the loading of a node
 */
public class LoadNode extends RegistryButton {

    /**
     * Constructor
     */
    public LoadNode() {
        super();
    }
    
    /**
     * Constructor
     * @param node save file to load 
     */
    public LoadNode(String node) {
        super(node);
        setText(node);
    }

    @Override
    public void execute() {
        ApplicationList.INSTANCE.select(node);
        NodeView.INSTANCE.bindWithNode();
        NodeView.INSTANCE.launch();
    }

    /**
     * Setter
     * the label of the button is set to the parameter value
     * @param node node attribute
     */
    @Override
    public void setNode(String node) {
        super.setNode(node);
        setText(node);
    }
    
    
    
}
