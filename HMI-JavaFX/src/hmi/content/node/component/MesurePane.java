/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node.component;

import hmi.content.AActivity;
import hmi.content.node.NodeComponentView;
import hmi.content.node.SummarizedView;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import model.node.Mesure;

/**
 *
 * @author Gwendal
 */
public class MesurePane extends NodeComponentView {

    //table of mesures
    protected TableView<Mesure> table;
    
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
        SummarizedView res = new SummarizedView(this, new Text(getClass().getName()));
        return res;
    }
    
    /**
     * Getter
     * @return table field
     */
    public final TableView<Mesure> getTable() {
        return table;
    }
    
}
