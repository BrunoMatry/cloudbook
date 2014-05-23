package model.request;

import java.net.UnknownHostException;
import model.network.interfaces.Sendable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.engine.Engine;
import model.friendmanager.FriendManager;
import model.friendmanager.IFriendManager;
import model.network.interfaces.Information;
import model.node.Message;
import model.node.friend.Member;

public class RequestManager implements IRequestManager {
    
    protected IFriendManager friendManager;
    protected List<Information> inbox;
    protected Engine engine;
    
    /**
     * Constructor
     * @param friendManager responsible of all the friends
     * @param engine engine which run this instance of FrienManager
     */
    public RequestManager(IFriendManager friendManager, Engine engine) {
        this.friendManager = friendManager;
        inbox = new ArrayList<>();
        this.engine = engine;
    }

    /**
     * Handling of a received request
     * @param req request which has been received
     */
    @Override
    public synchronized void handleRequest(Sendable req) {
        
        //The friend manager must examine the sender to determine if it is a friend
        try {
            Member mem = (Member)req.getSender();
            mem.afterDeserialization();
            friendManager.update(mem);
        } catch (RemoteException ex) {
            Logger.getLogger(RequestManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Information info = req.getInfo();
            if(info.getClass() == Message.class) {
                Message msg = (Message)info;
                msg.restoreProperties();
                engine.getNode().addMessage(msg);
            } //the request must contain a message. In the opposite case, the request is ignored
        } catch (RemoteException ex) {
            Logger.getLogger(RequestManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        notifyAll();
    }

    @Override
    public Request createRequest(Information data) {
        Request req = null;
        try {
            req = new Request(data, this);
        } catch (RemoteException | UnknownHostException ex) {
            Logger.getLogger(RequestManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return req;
    }
    
    @Override
    public <I extends Information> List<Request> createRequests(List<I> l) {
        ArrayList<Request> res = new ArrayList<>();
        for(Information i : l)
            res.add(createRequest(i));
        return res;
    }

    public IFriendManager getFriendManager() {
        return friendManager;
    }

    public List<Information> getInbox() {
        return inbox;
    }
    
    public Information getInbox(int i) {
        Information res = inbox.get(i);
        return res;
    }

    /**
     * Getter
     * @return engine field
     */
    public Engine getEngine() {
        return engine;
    }  
}
