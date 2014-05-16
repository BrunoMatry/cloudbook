/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.engine;

import hmi.content.AActivity;
import hmi.content.Activity;
import hmi.home.HomeView;

/**
 *
 * @author Gwendal
 * singleton
 */
public final class EngineView extends Activity {
    
    //unique instance
    public static final EngineView INSTANCE = new EngineView(HomeView.INSTANCE);

    /**
     * Constructor
     * @param p parent activity
     */
    private EngineView(AActivity p) {
        super(p);
    }
    
}
