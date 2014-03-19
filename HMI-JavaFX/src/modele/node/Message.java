package modele.node;

import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Bruno
 */
public class Message implements Information {
    private Information content;
    private Date date;
    private int idSender;
    private boolean relevant;
    private AppVector vector;
    
    protected MySimpleStringProperty description;
    public StringProperty descriptionProperty() {
        return description;
    }
    
    public Message() {
        description = new MySimpleStringProperty();
        description.set("Pas de message");
    }
    
    public Message(Information content, int idSender, boolean relevant, AppVector vector) {
        description = new MySimpleStringProperty("Pas de message");
        this.content = content;
        this.idSender = idSender;
        this.date = new Date();
        this.relevant = relevant;
        this.vector = vector;
    }
}
