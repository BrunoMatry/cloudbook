/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node;

import javafx.scene.Node;

/**
 *
 * @author Gwendal
 */
public class SummarizedView implements ComponentView {

    //full version of the current view
    private NodeComponentView fullView;
    
    //javafx node corresponding to the actual view
    protected Node view;
    
    public SummarizedView(NodeComponentView full, Node node) {
        fullView = full;
        view = node;
    }
    
    @Override
    public void display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node getNode() {
        return view;
    }
    
}
