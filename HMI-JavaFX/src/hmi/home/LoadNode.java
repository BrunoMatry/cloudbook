package hmi.home;

import hmi.content.node.NodeView;
import model.node.FileEngineRelation;

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
        FileEngineRelation.INSTANCE.select(node);
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
