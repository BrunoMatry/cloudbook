package hmi.content.register;

import hmi.Launcher;
import hmi.button.IconFlyWeight;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import static javafx.scene.layout.VBox.setMargin;
import javafx.stage.FileChooser;

/**
 * icon and browse button to choose it
 */
public class IconArea extends BorderPane {
    
        //logo of the application
        protected ImageView logo;
        
        //"browse to choose a logo" button
        protected Button browse;
        
        //File containing the chosen image
        private final ObjectProperty<File> imageFile;
        
        //View port of the image
        protected static final Rectangle2D viewPort = new Rectangle2D(0, 0, 120, 120);
        
        /**
         * Constructor
         */
        public IconArea() {
            super();
            File f = new File("./res/default_app.png");
            imageFile = new SimpleObjectProperty<>(f);
            setCenter(getLogo());
            setBottom(getBrowse());
            setWidth(100);
        }
        
        /**
         * getter
         * if logo is null, it is initialized
         * @return logo attribute 
         */
        public final ImageView getLogo() {
            if(logo == null) {
                logo = new ImageView(IconFlyWeight.INSTANCE.getDefaultLogo());
                setMargin(this.logo, new Insets(10, 10, 10, 10));
                setAlignment(logo, Pos.CENTER);
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
            setMargin(this.logo, new Insets(10, 10, 10, 10));
            this.logo.resize(128, 128);
            this.logo.setViewport(new Rectangle2D(0, 0, this.logo.getImage().getHeight(),
                    this.logo.getImage().getWidth()));
            setCenter(this.logo);
        }
        
        /**
         * getter
         * if browse is null, it is initialized
         * @return browse attribute 
         */
        public final Button getBrowse() {
            if(browse == null) {
                browse = new Button("Browse...");
                setMargin(browse, new Insets(0, 0, 100, 0));
                setAlignment(browse, Pos.CENTER);
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
            imageFile.set(fc.showOpenDialog(Launcher.STAGE));
            if(imageFile == null) throw new NullPointerException();
            FileInputStream fis = new FileInputStream(imageFile.get());
            Image i = new Image(fis);
            ImageView iv = new ImageView(i);
            setLogo(iv);
            return fis;
        }

        /**
         * Getter
         * @return file contaning the chosen image
         */
    public ObjectProperty<File> imageFileProperty() {
        return imageFile;
    }
        
}
