/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.tab;

import hmi.content.result.ContentResultTab;

/**
 *
 * @author Bruno
 */
public class HMIResultTab extends HMITab {
    
    public HMIResultTab(ContentResultTab c) {
        this.content = c;
        this.getChildren().add(content);
    }
}
