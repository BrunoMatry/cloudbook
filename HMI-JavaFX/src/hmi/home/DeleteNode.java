/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.home;

/**
 *
 * @author Gwendal
 */
public class DeleteNode extends NodeListAction {

    /**
     * Consructor
     * @param node node on which the action is to be performed
     */
    public DeleteNode(String node) {
        super(node);
    }

    /**
     * Deletes the selected node
     */
    @Override
    public void execute() {
        
    }
    
}
