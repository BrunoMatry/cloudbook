package hmi.content.node.component;

import hmi.content.AbstractActivity;
import hmi.content.node.NodeComponentView;
import hmi.content.node.SummarizedView;
import java.util.ArrayList;
import javafx.scene.image.ImageView;


public class StateView extends NodeComponentView {

    //all the clouds during the application existence
    protected ArrayList<ImageView> clouds;
    
    public StateView(AbstractActivity p) {
        super(p);
        clouds = new ArrayList<>();
        title = "History";
    }

    @Override
    public SummarizedView makeSummarized() {
        return new SummarizedView(this, new ImageView());
    }
    
}
