/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.node;

import java.util.ArrayList;
import java.util.Stack;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.network.interfaces.Information;

/**
 *
 * @author Gwendal
 * box containing some informations with string property describing the box content
 * @param <T> type of information
 */
public class InformationBox<T extends Information> implements Information {
    
    public static final String EMPTY_FLAG = "No information to display at this time";
    
    protected Stack<T> box; //box containing information
    protected String _description; //description of the box
    
    protected transient StringProperty description; //description property
    public StringProperty descriptionProperty() {
        return description;
    }

    /**
     * Constructor
     */
    public InformationBox() {
        box = new Stack<>();
        description = new SimpleStringProperty(EMPTY_FLAG);
    }
    
    /**
     * Adds information to the box and update the description
     * @param info information to add
     */
    public void push(T info) {
        box.push(info);
        description.set(info.toString());
    }
    
    /**
     * Pops information from the stack
     * @return top information
     */
    public T pop() {
        T info = box.pop();
        if(box.empty())
            description.set(EMPTY_FLAG);
        else
            description.set(box.peek().toString());
        return info;
    }
    
    /**
     * Removes specified information from the box
     * Description is set to the new peek if needed
     * @param info information to remove
     */
    public void remove(T info) {
        if(info == box.peek())
            this.pop();
        else {
            box.remove(info);
            if(box.isEmpty())
                description.set(EMPTY_FLAG);
        }
    }
    
    /**
     * Empty predicate
     * @return true if the box doesn't contains elements, false otherwise
     */
    public boolean empty() {
        return box.empty();
    }
    
    /**
     * Returns element at the given position in this box
     * @param index index of the element to return
     * @return element at the given position in this box
     */
    public T get(int index) {
        return box.get(index);
    }
    
    /**
     * Saves all properties of contained informations
     */
    @Override
    public void saveProperties() {
        for(Information info : box)
            info.saveProperties();
        _description = description.get();
    }

    /**
     * Restores all properties of contained informations
     */
    @Override
    public void restoreProperties() {
        for(Information info : box)
            info.restoreProperties();
        description = new SimpleStringProperty(_description);
    }
    
}
