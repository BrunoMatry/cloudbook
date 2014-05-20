package hmi.content.node;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

/**
 * @param <T> View type
 */
public class SummarizedView<T extends Node> extends Parent implements IComponentView {

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
        fullView = full;
        view = node;
        view.setLayoutX(0);
        view.setLayoutY(0);
        getChildren().add(view);
        setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
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
