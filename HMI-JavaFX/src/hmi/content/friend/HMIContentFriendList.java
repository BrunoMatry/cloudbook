/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.friend;

import java.util.List;
import javafx.scene.Parent;

/**
 *
 * @author Bruno
 */
public class HMIContentFriendList extends Parent {
    private List<HMIFriend> list;
    
    public HMIContentFriendList() {
        list = this.getFriendList();
    }

    private List<HMIFriend> getFriendList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
