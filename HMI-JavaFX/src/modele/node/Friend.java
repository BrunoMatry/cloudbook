package modele.node;

import java.util.Date;

/**
 *
 * @author Bruno
 */
public class Friend implements Information {  
    private int id;
    private int confidence;
    private boolean relevant;
    private AppVector vector;
    private Date lastConnexion;
    
    public Friend(int id, int confidence, boolean relevant, AppVector vector) {
        this.id = id;
        this.confidence = confidence;
        this.relevant = relevant;
        this.vector = vector;
        this.connexion();
    }
    
    public void setConfidence(int confidence) {
        this.confidence = confidence;
        this.connexion();
    }
    
    public void setRelevance(boolean relevant) {
        this.relevant = relevant;
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
