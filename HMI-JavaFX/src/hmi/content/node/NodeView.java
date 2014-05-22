package hmi.content.node;

import hmi.content.AActivity;
import hmi.content.OneNodeActivity;
import hmi.content.node.component.FriendPane;
import hmi.content.node.component.MessageView;
import hmi.content.node.component.MesurePane;
import hmi.content.node.component.StateView;
import hmi.home.NodeList;
import java.util.ArrayList;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.node.ApplicationList;
import model.node.CloudBookNode;
import model.node.Mesure;
import model.node.friend.Friend;

/**
 * singleton
 * View of the current member
 */
public final class NodeView extends OneNodeActivity {
    
    public static final NodeView INSTANCE = new NodeView(NodeList.INSTANCE);
    
    //view contaigning all the generated mesures
    private MesurePane mesurePane;
    
    //view containing all the friends of the current node
    private FriendPane friendPane;
    
    //summary of the state of application
    private SummarizedView<ImageView> state;
    
    //summary of the mesures
    private SummarizedView<Text> mesures;
    
    //summary of the messages
    private SummarizedView<Text> message;
    
    private SummarizedView<Text> friends;
    
    private Button appVector;
    
    /**
     * 
     * @param p the previous Activity
     */
    private NodeView(AActivity p) {
        super(p);
        title = "Friend management";
        appVector = new Button();
        appVector.setGraphic(new Text("Application characteristics"));
        ArrayList<Button> components = new ArrayList<>();
        components.add(getState());
        components.add(getMesures());
        components.add(getMessage());
        components.add(getFriend());
        components.add(appVector);
        
        int size = components.size();
        for(int i = 0 ; i < size ; i++) {
            Button cmp = components.get(i);
            double x = (getScene().getWidth()/2) + 200*Math.cos(i*(2*Math.PI/size))
                    - cmp.getBoundsInParent().getWidth()/2;
            double y = (getScene().getHeight()/2) + 200*Math.sin(i*(2*Math.PI/size))
                    - cmp.getBoundsInParent().getHeight()/2;
            placeChild(cmp, (int)x, (int)y);
        }
    }
    
    /**
     * getter
     * if state is null, it is initialized
     * @return state attribute
     */
    public SummarizedView getState() {
        if(state == null) {
            StateView sv = new StateView(this);
            state = sv.makeSummarized();
        }
        return state;
    }

    /**
     * Getter
     * if friends is null, it is initialized
     * @return friends attribute
     */
    public SummarizedView getFriend() {
        if(friends == null) {
            friendPane = new FriendPane(this);
            TableView<Friend> table = friendPane.getTable();
            CloudBookNode node = ApplicationList.INSTANCE.getCurrentNode();
            ObservableList<Friend> friendList = node.getFriends().boxObservableList();
            table.setItems(friendList);
            friends = friendPane.makeSummarized();
        }
        return friends;
    }
    
    /**
     * Getter
     * if mesures is null, it is initialized
     * @return mesures attribute
     */
    public SummarizedView getMesures() {
        if(mesures == null) {
            mesurePane = new MesurePane(this);
            TableView<Mesure> table = mesurePane.getTable();
            CloudBookNode node = ApplicationList.INSTANCE.getCurrentNode();
            ObservableList<Mesure> mesureList = node.getMesures().boxObservableList();
            table.setItems(mesureList);
            mesures = mesurePane.makeSummarized();
        }
        return mesures;
    }

    /**
     * getter
     * if message is null, it is initialized
     * @return message attribute
     */
    public SummarizedView getMessage() {
        if(message == null) {
            //TableView<Friend> table = friendPane.getTable();
            CloudBookNode node = ApplicationList.INSTANCE.getCurrentNode();
            ObservableList<Friend> friendList = node.getFriends().boxObservableList();
            //table.setItems(friendList);
        }
        return message;
    }
    
    /**
     * initialize the model and build all the children views
     * @param model : model of the current view
     */
    /*
    public NodeView(CloudBookNode model) {
        super();
        this.model = model;
        components = new ArrayList<>();
        //components.add();
    }
   */
    /**
     * Places the current view in its parent without refreshing it
     * @param node child to place
     * @param x horizontal position
     * @param y vertical position
     */
    public void placeChild(Node node, int x, int y) {
        node.setLayoutX(x);
        node.setLayoutY(y);
        getChildren().add(node);
    }
    
    /**
     * TODO
     * @param source : component demanding to be displayed
     */
    public void onDisplay(IComponentView source) {
        
    }
    
    /**
     * TODO
     * @param source : component demanding to be updated
     */
    public void onUpdate(IComponentView source) {
        
    }
    
    /**
     * TODO
     * @param source : component demanding to be hidden
     */
    public void onHide(IComponentView source) {
        getChildren().remove(source);
    }
    
    /**
     * Bind the view with the current node
     */
    @Override
    public void bindWithNode() {
        super.bindWithNode();
        CloudBookNode node = ApplicationList.INSTANCE.getCurrentNode();
        ObjectProperty<Image> stateView = state.getView().imageProperty();
        stateView.bind(node.getPlatform().iconProperty());
    }
    
}
