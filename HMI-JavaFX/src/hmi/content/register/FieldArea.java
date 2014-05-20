package hmi.content.register;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 * reserved for application settings
 */
public class FieldArea extends VBox {
    
        //name of the application
        private Field name;
        
        //host of the server
        private Field host;
        
        //port of the server
        private Field serverPort;
        
        //port of the application
        private Field nodePort;
        
        /**
         * Constructor
         */
        public FieldArea() {
            super();
            setAlignment(Pos.CENTER);
            setSpacing(30);
            getChildren().addAll(
                    getName(),
                    getNodePort(),
                    getHost(),
                    getServerPort()
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
            }
            return host;
        }

        /**
         * Getter
         * @return serverPort field
         */
        public final Field getServerPort() {
            if(serverPort == null) {
                serverPort = new Field("Enter the host port : ", "50000");
            }
            return serverPort;
        }
        
        /**
         * Getter
         * @return nodePort field 
         */
        public final Field getNodePort() {
            if(nodePort == null)
                nodePort = new Field("Enter your port : ", "50200");
            return nodePort;
        }
}
