/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.register;

import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.layout.HBox;
import model.node.Cloud;

/**
 *
 * @author Gwendal
 */
public class SettingsArea extends HBox {
    
    //icon of the application with browse button
    protected IconArea iconArea;
    
    //fields defining the app name and the host location and port
    protected FieldArea fieldArea;
    
    //selector : current cloud platform of the application
    protected ChoiceBox<Cloud> clouds;
    
    /**
     * Constructor
     */
    public SettingsArea() {
        super();
        setAlignment(Pos.CENTER);
        setSpacing(50);
        this.iconArea = new IconArea();
        this.fieldArea = new FieldArea();
        getChildren().addAll(
                this.iconArea,
                this.fieldArea,
                getClouds()
        );
    }

    public final IconArea getIconArea() {
        return iconArea;
    }

    public final FieldArea getFieldArea() {
        return fieldArea;
    }
    
    /**
     * getter
     * if clouds is null, it is initialized
     * @return clouds attribute 
     */
     public final ChoiceBox getClouds() {
            if(clouds == null) {
                clouds = new ChoiceBox<>();
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
}
