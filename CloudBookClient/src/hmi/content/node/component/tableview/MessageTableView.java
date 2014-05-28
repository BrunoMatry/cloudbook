package hmi.content.node.component.tableview;

import java.util.Date;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.network.interfaces.Information;
import model.node.Message;

/**
 * Table displaying messages.
 
 */
public class MessageTableView extends TableView<Message> {
    
    //columns
    protected TableColumn<Message, String> idSenderCol;
    protected TableColumn<Message, Boolean> relevantCol;
    protected TableColumn<Message, Date> dateCol;
    protected TableColumn<Message, Information> contentCol;
    
    /**
     * Constructor
     */
    public MessageTableView() {
        TableColumnBuilder simpleBuilder = new SimpleTableColumnBuilder(this);
        idSenderCol = simpleBuilder.buildColumn("Id", "idSender", 4);
        relevantCol = simpleBuilder.buildColumn("Relevant ?", "relevant", 4);
        TableColumnBuilder dateBuilder = new ToStringTableColumnBuilder<Message, Date>(this);
        dateCol = dateBuilder.buildColumn("Date", "date", 4);
        TableColumnBuilder infoBuilder = new ToStringTableColumnBuilder<Message, Information>(this);
        contentCol = infoBuilder.buildColumn("Content", "content", 4);
        getColumns().addAll(idSenderCol, relevantCol, dateCol, contentCol);
    }
   
}
