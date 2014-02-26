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
public class Message implements Information {
    private Information content;
    private Date date;
    private int idSender;
    private boolean relevant;
    private AppVector vector;
}
