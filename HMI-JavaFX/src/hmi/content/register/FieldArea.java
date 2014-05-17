/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.register;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import static javafx.scene.layout.VBox.setMargin;

/**
 *
 * @author Gwendal
 * reserved for application settings
 */
public class FieldArea extends VBox {
    
        //name of the application
        private Field name;
        
        //host of the server
        private Field host;
        
        //port of the server
        private Field port;
        
        public FieldArea() {
            super();
            setAlignment(Pos.CENTER);
            setSpacing(30);
            getChildren().addAll(
                    getName(),
                    getHost(),
                    getPort()
            );
        }
        
        /**
         * getter
         * if name is null, it is initialized
         * @return name attribute 
         */
        public final Field getName() {
            if(name == null) {
                name = new Field("Enter the name of your application : ", "App name");
                setMargin(name, new Insets(0, 200, 0, 200));
            }
            return name;
        }
        
        /**
         * Getter
         * @return host field
         */
        public final Field getHost() {
            if(host == null){
                host = new Field("Enter the host name : ", "host");
                setMargin(host, new Insets(0, 200, 0, 200));
            }
            return host;
        }

        /**
         * Getter
         * @return port field
         */
        public final Field getPort() {
            if(port == null) {
                port = new Field("Enter the host port : ", "50100");
                setMargin(host, new Insets(0, 200, 0, 200));
            }
            return port;
        }
}
