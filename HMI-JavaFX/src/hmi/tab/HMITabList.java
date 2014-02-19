/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.tab;

import hmi.content.HMIContent;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Parent;

/**
 *
 * @author Bruno
 */
public class HMITabList extends Parent {
    private List<HMITab> list;
    private IntegerProperty currentTab;
    
    public HMITabList(List<HMITab> l) {
        this.list = l;
        this.currentTab = new SimpleIntegerProperty(0);

        for(HMITab tab : this.list) {
            this.getChildren().add(tab);
        }
    }
    
    public HMIContent getContent() {
        return list.get(this.currentTab.get()).getContent();
    }
}
