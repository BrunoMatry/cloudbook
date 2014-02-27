/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.friend;

import hmi.content.AActivity;
import hmi.content.node.NodeComponentView;
import hmi.content.node.SummarizedView;

/**
 *
 * @author Bruno
 */
public class FriendView extends NodeComponentView {
    // Possède un objet ami
    public FriendView(AActivity p){
        super(p);
    }

    @Override
    public SummarizedView makeSummarized() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
