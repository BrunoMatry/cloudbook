/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.Observable;

/**
 *
 * @author Gwendal
 */
public class OpenObservable extends Observable {

    public OpenObservable() {
        super();
    }
    
    @Override
    public synchronized void setChanged() {
        super.setChanged();
    }
     
}
