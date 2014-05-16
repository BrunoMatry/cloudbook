/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.engine;

import hmi.content.AActivity;
import hmi.content.Activity;
import hmi.home.HomeView;
import javafx.beans.property.StringProperty;
import javafx.scene.text.Text;

/**
 *
 * @author Gwendal
 * singleton
 */
public final class MonitorView extends Activity {
    
    //unique instance
    public static final MonitorView INSTANCE = new MonitorView(HomeView.INSTANCE);
    
    private Text logs;
    public StringProperty logsTextProperty() {
        return logs.textProperty();
    }

    /**
     * Constructor
     * @param p parent activity
     */
    private MonitorView(AActivity p) {
        super(p);
        logs = new Text();
    }
    
}
