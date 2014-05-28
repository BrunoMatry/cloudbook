package hmi.content.node.component;

import hmi.content.node.component.tableview.MesureTableView;
import hmi.content.AbstractActivity;
import hmi.content.node.NodeComponentView;
import hmi.content.node.SummarizedView;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;
import model.node.Mesure;
import model.node.MyNode;

/**
 * Area displaying generated mesures.
 
 */
public class MesurePane extends NodeComponentView<Mesure> {

    /**
     * Constructor
     * @param p parent activity
     */
    public MesurePane(AbstractActivity p) {
        super(p);
        title = "Mesures";
        table = new MesureTableView();
        setCenter(table);
    }

    @Override
    public SummarizedView makeSummarized() {
        SummarizedView res = new SummarizedView(this, new Text("Mesures"));
        return res;
    }

    /**
     * Sets the content of the table as the list of mesures of the specified node
     * @param node node from which the mesures are to be displayed
     */
    @Override
    public void bind(MyNode node) {
        getChildren().remove(table);
        table = new MesureTableView();
        ObservableList<Mesure> mesureList = node.getMesures().boxObservableList();
        table.setItems(mesureList);
        setCenter(table);
    }
    
}
