package hmi.content.node.component;

import hmi.content.AbstractActivity;
import hmi.content.node.NodeComponentView;
import hmi.content.node.SummarizedView;
import hmi.content.node.component.tableview.StateTableView;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import model.node.MyNode;
import model.node.State;


public class StatePane extends NodeComponentView<State> {

    public StatePane(AbstractActivity p) {
        super(p);
        title = "History";
        table = new StateTableView();
        setCenter(table);
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
