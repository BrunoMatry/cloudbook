/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.friend;

import hmi.content.HMIContent;

/**
 *
 * @author Bruno
 */
public class HMIContentFriendTab extends HMIContent {
    private HMIContentFriendList friendList;
    
    public HMIContentFriendTab() {
        this.friendList = new HMIContentFriendList();
    }
}
