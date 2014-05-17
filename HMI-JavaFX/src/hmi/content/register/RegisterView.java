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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.node.Cloud;
import model.node.CloudBuilder;

/**
 * View letting the user register an application
 * @author Gwendal
 */
public class RegisterView extends Activity {
    
    //vertical box containing all the identificator components
    protected MyVBox vBox;
    
    //constructing object
    protected CloudBuilder builder;

    /**
     * Getter
     * @return builder field
     */
    public CloudBuilder getBuilder() {
        return builder;
    }

    /**
     * Setter
     * @param builder builder field
     */
    public void setBuilder(CloudBuilder builder) {
        this.builder = builder;
    }
    
    /**
     * initialize the vertical box
     * @param p previous Activity
     */
    public RegisterView(AActivity p) {
        super(p);
        title = "Register your application";
        vBox = new MyVBox();
        setCenter(vBox);
    }

    public MyVBox getvBox() {
        return vBox;
    }
    
    public ObjectProperty<Image> logoProperty() {
        return vBox.logo.imageProperty();
    }
    
    public StringProperty nameProperty() {
        return vBox.name.hint.textProperty();
    }
    
    public StringProperty hostProperty() {
        return vBox.host.hint.textProperty();
    }
    
    public StringProperty portProperty() {
        return vBox.port.hint.textProperty();
    }
    
    public ObjectProperty<Cloud> cloudProperty() {
        return vBox.clouds.valueProperty();
    }
    
    /**
     * VBox containing the components necessary to the user for registration
     */
    public class MyVBox extends VBox {
        
        /**
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
        
        //logo of the application
        private ImageView logo;
        
        //"browse to choose a logo" button
        private Button browse;
        
        //name of the application
        private Field name;
        
        //host of the server
        private Field host;
        
        //port of the server
        private Field port;
        
        //selector : current cloud platform of the application
        //TODO : transform in independant activity
        private ChoiceBox<Cloud> clouds;
        
        //ok button
        private Button ok;
        
        /**
         * initialize and places the components
         * inside the current vertical box
         */
        public MyVBox() {
            super();
            setAlignment(Pos.CENTER);
            setSpacing(30);
            getChildren().addAll(
                    getLogo(),
                    getBrowse(),
                    getName(),
                    getHost(),
                    getPort(),
                    getClouds(),
                    getOk()
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
            this.logo.setViewport(new Rectangle2D(0, 0, 160, 160));
            this.logo.resize(50, 80);
            getChildren().add(0, this.logo);
        }

        /**
         * getter
         * if clouds is null, it is initialized
         * @return clouds attribute 
         */
        public final ChoiceBox getClouds() {
            if(clouds == null) {
                clouds = new ChoiceBox<Cloud>();
                clouds.setSelectionModel(new SingleSelectionModel<Cloud>(){

                    @Override
                    protected Cloud getModelItem(int i) {
                        return Cloud.values()[i];
                    }

                    @Override
                    protected int getItemCount() {
                        return Cloud.values().length;
                    }
                    
                });
                for(Cloud c : Cloud.values())
                    clouds.getItems().add(c);
                clouds.setValue(Cloud.DEFAULT);
            }
            return clouds;
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
            MyVBox parent = (MyVBox)browse.getParent();
            parent.setLogo(iv);
            return fis;
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

        public final Button getOk() {
            if(ok == null) {
                ok = new Button("Ok");
                ok.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {
                        try {
                            builder.build();
                            Dialogs.showInformationDialog(Launcher.STAGE, "Success build", "OK");
                        } catch (IOException ex) {
                            Dialogs.showErrorDialog(Launcher.STAGE, "Error while building", "Error");
                        }
                    }
                    
                });
            }
            return ok;
        }

        public final Field getHost() {
            if(host == null){
                host = new Field("Enter the host name : ", "host");
                setMargin(host, new Insets(0, 200, 0, 200));
            }
            return host;
        }

        public final Field getPort() {
            if(port == null) {
                port = new Field("Enter the host port : ", "50100");
                setMargin(host, new Insets(0, 200, 0, 200));
            }
            return port;
        }
        
    }
 }
