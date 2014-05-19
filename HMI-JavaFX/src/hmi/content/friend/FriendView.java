package hmi.content.friend;

import hmi.content.AActivity;
import hmi.content.node.NodeComponentView;
import hmi.content.node.SummarizedView;

public class FriendView extends NodeComponentView {
    // Possède un objet ami
    public FriendView(AActivity p){
        super(p);
    }

    @Override
    public SummarizedView makeSummarized() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
