/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.register;

import hmi.Launcher;
import hmi.button.IconFlyWeight;
import hmi.content.AActivity;
import hmi.content.Activity;
import hmi.home.HomeView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * View letting the user register an application
 * singleton
 * @author Gwendal
 */
public final class RegisterView extends Activity {
    
    public static final RegisterView INSTANCE = new RegisterView(HomeView.INSTANCE);
    
    private MyVBox vBox;
    
    private RegisterView(AActivity p) {
        super(p);
        title = "Register your application";
        vBox = new MyVBox();
        setCenter(vBox);
    }
    
    /**
     * VBox containing the components of necessary to the user for registering
     */
    public class MyVBox extends VBox {
        
        private ImageView logo;
        private Button browse;
        private TextField name;
        private ChoiceBox clouds;
        private TableView mesures;
        
        public MyVBox() {
            super();
            setAlignment(Pos.CENTER);
            setSpacing(50);
            getChildren().addAll(
                    getLogo(),
                    getBrowse(),
                    getName(),
                    getClouds()
            );
        }
        
        public final ImageView getLogo() {
            if(logo == null) {
                logo = new ImageView(IconFlyWeight.INSTANCE.getDefaultLogo());
            }
            return logo;
        }

        public void setLogo(ImageView logo) {
            if(this.logo != null)
                getChildren().remove(this.logo);
            this.logo = logo;
            this.logo.setViewport(new Rectangle2D(0, 0, 80, 80));
            this.logo.resize(50, 80);
            getChildren().add(0, this.logo);
        }

        public final ChoiceBox getClouds() {
            if(clouds == null) {
                clouds = new ChoiceBox();
            }
            return clouds;
        }

        public final Button getBrowse() {
            if(browse == null) {
                browse = new Button("Browse...");
                browse.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                public void handle(ActionEvent t) {
                    FileInputStream fis = null;
                        try {
                            FileChooser fc = new FileChooser();
                            File f = fc.showOpenDialog(Launcher.STAGE);
                            fis = new FileInputStream(f);
                            Image i = new Image(fis);
                            ImageView iv = new ImageView(i);
                            MyVBox parent = (MyVBox)browse.getParent();
                            parent.setLogo(iv);
                            
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

        public final TextField getName() {
            if(name == null) {
                name = new TextField("App name");
                setMargin(name, new Insets(0, 200, 0, 200));
            }
            return name;
        }
        
    }
 }
