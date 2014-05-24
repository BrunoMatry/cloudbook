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
import model.node.Cloud;

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
    protected transient ObjectProperty<AppVector> appVector;
    private transient ObjectProperty<Cloud> cloud;
    
    /**
     * Constructor
     * @param friendId identifier of the friend application
     * @param cnfdnce confidence value
     * @param rlvnce relevance value
     * @param vector vector of characteriqtics of the member application
     * @param _cloud cloud on which this friend runs
     * @throws NullPointerException 
     */
    public Friend(String friendId, int cnfdnce, double rlvnce, AppVector vector, Cloud _cloud) throws NullPointerException {
        super(friendId, vector, _cloud);
        if(vector == null)
            throw new NullPointerException();
        id = new SimpleStringProperty(friendId);
        relevance = new RelevanceProperty(this, rlvnce);
        confidence = new ConfidenceProperty(this, cnfdnce);
        lastConnection = new SimpleObjectProperty<>(new Date());
        appVector = new SimpleObjectProperty<>(vector);
        cloud = new SimpleObjectProperty<>(_cloud);
    }

    @Override
    public String getId() {
        return id.get();
    }

    @Override
    public Cloud getCloud() {
        return cloud.get();
    }
    
    /**
     * Setter
     * @param vector vector attribute
     */
    public void setVector(AppVector vector) {
        this.appVector.set(vector);
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
    public AppVector getVector() { return appVector.get(); }
    public DoubleProperty relevanceProperty() { return relevance; }
    public IntegerProperty confidenceProperty() { return confidence; }
    public ObjectProperty lastConnectionProperty() { return lastConnection; }
    public ObjectProperty appVectorProperty() { return appVector; }
    public ObjectProperty<Cloud> cloudProperty() { return cloud; }

    @Override
    public void saveProperties() {
        _id = id.get();
        _relevance =  relevance.get();
        _confidence = confidence.get();
        _lastConnection = lastConnection.get();
        vector = appVector.get();
        _cloud = cloud.get();
    }

    @Override
    public void restoreProperties() {
        id = new SimpleStringProperty(_id);
        relevance = new RelevanceProperty(this, _relevance);
        confidence = new ConfidenceProperty(this, _confidence);
        lastConnection = new SimpleObjectProperty<>(_lastConnection);
        appVector = new SimpleObjectProperty<>(vector);
        cloud = new SimpleObjectProperty<>(_cloud);
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