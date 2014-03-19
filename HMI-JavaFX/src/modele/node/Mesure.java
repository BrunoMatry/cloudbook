/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.node;

import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Bruno
 */
public class Mesure implements Information {
    //contenu
    private Date date;
    private final MySimpleStringProperty stringDate = new MySimpleStringProperty();
    private MySimpleIntegerProperty mesure1 = new MySimpleIntegerProperty();
    public MySimpleIntegerProperty mesure1Property() {
        return mesure1;
    }
    private MySimpleIntegerProperty mesure2 = new MySimpleIntegerProperty();
    private MySimpleIntegerProperty mesure3 = new MySimpleIntegerProperty();
    private MySimpleIntegerProperty mesure4 = new MySimpleIntegerProperty();
    private MySimpleIntegerProperty mesure5 = new MySimpleIntegerProperty();
    
    protected MySimpleStringProperty description;
    public StringProperty descriptionProperty() {
        return description;
    }
    
    public Mesure() {
        description = new MySimpleStringProperty();
        description.set("Pas de mesure");
    }
    
    public Mesure(int mesure1, int mesure2, int mesure3, int mesure4, int mesure5) {
        description = new MySimpleStringProperty("" + mesure1);
        this.actualizeDate();
        this.mesure1.set(mesure1);
        this.mesure2.set(mesure2);
        this.mesure3.set(mesure3);
        this.mesure4.set(mesure4);
        this.mesure5.set(mesure5);
    }
    
    private void actualizeDate() {
        this.date = new Date();
        this.stringDate.set(this.date.toString());
    }
}
