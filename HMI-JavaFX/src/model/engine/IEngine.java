package model.engine;

import java.io.IOException;
import model.node.Information;
import model.request.Request;
/**
 *
 * @author Bruno
 */
public interface IEngine {
    /**
     * Methode permettant de saisir une requete qui est renvoyee par le bloc reseau
     * @param req 
     */
    void handleRequest(Request req);
    void save() throws IOException;
    void setInformation(Information info);
}
