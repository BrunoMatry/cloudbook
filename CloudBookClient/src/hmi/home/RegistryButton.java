package hmi.home;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * 
 * Defines an action to perform when a click is made on a node
 */
public abstract class RegistryButton extends Button {
    
    //command parameter : name of the node file
    protected String node;

    /**
     * Constructor
     */
    public RegistryButton() {
        setUp();
    }
    
    /**
     * Consructor
     * @param node node on which the action is to be performed
     */
    public RegistryButton(String node) {
        this.node = node;
        setUp();
    }
    
    /**
     * Sets the button action as the execution of the execute() method.
     */
    private void setUp() {
        this.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                execute();
            }
        });
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
