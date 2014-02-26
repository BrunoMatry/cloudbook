/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.tab;

import hmi.content.Content;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Parent;

/**
 *
 * @author Bruno
 */
public class TabList extends Parent {
    private List<Tab> list;
    private IntegerProperty currentTab;
    
    public TabList(List<Tab> l) {
        this.list = l;
        this.currentTab = new SimpleIntegerProperty(0);

        for(Tab tab : this.list) {
            this.getChildren().add(tab);
        }
    }
    
    public Content getContent() {
        return list.get(this.currentTab.get()).getContent();
    }
}
