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
    
    protected StringProperty description;
    public StringProperty descriptionProperty() {
        return description;
    }
    
    public Message() {
        description = new SimpleStringProperty("Pas de message");
    }
}
