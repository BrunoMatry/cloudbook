/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.node;

import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Bruno
 */
public class State implements Information {
    private Cloud cloud;
    private Date from;
    private Date to;
    
    public State() {
        cloud = Cloud.DROPBOX;
    }
}
