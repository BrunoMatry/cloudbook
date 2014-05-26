package hmi.home;

import hmi.button.IconFlyWeight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.node.FileEngineRelation;

/**
 * command
 * command dedecated to the destruction of a save file
 */
public class DeleteNode extends RegistryButton {

    /**
     * Constructor
     */
    public DeleteNode() {
        super();
        Image trash = IconFlyWeight.INSTANCE.getTrash();
        ImageView iv = new ImageView(trash);
        setGraphic(iv);
    }
    
    /**
     * Consructor
     * @param node node on which the action is to be performed
     */
    public DeleteNode(String node) {
        super(node);
        Image trash = IconFlyWeight.INSTANCE.getTrash();
        ImageView iv = new ImageView(trash);
        setGraphic(iv);
    }

    /**
     * Deletes the selected node
     */
    @Override
    public void execute() {
        FileEngineRelation.INSTANCE.removeByName(node + ".ser");
    }
    
}
