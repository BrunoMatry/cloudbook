/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.tab;

import hmi.content.Content;
import javafx.scene.Parent;

/**
 *
 * @author Bruno
 */
public abstract class Tab extends Parent {
    protected Content content;
    
    public Content getContent(){
        return content;
    }
}
