/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.home;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.engine.Engine;
import model.node.CloudBookNode;

/**
 *
 * @author Gwendal
 * command
 * command dedecated to the loading of a node
 */
public class LoadNode extends NodeListAction {

    /**
     * Constructor
     */
    public LoadNode() {
        
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
            Engine.INSTANCE.initialize(CloudBookNode.load(node + ".ser"));
            HomeView.INSTANCE.launch();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(LoadNode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
