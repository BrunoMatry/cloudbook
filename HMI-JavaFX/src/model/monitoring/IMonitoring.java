package model.monitoring;

/**
 * @author Bruno
 */
public interface IMonitoring {
    /**
     * Methode permettant la mise a jour du noeud courant avec les mesures du bloc mesure
     */
    void pushInformation(); 
}
