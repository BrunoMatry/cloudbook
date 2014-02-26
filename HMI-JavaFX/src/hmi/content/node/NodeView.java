/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node;

import hmi.content.node.component.StateView;
import java.util.ArrayList;
import javafx.scene.Node;
import modele.node.CloudBookNode;

/**
 *
 * @author Gwendal
 * singleton
 * View of the current member
 */
public final class NodeView extends Activity {
    
    public static final NodeView INSTANCE = new NodeView();
    //TODO : use observer tools of javafx
    //model
    private CloudBookNode model;
    private ArrayList<SummarizedView> components;
    
    /**
     * default constructor
     */
    private NodeView() {
        super();
        title = "Friend management";
        components = new ArrayList<>();
        StateView sv = new StateView();
        components.add(sv.makeSummarized(this));
        //components.add(new MessageView());
        //components.add(new MesureView());
        
        for(int i = 0 ; i < components.size() ; i++) {
            placeChild(components.get(i), (i+1)*100, (i+1)*100);
        }
    }
    // TODO : affichage de la vue entiere au clic sur la vue resumee
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
   
    /**
     * Places the current view in its parent without refreshing it
     * @param x : horizontal position
     * @param y : vertical position
     */
    public void placeChild(Node node, int x, int y) {
        node.setLayoutX(x);
        node.setLayoutY(y);
        getChildren().add(node);
    }
    
    /**
     * TODO
     * @param source : component demanding to be displayed
     */
    public void onDisplay(ComponentView source) {
        
    }
    
    /**
     * TODO
     * @param source : component demanding to be updated
     */
    public void onUpdate(ComponentView source) {
        
    }
    
    /**
     * TODO
     * @param source : component demanding to be hidden
     */
    public void onHide(ComponentView source) {
        getChildren().remove(source);
    }
}
