/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.friend;

import hmi.content.Content;

/**
 *
 * @author Bruno
 */
public class ContentFriendTab extends Content {
    private ContentFriendListView friendList;
    
    public ContentFriendTab() {
        this.friendList = new ContentFriendListView();
        this.getChildren().add(friendList);
    }
}
