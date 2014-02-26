/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.request;

import java.util.Date;
import modele.node.Information;

/**
 *
 * @author Bruno
 */
public class Request<Inf extends Information> implements Sendable {
    private Sender sender;
    private Date date;
    private Inf info;
    private int rebouds;
    private int recipent;
}
