/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.register;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 *
 * @author Gwendal
 * Defines a field with provided information about it's purpose
 */
public class Field extends HBox {
    
    //information about the purpose of the field
    private Text information;
            
    //Text to be entered
    private TextField hint;
            
            /**
             * Constructor
             * @param information information field content
             * @param defaultHint hint field content
             */
            public Field(String information, String defaultHint) {
                super();
                this.information = new Text(information);
                this.hint = new TextField(defaultHint);
                setAlignment(Pos.CENTER);
                setSpacing(10);
                getChildren().addAll(
                        this.information,
                        this.hint
                );
            }

            /**
             * Getter
             * @return information field 
             */
            public Text getInformation() {
                return information;
            }

            /**
             * Getter
             * @return hint field
             */
            public TextField getHint() {
                return hint;
            }
            
        }
