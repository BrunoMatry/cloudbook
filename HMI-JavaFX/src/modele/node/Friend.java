package modele.node;

import java.util.Date;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.friendmanager.Information;

/**
 *
 * @author Bruno
 */
public class Friend implements Information {  
    protected int _id;
    private transient IntegerProperty id = new SimpleIntegerProperty();
    
    protected int _confidence;
    private transient IntegerProperty confidence = new SimpleIntegerProperty();
    
    protected boolean _relevant;
    private transient BooleanProperty relevant = new SimpleBooleanProperty();
    
    private AppVector vector;
    private Date lastConnexion;
    
    public Friend(int id, int confidence, boolean relevant, AppVector vector) {
        this.id.set(id);
        this.confidence.set(confidence);
        this.relevant.set(relevant);
        this.vector = vector;
        this.connexion();
    }
    
    public void setConfidence(int confidence) {
        this.confidence.set(confidence);
        this.connexion();
    }
    
    public void setRelevance(boolean relevant) {
        this.relevant.set(relevant);
        this.connexion();
    }
    
    public void setVector(AppVector vector) {
        this.vector = vector;
        this.connexion();
    }
            
    private void connexion() {
        this.lastConnexion = new Date();
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
