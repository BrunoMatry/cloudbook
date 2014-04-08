package model.node;

import java.util.Date;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Friend implements Information {  
    
    /* Attributs serialisables */
    protected int _id;
    protected int _confidence;
    protected boolean _relevant; //TODO : A supprimer car si il est dans la liste d'amis, c'est qu'il est pertinent (enlever les lignes marqués RLV)
    protected AppVector _vector;
    protected Date _lastConnexion;
    
    /* Proprietes non serialisables */
    protected transient IntegerProperty confidence = new SimpleIntegerProperty();
    protected transient BooleanProperty relevant; //RLV
    
    public Friend(int friendId, int cnfdnce, boolean rlvnt, AppVector vector) {
        _id = friendId;
        confidence = new SimpleIntegerProperty(cnfdnce);
        relevant  = new SimpleBooleanProperty(rlvnt); //RLV
        _vector = vector;
        this.connexion();
    }
    
    public void setConfidence(int cnfdnce) {
        confidence.set(cnfdnce);
        connexion();
    }
    
    public void setRelevance(boolean rlvnt) { //RLV
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
        _relevant = relevant.get(); //RLV
        _confidence = confidence.get();
    }

    @Override
    public void restoreProperties() {
        relevant = new SimpleBooleanProperty(_relevant); //RLV
        confidence = new SimpleIntegerProperty(_confidence);
    }
    
}
