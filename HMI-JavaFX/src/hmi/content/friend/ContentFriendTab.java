package hmi.content.friend;

import hmi.content.Content;

public class ContentFriendTab extends Content {
    private ContentFriendListView friendList;
    
    public ContentFriendTab() {
        this.friendList = new ContentFriendListView();
        this.getChildren().add(friendList);
    }
}
