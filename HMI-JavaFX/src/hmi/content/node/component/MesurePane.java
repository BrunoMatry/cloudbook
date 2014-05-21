package hmi.content.node.component;

import hmi.content.node.component.tableview.MesureTableView;
import hmi.content.AActivity;
import hmi.content.node.NodeComponentView;
import hmi.content.node.SummarizedView;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import model.node.Mesure;

public class MesurePane extends NodeComponentView<Mesure> {

    /**
     * Constructor
     * @param p parent activity
     */
    public MesurePane(AActivity p) {
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
    
}
