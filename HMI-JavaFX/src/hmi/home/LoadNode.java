package hmi.home;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.engine.Engine;
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
    }

    @Override
    public void execute() {
        try {
            CloudBookNode cbn = CloudBookNode.load(node + ".ser");
            Engine.INSTANCE.initialize(cbn, ""+cbn.getNodePort());
            Engine.INSTANCE.start();
            HomeView.INSTANCE.launch();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(LoadNode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
