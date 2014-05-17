/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.register;

import hmi.Launcher;
import hmi.button.IconFlyWeight;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 *
 * @author Gwendal
 * icon and browse button to choose it
 */
public class IconArea extends VBox {
    
        //logo of the application
        protected ImageView logo;
        
        //"browse to choose a logo" button
        protected Button browse;
        
        //View port of the image
        protected static final Rectangle2D viewPort = new Rectangle2D(0, 0, 160, 160);
        
        /**
         * Constructor
         */
        public IconArea() {
            super();
            setAlignment(Pos.CENTER);
            setSpacing(30);
            getChildren().addAll(
                    getLogo(),
                    getBrowse()
            );
        }
        
        /**
         * getter
         * if logo is null, it is initialized
         * @return logo attribute 
         */
        public final ImageView getLogo() {
            if(logo == null) {
                logo = new ImageView(IconFlyWeight.INSTANCE.getDefaultLogo());
                this.logo.setViewport(viewPort);
            }
            return logo;
        }

        /**
         * setter
         * replaces the current logo by a new one
         * @param logo the logo to replace with
         */
        public void setLogo(ImageView logo) {
            if(this.logo != null)
                getChildren().remove(this.logo);
            this.logo = logo;
            this.logo.setViewport(viewPort);
            this.logo.resize(50, 80);
            getChildren().add(0, this.logo);
        }
        
        /**
         * getter
         * if browse is null, it is initialized
         * @return browse attribute 
         */
        public final Button getBrowse() {
            if(browse == null) {
                browse = new Button("Browse...");
                browse.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {
                        FileInputStream fis = null;
                        try {
                            fis = chooseFile();
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(RegisterView.class.getName()).log(Level.SEVERE, null, ex);
                        } catch(NullPointerException ex) {
                        } finally {
                            try {
                                fis.close();
                            } catch (IOException ex) {
                                Logger.getLogger(RegisterView.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    
                });
            }
            return browse;
        }
        
        /**
         * Open a file chooser dialog in order to choose a new logo
         * @return the input stream corresponding to the image file
         * @throws FileNotFoundException
         * @throws NullPointerException 
         */
        private FileInputStream chooseFile()
                throws FileNotFoundException, NullPointerException {
            FileChooser fc = new FileChooser();
            File f = fc.showOpenDialog(Launcher.STAGE);
            if(f == null) throw new NullPointerException();
            FileInputStream fis = new FileInputStream(f);
            Image i = new Image(fis);
            ImageView iv = new ImageView(i);
            setLogo(iv);
            return fis;
        }
}
