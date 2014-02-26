/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi;

import hmi.content.friend.ContentFriendTab;
import hmi.content.result.ContentResultTab;
import hmi.tab.HMIFriendTab;
import hmi.tab.HMIResultTab;
import hmi.tab.HMITab;
import hmi.tab.HMITabList;
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
    
    public static HMITabList getTabList() {
        List<HMITab> tabList = new ArrayList<>();
        
        /* Friend Tab */
        ContentFriendTab contentFriendTab = new ContentFriendTab();
        HMIFriendTab friendTab = new HMIFriendTab(contentFriendTab);
        tabList.add(friendTab);
        
        /* Result Tab */
        ContentResultTab contentResultTab = new ContentResultTab();
        HMIResultTab resultTab = new HMIResultTab(contentResultTab);
        tabList.add(resultTab);
        
        HMITabList result = new HMITabList(tabList);
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
