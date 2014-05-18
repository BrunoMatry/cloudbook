/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node;

import hmi.content.AActivity;
import hmi.content.Activity;
import hmi.content.node.component.MessageView;
import hmi.content.node.component.MesurePane;
import hmi.content.node.component.StateView;
import hmi.home.HomeView;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.engine.Engine;
import model.node.CloudBookNode;
import model.node.Mesure;

/**
 *
 * @author Gwendal
 * singleton
 * View of the current member
 */
public final class NodeView extends Activity {
    
    public static final NodeView INSTANCE = new NodeView(HomeView.INSTANCE);
    
    //view contaigning all received mesures
    private MesurePane mesurePane;
    
    //summary of the state of application
    private SummarizedView<ImageView> state;
    
    //summary of the mesures
    private SummarizedView<Text> mesures;
    
    //summary of the messages
    private SummarizedView<Text> message;
    
    /**
     * 
     * @param p the previous Activity
     */
    private NodeView(AActivity p) {
        super(p);
        title = "Friend management";
        ArrayList<SummarizedView> components = new ArrayList<>();
        components.add(getState());
        components.add(getMesures());
        components.add(getMessage());
        
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
    
    /**
     * getter
     * if state is null, it is initialized
     * @return state attribute
     */
    public SummarizedView getState() {
        if(state == null) {
            StateView sv = new StateView(this);
            state = sv.makeSummarized();
        }
        return state;
    }

    /**
     * getter
     * if mesures is null, it is initialized
     * @return mesures attribute
     */
    public SummarizedView getMesures() {
        if(mesures == null) {
            mesurePane = new MesurePane(this);
            TableView<Mesure> table = mesurePane.getTable();
            CloudBookNode node = Engine.INSTANCE.getNode();
            ObservableList<Mesure> mesureList = node.getMesures().boxObservableList();
            table.setItems(mesureList);
            mesures = mesurePane.makeSummarized();
        }
        return mesures;
    }

    /**
     * getter
     * if message is null, it is initialized
     * @return message attribute
     */
    public SummarizedView getMessage() {
        if(message == null) {
            MessageView mv = new MessageView(this);
            message = mv.makeSummarized();
        }
        return message;
    }
    
    /**
     * initialize the model and build all the children views
     * @param model : model of the current view
     */
    /*
    public NodeView(CloudBookNode model) {
        super();
        this.model = model;
        components = new ArrayList<>();
        //components.add();
    }
   */
    /**
     * Places the current view in its parent without refreshing it
     * @param node child to place
     * @param x horizontal position
     * @param y vertical position
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
