package modele.node;

import java.util.Date;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Bruno
 */
public class Friend implements Information {  
    private MySimpleIntegerProperty id = new MySimpleIntegerProperty();
    private MySimpleIntegerProperty confidence = new MySimpleIntegerProperty();
    private MySimpleBooleanProperty relevant = new MySimpleBooleanProperty();
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
}
