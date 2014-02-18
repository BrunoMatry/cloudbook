/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi;

import hmi.content.friend.HMIContentFriendTab;
import hmi.content.result.HMIContentResultTab;
import hmi.tab.HMIFriendTab;
import hmi.tab.HMIResultTab;
import hmi.tab.HMITab;
import hmi.tab.HMITabList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class HMIMounter {
    
    public static HMITabList getTabList() {
        List<HMITab> tabList = new ArrayList<>();
        
        /* Friend Tab */
        HMIContentFriendTab contentFriendTab = new HMIContentFriendTab();
        HMIFriendTab friendTab = new HMIFriendTab(contentFriendTab);
        tabList.add(friendTab);
        
        /* Result Tab */
        HMIContentResultTab contentResultTab = new HMIContentResultTab();
        HMIResultTab resultTab = new HMIResultTab(contentResultTab);
        tabList.add(resultTab);
        
        HMITabList result = new HMITabList(tabList);
        return result;
    }
}
