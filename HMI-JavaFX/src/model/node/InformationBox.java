/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.node;

import java.util.List;
import javafx.beans.property.StringProperty;
import model.network.interfaces.Information;

/**
 *
 * @author Gwendal
 * box containing some informations with string property describing the box content
 * @param <T> type of information
 */
public class InformationBox<T extends Information> {
    
    protected List<T> box; //box containing information
    protected String _description; //description of the box
    protected StringProperty description; //description property
    
    
}
