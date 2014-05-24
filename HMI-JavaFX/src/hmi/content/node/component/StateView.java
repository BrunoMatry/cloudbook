package hmi.content.node.component;

import hmi.content.AbstractActivity;
import hmi.content.node.NodeComponentView;
import hmi.content.node.SummarizedView;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import model.node.MyNode;
import model.node.State;


public class StateView extends NodeComponentView {

    //all the clouds during the application existence
    protected ArrayList<ImageView> clouds;
    
    public StateView(AbstractActivity p) {
        super(p);
        clouds = new ArrayList<>();
        title = "History";
    }

    @Override
    public SummarizedView makeSummarized() {
        return new SummarizedView(this, new CloudView());
    }

    /**
     * Sets the content of the table as the list of state of the specified node
     * @param node node from which the state are to be displayed
     */
    @Override
    public void bind(MyNode node) {/*
        ObservableList<State> stateList = node.getSs().boxObservableList();
        table.setItems(stateList);*/
    }
    
}
