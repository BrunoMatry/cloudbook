package controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import model.node.AppVector;

public class AppVectorStringRelation extends SimpleObjectProperty<AppVector> {

    //view side of the relation
    private final StringProperty text;
    
    public AppVectorStringRelation() {
        super();
        this.text = new SimpleStringProperty();
    }

    public AppVectorStringRelation(AppVector m) {
        super(m);
        this.text = new SimpleStringProperty(m.toString());
    }
    
    @Override
    public void set(AppVector m) {
        super.set(m);
        this.text.set(m.toString());
    }

    @Override
    public void bind(ObservableValue<? extends AppVector> ov) {
        super.bind(ov);
        AppVector vect = ov.getValue();
        this.text.set(vect.toString());
    }
    
    /**
     * Makes a specified text property be bound to the view side of the relation
     * @param textProperty image Property to bind with
     */
    public void drive(StringProperty textProperty) {
        textProperty.bind(text);
    }
}