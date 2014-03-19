/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.node;

import java.io.Serializable;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Gwendal
 */
public class MySimpleObjectProperty<T> extends SimpleObjectProperty<T> implements Serializable {
    
        public MySimpleObjectProperty() {
            super();
        }

        public MySimpleObjectProperty(T t) {
            super(t);
        }

        public MySimpleObjectProperty(Object o, String string) {
            super(o, string);
        }

        public MySimpleObjectProperty(Object o, String string, T t) {
            super(o, string, t);
        }
}
