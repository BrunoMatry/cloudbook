/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.node;

import java.io.Serializable;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Gwendal
 */
public class MySimpleIntegerProperty extends SimpleIntegerProperty implements Serializable {

    public MySimpleIntegerProperty() {
        super();
    }

    public MySimpleIntegerProperty(int i) {
        super(i);
    }

    public MySimpleIntegerProperty(Object o, String string) {
        super(o, string);
    }

    public MySimpleIntegerProperty(Object o, String string, int i) {
        super(o, string, i);
    }
    
}
