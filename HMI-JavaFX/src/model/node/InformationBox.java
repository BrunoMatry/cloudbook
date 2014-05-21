package model.node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.network.interfaces.Information;

/**
 * box containing some informations with string property describing the box content
 * @param <T> type of information
 */
public class InformationBox<T extends Information> implements Information, Iterable<T> {
    
    public static final String EMPTY_FLAG = "No information to display at this time";
    
    protected List<T> _box; //box containing information
    protected transient ObservableList<T> box; //box containing information
    public final ObservableList<T> boxObservableList() {
        return box;
    }
    
    protected String _description; //description of the box
    
    protected transient StringProperty description; //description property
    public StringProperty descriptionProperty() {
        return description;
    }

    /**
     * Constructor
     */
    public InformationBox() {
        box = FXCollections.observableArrayList();
        description = new SimpleStringProperty(EMPTY_FLAG);
    }
    
    /**
     * Adds information to the box and update the description
     * @param info information to add
     */
    public void push(T info) {
        box.add(info);
        description.set(info.toString());
    }
    
    /**
     * Pops information from the stack
     * @return top information
     */
    public T pop() {
        int lastIndex = box.size() - 1;
        T info = box.remove(lastIndex);
        if(box.isEmpty())
            description.set(EMPTY_FLAG);
        else
            description.set(box.get(lastIndex-1).toString());
        return info;
    }
    
    /**
     * Removes specified information from the box
     * Description is set to the new peek if needed
     * @param info information to remove
     */
    public void remove(T info) {
        int lastIndex = box.size() - 1;
        if(info == box.get(lastIndex))
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
        return box.isEmpty();
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
        _box = new ArrayList<>();
        for(Information info : box) {
            info.saveProperties();
            _box.add((T)info);
        }
        _description = description.get();
    }

    /**
     * Restores all properties of contained informations
     */
    @Override
    public void restoreProperties() {
        box = FXCollections.observableArrayList();
        for(Information info : _box) {
           info.restoreProperties();
           box.add((T)info);
        }  
        description = new SimpleStringProperty(_description);
    }
    
    /**
     * Get the last nb values from the InformationBox
     * @param nb number of values wanted
     * @return List of nb values
     */
    public ArrayList<T> getLastValues(int nb) {
        ArrayList<T> tab = new ArrayList<>();
        for(int i = (box.size() - nb) ; i < box.size() ; i++) {
            tab.add(box.get(i));
        }
        return tab;
    }

    @Override
    public Iterator<T> iterator() {
        return box.iterator();
    }
    
    /**
     * Computes the number of elements of the box
     * @return the number of elements of the box
     */
    public int size() {
        return box.size();
    }

}
