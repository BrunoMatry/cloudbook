package hmi.tab;

import hmi.content.friend.ContentFriendTab;

public class FriendTab extends Tab {
    
    public FriendTab(ContentFriendTab c){
        this.content = c;
        this.getChildren().add(content);
    }
}
