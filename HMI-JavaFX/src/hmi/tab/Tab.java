package hmi.tab;

import hmi.content.Content;
import javafx.scene.Parent;

/**
 *
 * @author Bruno
 */
public abstract class Tab extends Parent {
    protected Content content;
    
    public Content getContent(){
        return content;
    }
}
