/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.nodeview;

/**
 *
 * @author Gwendal
 * 
 * methods shared by all customized graphical components
 */
public interface ComponentView {
    
    /**
     * display the component
     */
    public void display();
    
    /**
     * hide the component
     */
    public void hide();
    
    /**
     * update the component
     */
    public void update();
}
