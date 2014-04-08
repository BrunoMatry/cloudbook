package model.node;

import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Message implements Information {
    
    protected Information content;
    protected Date date;
    protected int idSender;
    protected boolean relevant;
    protected AppVector vector;
    
    /* TODO Verifier si l'utilisation des proprietes est necessaire */
    protected String _description;
    protected transient StringProperty description;
    public StringProperty descriptionProperty() {
        return description;
    }
    
    public Message() {
        description = new SimpleStringProperty();
        description.set("Pas de message");
    }
    
    public Message(String msg) {
        description = new SimpleStringProperty();
        description.set(msg);
    }
    
    public Message(Information cntnt, int idSndr, boolean rlvnt, AppVector vctr) {
  
        content = cntnt;
        idSender = idSndr;
        date = new Date();
        relevant = rlvnt;
        vector = vctr;
        
        /* TODO changer cet affichage */
        description = new SimpleStringProperty(idSender + ", " + date + ", " + relevant + ", " + vector);
    }

    @Override
    public void saveProperties() {
        _description = description.get();
    }

    @Override
    public void restoreProperties() {
        description = new SimpleStringProperty();
        if(_description != null)
            description.set(_description);
        else
            description.set("Pas de message");
    }
}
