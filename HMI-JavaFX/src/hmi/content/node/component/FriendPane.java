/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node.component;

import hmi.content.AbstractActivity;
import hmi.content.node.NodeComponentView;
import hmi.content.node.SummarizedView;
import hmi.content.node.component.tableview.FriendTableView;
import javafx.scene.text.Text;
import model.node.friend.Friend;

/**
 *
 * @author Gwendal
 * Pane containing the friends' table
 */
public class FriendPane extends NodeComponentView<Friend> {

    /**
     * Constructor
     * @param p parent activity
     */
    public FriendPane(AbstractActivity p) {
        super(p);
        title = "Friends";
        table = new FriendTableView();
        setCenter(table);
    }

    @Override
    public SummarizedView makeSummarized() {
        return new SummarizedView<>(this, new Text("Friends"));
    }
    
}
