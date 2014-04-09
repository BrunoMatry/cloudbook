package model.node;

import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Member implements Information {  
    
    /* Attributs serialisables */
    protected int _id;
    protected int _confidence;
    protected AppVector _vector;
    protected Date _lastConnexion;
    
    /* Proprietes non serialisables */
    protected transient IntegerProperty confidence = new SimpleIntegerProperty();
    protected transient ObjectProperty<Date> lastConnexion;
    
    public Member(int friendId, int cnfdnce, boolean rlvnt, AppVector vector) {
        _id = friendId;
        _vector = vector;
        
        confidence = new SimpleIntegerProperty(cnfdnce);
        lastConnexion = new SimpleObjectProperty<>(new Date());
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
        _confidence = confidence.get();
        _lastConnexion = lastConnexion.get();
    }

    @Override
    public void restoreProperties() {
        confidence = new SimpleIntegerProperty(_confidence);
        lastConnexion = new SimpleObjectProperty<>(_lastConnexion);
    }
}