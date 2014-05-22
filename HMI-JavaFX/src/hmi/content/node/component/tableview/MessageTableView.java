/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node.component.tableview;

import java.util.Date;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.network.interfaces.Information;
import model.node.Message;

/**
 *
 * @author Gwendal
 */
public class MessageTableView extends StandardizedTable {
    
    protected TableColumn<Message, Integer> idSenderCol;
    protected TableColumn<Message, Boolean> relevantCol;
    protected TableColumn<Message, Date> dateCol;
    protected TableColumn<Message, Information> contentCol;
    
    /**
     * Constructor
     */
    public MessageTableView() {
        idSenderCol = buildColumn("Id", "idSender", 4);
        relevantCol = buildColumn("Relevant ?", "relevant", 4);
        setUpDateCol();
        setUpContentCol();
        getColumns().addAll(idSenderCol, relevantCol, dateCol, contentCol);
    }
    
    /**
     * Makes the content of the column be the string representation of the date
     */
    private void setUpDateCol() {
        dateCol = buildColumn("Date", "date", 4);
        dateCol.setCellFactory(new Callback<TableColumn<Message, Date>, TableCell<Message, Date>>() {

            @Override
            public TableCell<Message, Date> call(TableColumn<Message, Date> p) {
                TableCell<Message, Date> cell = new TableCell<Message, Date>() {

                    @Override
                    protected void updateItem(Date t, boolean bln) {
                        if(t != null) {
                            Text text = new Text(t.toString());
                            setGraphic(text);
                        }
                    }
                    
                };
                return cell;
            }
        });
    }
    
    /**
     * Makes the content of the column be the string representation of the content
     */
    private void setUpContentCol() {
        contentCol = buildColumn("Content", "content", 4);
        contentCol.setCellFactory(new Callback<TableColumn<Message, Information>, TableCell<Message, Information>>() {

            @Override
            public TableCell<Message, Information> call(TableColumn<Message, Information> p) {
                TableCell<Message, Information> cell = new TableCell<Message, Information>() {

                    @Override
                    protected void updateItem(Information t, boolean bln) {
                        if(t != null) {
                            Text text = new Text(t.toString());
                            setGraphic(text);
                        }
                    }
                    
                };
                return cell;
            }
        });
    }
}
