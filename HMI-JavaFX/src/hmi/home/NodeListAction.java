package hmi.home;

/**
 * command
 * strategy
 * Defines an action to perform when a click is made on a node
 */
public abstract class NodeListAction {
    
    //command parameter : name of the node file
    protected String node;

    /**
     * Constructor
     */
    public NodeListAction() {
        
    }
    
    /**
     * Consructor
     * @param node node on which the action is to be performed
     */
    public NodeListAction(String node) {
        this.node = node;
    }
    
    /**
     * performs an action on the selected node
     */
    public abstract void execute();
    
    /**
     * Getter
     * @return node field
     */
    public final String getNode() {
        return node;
    }

    /**
     * Setter
     * @param node node field
     */
    public void setNode(String node) {
        this.node = node;
    }
    
}
