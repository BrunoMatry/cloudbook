/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.tab;

import hmi.content.friend.ContentFriendTab;

/**
 *
 * @author Bruno
 */
public class FriendTab extends Tab {
    
    public FriendTab(ContentFriendTab c){
        this.content = c;
        this.getChildren().add(content);
    }
}
