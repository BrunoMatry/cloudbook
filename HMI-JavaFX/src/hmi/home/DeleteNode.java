package hmi.home;

import hmi.button.IconFlyWeight;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        File saveFile = new File(node + ".ser");
        ObservableFileList.INSTANCE.remove(saveFile);
    }
    
}
