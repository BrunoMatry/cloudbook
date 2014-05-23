package model.node;

import java.util.ArrayList;
import model.engine.Engine;

/**
 * Singleton
 * Contains all the engine which run in this JVM
 */
public final class ApplicationList extends ArrayList<Engine> {
    
    public static final ApplicationList INSTANCE = new ApplicationList();
    
    //Current node selected by the user
    private Engine currentEngine;
    
    /**
     * Constructor
     */
    private ApplicationList() {
        super();
    }
    
    /**
     * Selects the specified node as the current one
     * @param name name of the node to select
     * @return selected node
     */
    public Engine select(String name) {
        for(Engine engine : this) {
            if(engine.getNode().nameProperty().get().equals(name)) {
                currentEngine = engine;
                break;
            }
        }
        return currentEngine;
    }

    /**
     * Getter
     * @return get the current node
     */
    public Engine getCurrentEngine() {
        return currentEngine;
    }

    public boolean containsNode(MyNode node) {
        for(Engine engine : this)
            if(node.nameProperty().get().equals(engine.getNode().nameProperty().get()))
                return true;
        return false;
    }
}