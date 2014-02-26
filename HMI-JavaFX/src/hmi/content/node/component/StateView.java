/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node.component;

import hmi.content.node.NodeComponentView;
import hmi.content.node.SummarizedView;
import javafx.scene.Node;

/**
 *
 * @author Gwendal
 */
public class StateView extends NodeComponentView {

    protected CloudView cloud;
    
    public StateView() {
        super();
        cloud = new CloudView();
        cloud.setLayoutX(250);
        cloud.setLayoutY(250);
    }
    
    @Override
    public SummarizedView makeSummarizedView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node getNode() {
        return cloud;
    }
    
}
