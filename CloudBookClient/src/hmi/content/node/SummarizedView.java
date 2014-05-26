package hmi.content.node;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 * @param <T> View type
 */
public class SummarizedView<T extends Node> extends Button {

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
    
    /**
     * Builds-up the summarized view
     * A click on the view launches the full view
     * @param full full version of the current view
     * @param title title of the button
     */
    public SummarizedView(NodeComponentView full, String title) {
        super(title);
        fullView = full;
        setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                fullView.bindWithNode();
                fullView.launch();
            }
        
        });
    }
    
    public NodeComponentView getFullView() {
        return fullView;
    }

    public T getView() {
        return view;
    }
    
}
