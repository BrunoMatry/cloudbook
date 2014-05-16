/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.home;

import java.io.File;

/**
 *
 * @author Gwendal
 * command
 * command dedecated to the destruction of a save file
 */
public class DeleteNode extends NodeListAction {

    /**
     * Constructor
     */
    public DeleteNode() {
        super();
    }
    
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
        File saveFile = new File(node + ".ser");
        saveFile.delete();
    }
    
}
