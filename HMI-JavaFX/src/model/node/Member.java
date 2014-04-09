package model.node;

import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Member implements Information {  
    
    /* Attributs serialisables */
    protected int _id;
    protected int _confidence;
    protected AppVector _vector;
    protected Date _lastConnexion;
    
    /* Proprietes non serialisables */
    protected transient IntegerProperty confidence = new SimpleIntegerProperty();
    protected transient ObjectProperty<Date> lastConnection;
    
    public Member(int friendId, int cnfdnce, boolean rlvnt, AppVector vector) {
        _id = friendId;
        confidence = new SimpleIntegerProperty(cnfdnce);
        _vector = vector;
        this.connexion();
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
        _lastConnexion = new Date();
    }
    
    /**
     * Calcul du nombre de jours écoulés depuis la dernière connection
     * @return  nombre de jours écoulés depuis la dernière connection de l'ami
     */
    public int daysSinceLastConnection() {
        //TODO : calcul du temps depuis la dernière connexion
        return 0;
    }
    
    public int getId() { return _id; }

    @Override
    public void saveProperties() {
        _confidence = confidence.get();
    }

    @Override
    public void restoreProperties() {
        confidence = new SimpleIntegerProperty(_confidence);
    }
    
}
