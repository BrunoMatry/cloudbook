package model.engine;

import java.io.IOException;
import model.friendmanager.Information;
import model.request.Request;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
