package hmi.content.register;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 * reserved for application settings
 */
public class FieldArea extends VBox {
    
        //name of the application
        private Field name;
        
        //port of the application
        private Field nodePort;
        
        //type of the application
        private Field appType;
        
        //performance value (integer)
        private Field performance;
        
        //speed value (integer)
        private Field speed;
        
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
                    getAppType(),
                    getPerformance(),
                    getSpeed()
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
         * @return nodePort field 
         */
        public final Field getNodePort() {
            if(nodePort == null)
                nodePort = new Field("Enter your port : ", "50200");
            return nodePort;
        }
        
        /**
         * Getter
         * @return appType field 
         */
        public final Field getAppType() {
            if(appType == null)
                appType = new Field("Enter the application type : ", "0");
            return appType;
        }
        
        /**
         * Getter
         * @return performance field 
         */
        public final Field getPerformance() {
            if(performance == null)
                performance = new Field("Enter the performance constraint : ", "0");
            return performance;
        }
        
        /**
         * Getter
         * @return speed field 
         */
        public final Field getSpeed() {
            if(speed == null)
                speed = new Field("Enter the speed constraint : ", "0");
            return speed;
        }
}
