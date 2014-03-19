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
    
    protected String _description;
    protected transient StringProperty description;
    public StringProperty descriptionProperty() {
        return description;
    }
    
    public Message() {
        description = new SimpleStringProperty();
        description.set("Pas de message");
    }
    
    public Message(Information content, int idSender, boolean relevant, AppVector vector) {
        description = new SimpleStringProperty("Pas de message");
        this.content = content;
        this.idSender = idSender;
        this.date = new Date();
        this.relevant = relevant;
        this.vector = vector;
    }

    @Override
    public void saveProperties() {
        _description = description.get();
    }

    @Override
    public void restoreProperties() {
        description = new SimpleStringProperty(_description);
    }
}
