package hmi.button;

import hmi.content.monitor.MonitorView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.engine.Engine;
import model.monitoring.Monitoring;
import model.node.FileEngineRelation;

public class LogsButton extends MyButton{
    
    public LogsButton() {
        super("Monitor logs");
        
        this.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                /*TODO faire l'affichage d'autre chose !!!! */
                // InformationBox mesures = Engine.INSTANCE.getNode().getMesures();
                Engine engine = FileEngineRelation.INSTANCE.getCurrentEngine();
                Monitoring model = (Monitoring)engine.getMonitoring();
                MonitorView view = MonitorView.INSTANCE;
                view.logsTextProperty().bind(model.logsProperty());
                view.launch();
            }
        });
    }
}
