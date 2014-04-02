package modele.node;

import java.util.Date;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.friendmanager.Information;

public class Friend implements Information {  
    
    /* Attributs serialisables */
    protected int _id;
    protected int _confidence;
    protected boolean _relevant;
    protected AppVector _vector;
    protected Date _lastConnexion;
    
    /* Proprietes non serialisables */
    protected transient IntegerProperty id = new SimpleIntegerProperty();
    protected transient IntegerProperty confidence = new SimpleIntegerProperty();
    protected transient BooleanProperty relevant;
    
    public Friend(int friendId, int cnfdnce, boolean rlvnt, AppVector vector) {
        id = new SimpleIntegerProperty(friendId);
        confidence = new SimpleIntegerProperty(cnfdnce);
        relevant  = new SimpleBooleanProperty(rlvnt);
        _vector = vector;
        this.connexion();
    }
    
    public void setConfidence(int cnfdnce) {
        confidence.set(cnfdnce);
        connexion();
    }
    
    public void setRelevance(boolean rlvnt) {
        relevant.set(rlvnt);
        connexion();
    }
    
    public void setVector(AppVector vector) {
        _vector = vector;
        connexion();
    }
            
    private void connexion() {
        _lastConnexion = new Date();
    }

    @Override
    public void saveProperties() {
        _id = id.get();
        _relevant = relevant.get();
        _confidence = confidence.get();
    }

    @Override
    public void restoreProperties() {
        id = new SimpleIntegerProperty(_id);
        relevant = new SimpleBooleanProperty(_relevant);
        confidence = new SimpleIntegerProperty(_confidence);
    }
}
