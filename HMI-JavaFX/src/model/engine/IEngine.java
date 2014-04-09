package model.engine;

import java.io.IOException;
import model.network.interfaces.Information;
import model.network.interfaces.Sendable;
/**
 *
 * @author Bruno
 */
public interface IEngine {
    /**
     * Methode permettant de saisir une requete qui est renvoyee par le bloc reseau
     * @param req 
     */
    void handleRequest(Sendable req);
    void save() throws IOException;
    void setInformation(Information info);
}
