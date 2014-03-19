/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.node;

import java.io.Serializable;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author Gwendal
 */
public class MySimpleBooleanProperty extends SimpleBooleanProperty implements Serializable {

    public MySimpleBooleanProperty() {
        super();
    }

    public MySimpleBooleanProperty(boolean bln) {
        super(bln);
    }

    public MySimpleBooleanProperty(Object o, String string) {
        super(o, string);
    }

    public MySimpleBooleanProperty(Object o, String string, boolean bln) {
        super(o, string, bln);
    }
    
}
