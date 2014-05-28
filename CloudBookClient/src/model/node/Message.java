package model.node;

import model.network.interfaces.Information;
import java.util.Date;
import java.util.Objects;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Message implements Information {
    
    //private attributes which purpose is to save and restore properties values
    protected Information _content;
    protected Date _date;
    protected String _idSender;
    protected boolean _relevant;
    
    //vector of characteristics of the sender
    protected AppVector vector;
    
    protected transient ObjectProperty<Information> content;
    protected transient ObjectProperty<Date> date;
    protected transient StringProperty idSender;
    protected transient BooleanProperty relevant;
    
    /**
     * Information stored in the message.
     * This information is one of the sender's
     * @return Information property
     */
    public ObjectProperty<Information> contentProperty() {
        return content;
    } 
    
    /**
     * Date of generation of the message
     * @return date property
     */
    public ObjectProperty<Date> dateProperty() {
        return date;
    }
    
    /**
     * identifier of the sender
     * @return identifier property
     */
    public StringProperty idSenderProperty() {
        return idSender;
    }
    
    /**
     * indicates if the sender is relevant or not
     * @return relevant property
     */
    public BooleanProperty relevantProperty() {
        return relevant;
    }
    
    /**
     * Message constructor
     * @param id id of the sender
     * @param vect vector of the sender
     * @param ctnt content of the message
     * @param rlvnt
     * @throws NullPointerException in case of content or vector empty
     */
    public Message(String id, AppVector vect, Information ctnt, boolean rlvnt) throws NullPointerException {
        if(ctnt == null || vect == null)
            throw new NullPointerException();
        idSender = new SimpleStringProperty(id);
        content = new SimpleObjectProperty<>(ctnt);
        vector = vect;
        date = new SimpleObjectProperty<>(new Date());
        relevant = new SimpleBooleanProperty(rlvnt);
    }

    @Override
    public String toString() {
        return idSender + ", " + date + ", " + relevant + ", " + vector;
    }
    
    public String getIdSender() { return idSender.get(); }
    public Information getContent() { return content.get(); }
    public Date getDate() { return new Date(date.get().getTime()); }
    public AppVector getVector() { return vector.copy(); }
    public boolean getRelevance() { return relevant.get(); }
    
    @Override
    public void saveProperties() {
        _idSender = idSender.get();
        _date = date.get();
        _relevant = relevant.get();
        _content = content.get();
        _content.saveProperties();
        vector.saveProperties();
    }

    @Override
    public void restoreProperties() {
        idSender = new SimpleStringProperty(_idSender);
        date = new SimpleObjectProperty<>(_date);
        relevant = new SimpleBooleanProperty(_relevant);
        _content.restoreProperties();
        content = new SimpleObjectProperty<>(_content);
        vector.restoreProperties();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        boolean res = (obj != null);
        res &= (getClass() == obj.getClass());
        final Message other = (Message) obj;
        res &= Objects.equals(this.content, other.content);
        res &= this.idSender.equals(other.idSender);
        res &= this.date.equals(other.date);
        res &= (this.relevant == other.relevant);
        res &= Objects.equals(this.vector, other.vector);
        return res;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.vector);
        hash = 79 * hash + Objects.hashCode(this.content);
        hash = 79 * hash + Objects.hashCode(this.date);
        hash = 79 * hash + Objects.hashCode(this.idSender);
        hash = 79 * hash + Objects.hashCode(this.relevant);
        return hash;
    }
}