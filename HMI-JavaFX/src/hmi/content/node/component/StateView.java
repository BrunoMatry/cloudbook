/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node.component;

import hmi.content.node.NodeComponentView;
import hmi.content.node.NodeView;
import hmi.content.node.SummarizedView;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.TableView;

/**
 *
 * @author Gwendal
 */
public class StateView extends NodeComponentView {

    //all the clouds during the application existence
    protected ArrayList<CloudView> clouds;
    
    public StateView() {
        super();
        clouds = new ArrayList<>();
        for(int i = 0 ; i < 3 ; i++) {
            clouds.add(new CloudView());
        }
        title = "History";
    }

    @Override
    public SummarizedView makeSummarized() {
        return new SummarizedView(this, clouds.get(0));
    }
    
}
