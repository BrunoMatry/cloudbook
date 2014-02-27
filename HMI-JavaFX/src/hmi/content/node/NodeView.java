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
        MesureView mv = new MesureView();
        MessageView msg = new MessageView();
        components.add(sv.makeSummarized());
        components.add(mv.makeSummarized());
        components.add(msg.makeSummarized());
        
        int size = components.size();
        for(int i = 0 ; i < size ; i++) {
            SummarizedView cmp = components.get(i);
            double x = (getScene().getWidth()/2) + 200*Math.cos(i*(2*Math.PI/size))
                    - cmp.getBoundsInParent().getWidth()/2;
            double y = (getScene().getHeight()/2) + 200*Math.sin(i*(2*Math.PI/size))
                    - cmp.getBoundsInParent().getHeight()/2;
            placeChild(cmp, (int)x, (int)y);
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
