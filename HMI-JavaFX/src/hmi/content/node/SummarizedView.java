/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Gwendal
 */
public class SummarizedView extends Parent implements ComponentView {

    //full version of the current view
    protected NodeComponentView fullView;
    
    //javafx node corresponding to the actual view
    protected Node view;
    
    public SummarizedView(NodeComponentView full, Node node) {
        fullView = full;
        view = node;
        view.setLayoutX(0);
        view.setLayoutY(0);
        getChildren().add(view);
        setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                fullView.launch();
            }
        
        });
    }
    
    @Override
    public void display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node getNode() {
        return view;
    }
    
    public NodeComponentView getFullView() {
        return fullView;
    }
    
}
