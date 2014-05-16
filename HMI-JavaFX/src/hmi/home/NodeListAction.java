/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.home;

/**
 *
 * @author Gwendal
 * command
 * strategy
 * Defines an action to perform when a click is made on a node
 */
public abstract class NodeListAction {
    
    //command parameter : name of the node file
    protected String node;
    
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
}
