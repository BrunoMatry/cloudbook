package model.node;

import java.util.ArrayList;

/**
 * Singleton
 * Contains all the engine which run in this JVM
 */
public final class ApplicationList extends ArrayList<Node> {
    
    public static final ApplicationList INSTANCE = new ApplicationList();
    
    //Current node selected by the user
    private Node currentNode;
    
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
    public Node select(String name) {
        for(Node node : this) {
            if(node.nameProperty().get().equals(name)) {
                currentNode = node;
                break;
            }
        }
        return currentNode;
    }

    /**
     * Getter
     * @return get the current node
     */
    public Node getCurrentNode() {
        return currentNode;
    }
    
}
