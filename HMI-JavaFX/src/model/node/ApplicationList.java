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
     * The node is added to the list if necessary
     * @param node node to be selected as the current one
     */
    public void select(CloudBookNode node) {
        if(!this.contains(node))
            this.add(node);
        currentNode = node;
    }

    /**
     * Getter
     * @return get the current node
     */
    public CloudBookNode getCurrentNode() {
        return currentNode;
    }
    
}
