/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.node;

import java.util.ArrayList;

/**
 *
 * @author Gwendal
 * singleton
 * Contains all the engine which run in this JVM
 */
public final class ApplicationList extends ArrayList<CloudBookNode> {
    
    public static final ApplicationList INSTANCE = new ApplicationList();
    
    //Current node selected by the user
    private CloudBookNode currentNode;
    
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
    public CloudBookNode select(String name) {
        for(CloudBookNode node : this) {
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
    public CloudBookNode getCurrentNode() {
        return currentNode;
    }
    
}
