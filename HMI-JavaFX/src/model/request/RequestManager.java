package model.request;

import model.network.interfaces.Sendable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.friendmanager.IFriendManager;
import model.network.interfaces.Information;
import model.node.Message;

public class RequestManager implements IRequestManager {
    
    protected IFriendManager friendManager;
    protected List<Information> inbox;
    
    /**
     * Constructor
     * @param friendManager responsible of all the friends
     */
    public RequestManager(IFriendManager friendManager) {
        this.friendManager = friendManager;
        inbox = new ArrayList<>();
    }

    /**
     * Handling of a received request
     * @param req   request which has been received
     */
    @Override
    public synchronized void handleRequest(Sendable req) {
        /*
        try {
        friendManager.update(req.getSender());
        } catch (RemoteException ex) {
        Logger.getLogger(RequestManager.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        try {
            Information info = req.getInfo();
            if(info.getClass() == Message.class) {
                Message msg = (Message)info;
                msg.restoreProperties();
                inbox.add(msg);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(RequestManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        notifyAll();
    }

    @Override
    public Request createRequest(int target, Information data) {
        throw new UnsupportedOperationException("Not supported yet.");
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
}
