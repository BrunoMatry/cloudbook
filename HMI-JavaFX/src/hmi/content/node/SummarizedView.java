package hmi.content.node;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 * @param <T> View type
 */
public class SummarizedView<T extends Node> extends Button implements IComponentView {

    //full version of the current view
    protected NodeComponentView fullView;
    
    //javafx node corresponding to the current view
    protected T view;
    
    /**
     * Builds-up the summarized view
     * A click on the view launches the full view
     * @param full full version of the current view
     * @param node javafx node corresponding to the current view
     */
    public SummarizedView(NodeComponentView full, T node) {
        super();
        fullView = full;
        view = node;
        setGraphic(view);
        setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                fullView.bindWithNode();
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
    
    public NodeComponentView getFullView() {
        return fullView;
    }

    public T getView() {
        return view;
    }
    
}
