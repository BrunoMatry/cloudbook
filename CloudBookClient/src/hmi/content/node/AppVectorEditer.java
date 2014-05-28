package hmi.content.node;

import hmi.Launcher;
import hmi.content.register.Field;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.layout.HBox;
import model.engine.Engine;
import model.node.AppVector;
import model.node.AppVectorBuilder;
import model.node.FileEngineRelation;
import model.node.MyNode;

/**
 * Graphic chart which allow to modify a vector of features.
 * @author Gwendal
 */
public class AppVectorEditer extends HBox {
    
    private final Field type;
    private final Field perf;
    private final Field speed;
    private Button apply;
    private AppVectorBuilder builder;
    
    /**
     * Type of the application.
     * @return Type of the application.
     */
    public StringProperty typeProperty() {
        return type.getHint().textProperty();
    }
    
    /**
     * Performance need of the application.
     * @return Performance need of the application.
     */
    public StringProperty perfProperty() {
        return perf.getHint().textProperty();
    }
    
    /**
     * Speed need of the application.
     * @return Speed need of the application.
     */
    public StringProperty speedProperty() {
        return speed.getHint().textProperty();
    }
    
    /**
     * Constructor
     * @param type application type
     * @param perf performance need
     * @param speed speed need 
     */
    public AppVectorEditer(String type, String perf, String speed) {
        this.type = new Field("Application type : ", type);
        this.perf = new Field("Performance need : ", perf);
        this.speed = new Field("Speed need : ", speed);
        setUpApply();
        getChildren().addAll(this.type, this.perf, this.speed, this.apply);
    }
    
    /**
     * Sets up the validation button (title and action).
     */
    private void setUpApply() {
        apply = new Button("Apply");
        apply.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                AppVector vector = builder.build();
                Engine engine = FileEngineRelation.INSTANCE.getCurrentEngine();
                MyNode node = engine.getNode();
                node.setVector(vector);
                Dialogs.showInformationDialog(Launcher.STAGE, "Vector edited with success : vector = " + node.getVector(), "OK");
            }
        });
    }

    /**
     * Setter
     * @param builder of the vector.
     */
    public void setBuilder(AppVectorBuilder builder) {
        this.builder = builder;
    }
    
}
