package model.node;

import model.network.interfaces.Information;
import java.util.Date;
import java.util.Objects;

public class Message implements Information {
    
    protected Information content;
    protected Date date;
    protected int idSender;
    protected boolean relevant;
    protected AppVector vector;
    
    public Message(int id, AppVector vect, Information ctnt, boolean rlvnt) throws NullPointerException {
        if(ctnt == null || vect == null)
            throw new NullPointerException();
        idSender = id;
        content = ctnt;
        vector = vect;
        date = new Date();
        relevant = rlvnt;
    }

    @Override
    public String toString() {
        return idSender + ", " + date + ", " + relevant + ", " + vector;
    }
    
    public int getIdSender() { return idSender; }
    public Information getContent() { return content; }
    public Date getDate() { return new Date(date.getTime()); }
    public AppVector getVector() { return vector.copy(); }
    public boolean getRelevance() { return relevant; }
    
    /**
     * save each property of each contained object
     */
    @Override
    public void saveProperties() {
        content.saveProperties();
        vector.saveProperties();
    }

    /**
     * restore each property of each contained object
     */
    @Override
    public void restoreProperties() {
        content.restoreProperties();
        vector.restoreProperties();
    }

    /**
     * hashCode
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.content);
        hash = 29 * hash + Objects.hashCode(this.date);
        hash = 29 * hash + this.idSender;
        hash = 29 * hash + (this.relevant ? 1 : 0);
        hash = 29 * hash + Objects.hashCode(this.vector);
        return hash;
    }

    /**
     * Compares two messages field by field
     * @param obj object to be compared with
     * @return true if the two objects have equals fields
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Message other = (Message) obj;
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (this.idSender != other.idSender) {
            return false;
        }
        if (!this.date.equals(other.date)) {
            return false;
        }
        if (this.relevant != other.relevant) {
            return false;
        }
        if (!Objects.equals(this.vector, other.vector)) {
            return false;
        }
        return true;
    }
}
