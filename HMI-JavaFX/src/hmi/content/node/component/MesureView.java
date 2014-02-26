/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node.component;

import hmi.content.node.NodeComponentView;
import hmi.content.node.NodeView;
import hmi.content.node.SummarizedView;
import javafx.scene.Node;

/**
 *
 * @author Gwendal
 */
public class MesureView extends NodeComponentView {

    public MesureView(NodeView parent) {
        super(parent);
    }

    @Override
    public SummarizedView makeSummarizedView() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node getNode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
