package model.node.friend;

import model.network.interfaces.Information;
import java.util.Date;
import java.util.Objects;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.node.AppVector;

public class Friend extends Member implements Information {  
    
    /* Attributs serialisables */
    protected double _relevance;
    protected int _confidence;
    protected Date _lastConnection;
    
    /* Proprietes non serialisables */
    protected transient StringProperty id;
    protected transient DoubleProperty relevance;
    protected transient IntegerProperty confidence;
    protected transient ObjectProperty<Date> lastConnection;
    
    /**
     * Constructor
     * @param friendId identifier of the friend application
     * @param cnfdnce confidence value
     * @param rlvnce relevance value
     * @param vector vector of characteriqtics of the member application
     * @throws NullPointerException 
     */
    public Friend(String friendId, int cnfdnce, double rlvnce, AppVector vector) throws NullPointerException {
        super(friendId, vector);
        if(vector == null)
            throw new NullPointerException();
        id = new SimpleStringProperty(friendId);
        relevance = new RelevanceProperty(this, rlvnce);
        confidence = new ConfidenceProperty(this, cnfdnce);
        lastConnection = new SimpleObjectProperty<>(new Date());
    }

    @Override
    public String getId() {
        return id.get();
    }
    
    /**
     * Setter
     * @param vector vector attribute
     */
    public void setVector(AppVector vector) {
        this.vector = vector;
        updateLastConnectionDate();
    }
    
    /**
     * The las tconnection date is updated to the current one
     */
    void updateLastConnectionDate() {
        lastConnection.set(new Date());
    }
    
    /**
     * Calcul du nombre de jours écoulés depuis la dernière connection
     * @return  nombre de jours écoulés depuis la dernière connection de l'ami
     */
    public int daysSinceLastConnection() {
        Date today = new Date();
        long diff = today.getTime() - lastConnection.get().getTime();
        return (int) (diff / (1000*60*60*24));
    }
    
    public StringProperty idProperty() { return id; }
    @Override
    public AppVector getVector() { return vector; }
    public DoubleProperty relevanceProperty() { return relevance; }
    public IntegerProperty confidenceProperty() { return confidence; }
    public ObjectProperty lastConnectionProperty() { return lastConnection; }

    @Override
    public void saveProperties() {
        _id = id.get();
        _relevance =  relevance.get();
        _confidence = confidence.get();
        _lastConnection = lastConnection.get();
    }

    @Override
    public void restoreProperties() {
        id = new SimpleStringProperty(_id);
        relevance = new RelevanceProperty(this, _relevance);
        confidence = new ConfidenceProperty(this, _confidence);
        lastConnection = new SimpleObjectProperty<>(_lastConnection);
    }

    /**
     * hashCode
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.relevance);
        hash = 17 * hash + Objects.hashCode(this.confidence);
        hash = 17 * hash + Objects.hashCode(this.lastConnection);
        return hash;
    }

    /**
     * equals
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
        final Friend other = (Friend) obj;
        return this.relevance.get() == other.relevance.get()
                && this.confidence.get() == other.confidence.get();
    }

    @Override
    public String toString() {
        return "Friend{" + "id=" + id.get() + ", relevance=" + relevance.get() + ", confidence=" + confidence.get() + ", lastConnection=" + lastConnection.get()+ '}';
    }
    
    
}