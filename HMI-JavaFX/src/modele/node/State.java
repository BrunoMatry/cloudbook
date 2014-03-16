/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.node;

import java.util.Date;

/**
 *
 * @author Bruno
 */
public class State implements Information {
    private Cloud cloud;
    private Date from;
    private Date to;
    private boolean current;
    
    public State(Cloud c) {
        this.cloud = c;
        this.from = new Date();
        this.to = new Date();
        this.current = true;
    }
    
    public void notCurrentAnymore() {
        this.current = false;
        this.to = new Date();
    }
    
    public Cloud getCloud() {
        return cloud;
    }
}
