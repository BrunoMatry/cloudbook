package hmi.content.node.component;

import hmi.content.AActivity;
import hmi.content.node.NodeComponentView;
import hmi.content.node.SummarizedView;
import javafx.scene.text.Text;

public class MessageView extends NodeComponentView {

    public MessageView(AActivity p) {
        super(p);
        title = "Messages";
    }

    @Override
    public SummarizedView makeSummarized() {
        SummarizedView res = new SummarizedView(this, new Text(getClass().getName()));
        return res;
    }
    
}
