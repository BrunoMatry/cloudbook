package hmi.content.node.component;

import hmi.content.AbstractActivity;
import hmi.content.node.NodeComponentView;
import hmi.content.node.SummarizedView;
import hmi.content.node.component.tableview.StateTableView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import model.engine.Engine;
import model.node.FileEngineRelation;
import model.node.MyNode;
import model.node.State;


public class StatePane extends NodeComponentView<State> {

    //Look for the best platform, according to the current data
    private Button update;
    
    public StatePane(AbstractActivity p) {
        super(p);
        title = "History";
        table = new StateTableView();
        setUpUpdateButton();
        setCenter(table);
        setBottom(update);
    }
    
    /**
     * Sets up the button command and label
     */
    private void setUpUpdateButton() {
        update = new Button("Look for the best cloud platform");
        update.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                Engine engine = FileEngineRelation.INSTANCE.getCurrentEngine();
                engine.updateState();
            }
        });
    }

    @Override
    public SummarizedView makeSummarized() {
        return new SummarizedView(this, new ImageView());
    }

    /**
     * Sets the content of the table as the list of state of the specified node
     * @param node node from which the state are to be displayed
     */
    @Override
    public void bind(MyNode node) {
        ObservableList<State> stateList = node.getStates().boxObservableList();
        table.setItems(stateList);
    }
    
}
