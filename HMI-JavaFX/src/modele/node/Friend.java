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
public class Friend implements Information {
    private int confidence;
    private int id;
    private Date lastConnexion;
    private boolean relevant;
    private AppVector vector;
    
}
