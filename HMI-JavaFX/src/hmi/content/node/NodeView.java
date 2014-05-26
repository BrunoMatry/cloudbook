package hmi.content.node;

import controller.AppVectorStringRelation;
import controller.CloudImageRelation;
import hmi.content.AbstractActivity;
import hmi.content.OneNodeActivity;
import hmi.content.node.component.FriendPane;
import hmi.content.node.component.MessagePane;
import hmi.content.node.component.MesurePane;
import hmi.content.node.component.StatePane;
import hmi.home.MainView;
import java.util.ArrayList;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.node.FileEngineRelation;
import model.node.MyNode;
import model.node.Message;
import model.node.Mesure;
import model.node.State;
import model.node.friend.Friend;

/**
 * singleton
 * View of the current member
 */
public final class NodeView extends OneNodeActivity {
    
    public static final NodeView INSTANCE = new NodeView(MainView.INSTANCE);
    
    //view contaigning all the generated mesures
    private MesurePane mesurePane;
    
    //view containing all the friends of the current node
    private FriendPane friendPane;
    
    //view containing all the messages received by the current node
    private MessagePane messagePane;
    
    //ciew containing all the states in the history of the application
    private StatePane statePane;
    
    //summary of the state of application
    private SummarizedView<ImageView> state;
    
    //summary of the mesures
    private SummarizedView<Text> mesures;
    
    //summary of the messages
    private SummarizedView<Text> message;
    
    private SummarizedView<Text> friends;
    
    private final Text appVector;
    
    private CloudImageRelation binder;
    
    private AppVectorStringRelation vectorBinder;
    
    
    
    /**
     * 
     * @param p the previous Activity
     */
    private NodeView(AbstractActivity p) {
        super(p);
        title = "Friend management";
        appVector = new Text();
        ArrayList<Button> components = new ArrayList<>();
        components.add(getState());
        components.add(getMesures());
        components.add(getMessage());
        components.add(getFriend());
        
        setBottom(appVector);
        
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
            statePane = new StatePane(this);
            TableView<State> table = statePane.getTable();
            MyNode node = FileEngineRelation.INSTANCE.getCurrentEngine().getNode();
            ObservableList<State> stateList = node.getStates().boxObservableList();
            table.setItems(stateList);
            state = statePane.makeSummarized();
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
            MyNode node = FileEngineRelation.INSTANCE.getCurrentEngine().getNode();
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
            MyNode node = FileEngineRelation.INSTANCE.getCurrentEngine().getNode();
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
            messagePane = new MessagePane(this);
            TableView<Message> table = messagePane.getTable();
            MyNode node = FileEngineRelation.INSTANCE.getCurrentEngine().getNode();
            ObservableList<Message> messageList = node.getMessages().boxObservableList();
            table.setItems(messageList);
            message = messagePane.makeSummarized();
        }
        return message;
    }
    
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
     * Bind the view with the current node
     */
    @Override
    public void bindWithNode() {
        super.bindWithNode();
        MyNode node = FileEngineRelation.INSTANCE.getCurrentEngine().getNode();
        ObjectProperty<Image> stateImage = state.getView().imageProperty();
        binder = new CloudImageRelation();
        binder.bind(node.platformProperty());
        binder.drive(stateImage);
        messagePane.bind(node);
        mesurePane.bind(node);
        friendPane.bind(node);
        statePane.bind(node);
        vectorBinder = new AppVectorStringRelation();
        vectorBinder.bind(node.vectorProperty());
        vectorBinder.drive(appVector.textProperty());
    }
}