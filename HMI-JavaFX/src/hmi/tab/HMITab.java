/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.tab;

import hmi.content.HMIContent;
import javafx.scene.Parent;

/**
 *
 * @author Bruno
 */
public abstract class HMITab extends Parent {
    protected HMIContent content;
    
    public HMIContent getContent(){
        return content;
    }
}
