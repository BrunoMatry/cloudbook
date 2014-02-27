/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.register;

import hmi.content.Activity;

/**
 * View letting the user register an application
 * @author Gwendal
 */
public final class RegisterView extends Activity {
    
    public static final RegisterView INSTANCE = new RegisterView();
    
    private RegisterView() {
        super();
        title = "Register your application";
    }
}
