/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi;

import hmi.content.friend.ContentFriendTab;
import hmi.content.result.ContentResultTab;
import hmi.tab.FriendTab;
import hmi.tab.ResultTab;
import hmi.tab.Tab;
import hmi.tab.TabList;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 *
 * @author Bruno
 */
public class Mounter {
    
    public static TabList getTabList() {
        List<Tab> tabList = new ArrayList<>();
        
        /* Friend Tab */
        ContentFriendTab contentFriendTab = new ContentFriendTab();
        FriendTab friendTab = new FriendTab(contentFriendTab);
        tabList.add(friendTab);
        
        /* Result Tab */
        ContentResultTab contentResultTab = new ContentResultTab();
        ResultTab resultTab = new ResultTab(contentResultTab);
        tabList.add(resultTab);
        
        TabList result = new TabList(tabList);
        return result;
    }
    
    /**
     * 
     * @param root : parent of the scene
     * @return a scene which design matches the standards
     */
    public static Scene getStandardScene(Parent root) {
        Scene scene = new Scene(root, 800, 600);
        scene.setFill(Color.CORNFLOWERBLUE);
        return scene;
    }
}
