package hmi.content;

import hmi.button.BackButton;
import hmi.button.HomeButton;
import hmi.home.NodeList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;

/**
 * specifies the basic content of a standard activity (i.e not the starting activity)
 */
public class Activity extends AActivity {
    
    //horizontal container of the back and home buttons
    protected MenuHBox mHBox;
    
    //activity that must be launch if the back button is pressed
    protected AActivity prec;
    
    /**
     * initialize the menu box (filled with back and home buttons)
     * @param p previous Activity
     */
    public Activity(AActivity p) {
        super();
        prec = p;
        mHBox = new MenuHBox();
        setTop(mHBox);
    }
    
    /**
     * Defines the content of the menu box
     */
    public class MenuHBox extends HBox {
        
        //"go to the previous page" button
        private BackButton goBack;
        
        //"go to the home-page" button
        private HomeButton goHome;
    
        /**
         * initialize and places the components
         * inside the current horizontal box
         */
        public MenuHBox() {
            super();
            getChildren().addAll(
                    getGoBack(),
                    getGoHome()
            );
        }
        
        /**
         * getter
         * if goBack is null, it is initialized
         * @return goBack attribute
         */
        public final BackButton getGoBack() {
            if(goBack == null) {
                goBack = new BackButton();
                goBack.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {
                        prec.launch();
                    }
        
                });
            }
            return goBack;
        }
        
        /**
         * getter
         * if goHome is null, it is initialized
         * @return goHome attribute
         */
        public final HomeButton getGoHome() {
            if(goHome == null) {
                goHome = new HomeButton();
                goHome.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {
                        NodeList.INSTANCE.launch();
                    }
        
                });
            }
            return goHome;
        }
    }
}
