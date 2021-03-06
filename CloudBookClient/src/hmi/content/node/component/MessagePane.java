package hmi.content.node.component;

import hmi.content.AbstractActivity;
import hmi.content.node.NodeComponentView;
import hmi.content.node.SummarizedView;
import hmi.content.node.component.tableview.MessageTableView;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;
import model.node.Message;
import model.node.MyNode;

/**
 * Area displaying arriving messages.
 
 */
public class MessagePane extends NodeComponentView {
    
    /**
     * Constructor
     * @param p parent activity
     */
    public MessagePane(AbstractActivity p) {
        super(p);
        title = "Messages";
        table = new MessageTableView();
        setCenter(table);
    }

    @Override
    public SummarizedView makeSummarized() {
        return new SummarizedView<>(this, new Text("Messages"));
    }

    /**
     * Sets the content of the table as the list of messages of the specified node
     * @param node node from which the messages are to be displayed
     */
    @Override
    public void bind(MyNode node) {
        getChildren().remove(table);
        table = new MessageTableView();
        ObservableList<Message> messageList = node.getMessages().boxObservableList();
        table.setItems(messageList);
        setCenter(table);
    }
}
