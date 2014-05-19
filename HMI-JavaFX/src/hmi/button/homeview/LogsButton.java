package hmi.button.homeview;

import hmi.button.CloudBookButton;
import hmi.content.monitor.MonitorView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.engine.Engine;
import model.monitoring.Monitoring;

public class LogsButton extends CloudBookButton{
    
    public LogsButton() {
        super("Monitor logs");
        
        this.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                Monitoring model = (Monitoring)Engine.INSTANCE.getMonitoring();
                MonitorView view = MonitorView.INSTANCE;
                view.logsTextProperty().bind(model.logsProperty());
                view.launch();
            }
        });
    }
}
