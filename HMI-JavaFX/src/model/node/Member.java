package model.node;

import model.network.interfaces.Information;
import java.util.Date;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Member implements Information {  
    
    /* Attributs serialisables */
    protected int _id;
    protected double _relevance;
    protected int _confidence;
    protected AppVector _vector;
    protected Date _lastConnexion;
    
    /* Proprietes non serialisables */
    protected transient DoubleProperty relevance = new SimpleDoubleProperty();
    protected transient IntegerProperty confidence = new SimpleIntegerProperty();
    protected transient ObjectProperty<Date> lastConnexion;
    
    public Member(int friendId, int cnfdnce, double rlvnce, AppVector vector) {
        _id = friendId;
        _vector = vector;
        relevance = new SimpleDoubleProperty(rlvnce);
        confidence = new SimpleIntegerProperty(cnfdnce);
        lastConnexion = new SimpleObjectProperty<>(new Date());
    }
    
     public void setRelevance(double rlvnce) {
        relevance.set(rlvnce);
        connexion();
    }
     
    public void setConfidence(int cnfdnce) {
        confidence.set(cnfdnce);
        connexion();
    }
    
    public void setVector(AppVector vector) {
        _vector = vector;
        connexion();
    }
            
    private void connexion() {
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
    
    public int getId() { return _id; }

    @Override
    public void saveProperties() {
        _relevance =  relevance.get();
        _confidence = confidence.get();
        _lastConnexion = lastConnexion.get();
    }

    @Override
    public void restoreProperties() {
        relevance = new SimpleDoubleProperty(_relevance);
        confidence = new SimpleIntegerProperty(_confidence);
        lastConnexion = new SimpleObjectProperty<>(_lastConnexion);
    }
}