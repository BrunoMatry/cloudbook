/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node;

import hmi.content.node.component.MessageView;
import hmi.content.node.component.MesureView;
import hmi.content.node.component.StateView;
import java.util.ArrayList;
import javafx.scene.Parent;
import model.friendmanager.CloudBookNode;

/**
 *
 * @author Gwendal
 * 
 * View of the current member
 */
public class NodeView extends Parent {
    
    //TODO : use observer tools of javafx
    //model
    private CloudBookNode model;
    private ArrayList<NodeComponentView> components;
    
    /**
     * default constructor
     */
    public NodeView() {
        super();
        components = new ArrayList<>();
        components.add(new StateView());
        //components.add(new MessageView());
        //components.add(new MesureView());
        
        for(NodeComponentView ncv : components)
            getChildren().add(ncv.getNode());
    }
    
    /**
     * initialize the model and build all the children views
     * @param model : model of the current view
     */
    public NodeView(CloudBookNode model) {
        super();
        this.model = model;
        components = new ArrayList<>();
        //components.add();
    }
   
}
