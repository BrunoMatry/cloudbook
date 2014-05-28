package hmi.content.node.component;

import hmi.content.AbstractActivity;
import hmi.content.node.NodeComponentView;
import hmi.content.node.SummarizedView;
import hmi.content.node.component.tableview.FriendTableView;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;
import model.node.MyNode;
import model.node.friend.Friend;

/**
 * Pane containing the friends table
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

    /**
     * Sets the content of the table as the list of friends of the specified node
     * @param node node from which the friends are to be displayed
     */
    @Override
    public void bind(MyNode node) {
        getChildren().remove(table);
        table = new FriendTableView();
        ObservableList<Friend> friendList = node.getFriends().boxObservableList();
        table.setItems(friendList);
        setCenter(table);
    }
    
}
