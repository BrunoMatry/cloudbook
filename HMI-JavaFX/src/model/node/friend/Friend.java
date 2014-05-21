package model.node.friend;

import model.network.interfaces.Information;
import java.util.Date;
import java.util.Objects;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.node.AppVector;

public class Friend extends Member implements Information {  
    
    /* Attributs serialisables */
    protected double _relevance;
    protected int _confidence;
    protected Date _lastConnexion;
    
    /* Proprietes non serialisables */
    protected transient IntegerProperty id;
    protected transient DoubleProperty relevance;
    protected transient IntegerProperty confidence;
    protected transient ObjectProperty<Date> lastConnexion;
    
    /**
     * Constructor
     * @param friendId identifier of the friend application
     * @param cnfdnce confidence value
     * @param rlvnce relevance value
     * @param vector vector of characteriqtics of the member application
     * @throws NullPointerException 
     */
    public Friend(int friendId, int cnfdnce, double rlvnce, AppVector vector) throws NullPointerException {
        super(friendId, vector);
        if(vector == null)
            throw new NullPointerException();
        id = new SimpleIntegerProperty(friendId);
        relevance = new RelevanceProperty(this, rlvnce);
        confidence = new ConfidenceProperty(this, cnfdnce);
        lastConnexion = new SimpleObjectProperty<>(new Date());
    }

    @Override
    public int getId() {
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
        lastConnexion.set(new Date());
    }
    
    /**
     * Calcul du nombre de jours écoulés depuis la dernière connection
     * @return  nombre de jours écoulés depuis la dernière connection de l'ami
     */
    public int daysSinceLastConnection() {
        Date today = new Date();
        long diff = today.getTime() - lastConnexion.get().getTime();
        return (int) (diff / (1000*60*60*24));
    }
    
    public final IntegerProperty idProperty() { return id; }
    public final AppVector getVector() { return vector; }
    public final DoubleProperty relevanceProperty() { return relevance; }
    public final IntegerProperty confidenceProperty() { return confidence; }

    @Override
    public void saveProperties() {
        _id = id.get();
        _relevance =  relevance.get();
        _confidence = confidence.get();
        _lastConnexion = lastConnexion.get();
    }

    @Override
    public void restoreProperties() {
        id = new SimpleIntegerProperty(_id);
        relevance = new RelevanceProperty(this, _relevance);
        confidence = new ConfidenceProperty(this, _confidence);
        lastConnexion = new SimpleObjectProperty<>(_lastConnexion);
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
        hash = 17 * hash + Objects.hashCode(this.lastConnexion);
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
}