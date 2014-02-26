/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.monitoring;

/**
 *
 * @author Bruno
 */
public interface IMonitoring {
    /**
     * Methode permettant la mise a jour du noeud courant avec les mesures du bloc mesure
     */
    void pushInformation();
}
