/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node.component;

import hmi.content.node.NodeComponentView;
import hmi.content.node.SummarizedView;
import javafx.scene.Node;
import javafx.scene.text.Text;

/**
 *
 * @author Gwendal
 */
public class MessageView extends NodeComponentView {

    public MessageView() {
        super();
        title = "Messages";
    }

    @Override
    public SummarizedView makeSummarized() {
        SummarizedView res = new SummarizedView(this, new Text(getClass().getName()));
        return res;
    }
    
}
