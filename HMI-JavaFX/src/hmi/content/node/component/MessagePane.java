/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node.component;

import hmi.content.AActivity;
import hmi.content.node.NodeComponentView;
import hmi.content.node.SummarizedView;
import hmi.content.node.component.tableview.MessageTableView;
import javafx.scene.text.Text;

/**
 *
 * @author Gwendal
 */
public class MessagePane extends NodeComponentView {
    /**
     * Constructor
     * @param p parent activity
     */
    public MessagePane(AActivity p) {
        super(p);
        title = "Messages";
        table = new MessageTableView();
        setCenter(table);
    }

    @Override
    public SummarizedView makeSummarized() {
        return new SummarizedView<>(this, new Text("Messages"));
    }
}
