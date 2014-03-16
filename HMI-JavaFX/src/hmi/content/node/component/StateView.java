/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node.component;

import hmi.content.AActivity;
import hmi.content.node.NodeComponentView;
import hmi.content.node.SummarizedView;
import java.util.ArrayList;
import modele.node.Cloud;

/**
 *
 * @author Gwendal
 */
public class StateView extends NodeComponentView {

    //all the clouds during the application existence
    protected ArrayList<CloudView> clouds;
    
    public StateView(AActivity p) {
        super(p);
        clouds = new ArrayList<>();
        for(int i = 0 ; i < 3 ; i++) {
            clouds.add(new CloudView(Cloud.DROPBOX));
        }
        title = "History";
    }

    @Override
    public SummarizedView makeSummarized() {
        return new SummarizedView(this, clouds.get(0));
    }
    
}
