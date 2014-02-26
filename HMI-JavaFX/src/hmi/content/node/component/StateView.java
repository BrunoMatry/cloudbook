/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node.component;

import hmi.content.node.NodeComponentView;
import hmi.content.node.SummarizedView;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.TableView;

/**
 *
 * @author Gwendal
 */
public class StateView extends NodeComponentView {

    protected ArrayList<CloudView> clouds;
    protected TableView historyView;
    
    public StateView() {
        super();
        historyView = new TableView();
        clouds = new ArrayList<>();
        for(int i = 0 ; i < 3 ; i++)
            clouds.add(new CloudView());
    }
    
    @Override
    public SummarizedView makeSummarizedView() {
        return new SummarizedView(this, clouds.get(0));
    }

    @Override
    public Node getNode() {
        return historyView;
    }
    
}
