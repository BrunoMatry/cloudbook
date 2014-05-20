package hmi.home;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.engine.Engine;
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
        try {
            CloudBookNode cbn = CloudBookNode.load(node + ".ser");
            ApplicationList.INSTANCE.select(cbn);
            Engine engine = cbn.getEngine();
            engine.start();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(LoadNode.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            HomeView.INSTANCE.launch();
        }
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
