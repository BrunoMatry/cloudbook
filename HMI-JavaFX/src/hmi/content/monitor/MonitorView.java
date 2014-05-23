package hmi.content.monitor;

import hmi.content.AbstractActivity;
import hmi.content.Activity;
import hmi.home.NodeList;
import javafx.beans.property.StringProperty;
import javafx.scene.text.Text;

public final class MonitorView extends Activity {
    
    //unique instance
    public static final MonitorView INSTANCE = new MonitorView(NodeList.INSTANCE);
    
    protected final Text logs;
    
    public StringProperty logsTextProperty() {
        return logs.textProperty();
    }

    /**
     * Constructor
     * @param p parent activity
     */
    private MonitorView(AbstractActivity p) {
        super(p);
        title = "Monitor logs";
        logs = new Text("MonitorView");
        setCenter(logs);
    }
    
}
