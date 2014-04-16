package model.node;

import model.network.interfaces.Information;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Message implements Information {
    
    protected Information content;
    protected Date date;
    protected int idSender;
    protected boolean relevant;
    protected AppVector vector;
    
    public Message(int id, AppVector vect, Information ctnt, boolean rlvnt) {
        idSender = id;
        content = ctnt;
        vector = vect;
        date = new Date();
        relevant = rlvnt;
    }

    public String toString() {
        return idSender + ", " + date + ", " + relevant + ", " + vector;
    }
    
    @Override
    public void saveProperties() {
    }

    @Override
    public void restoreProperties() {
    }
}
