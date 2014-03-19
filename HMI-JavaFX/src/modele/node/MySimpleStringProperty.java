/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.node;

import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Gwendal
 */
public class MySimpleStringProperty extends SimpleStringProperty implements Serializable {
    
        public MySimpleStringProperty() {
            super();
        }

        public MySimpleStringProperty(String string) {
            super(string);
        }

        public MySimpleStringProperty(Object o, String string) {
            super(o, string);
        }

        public MySimpleStringProperty(Object o, String string, String string1) {
            super(o, string, string1);
        }
}
