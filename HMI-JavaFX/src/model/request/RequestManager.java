package model.request;

import model.network.interfaces.Sendable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.engine.Engine;
import model.friendmanager.IFriendManager;
import model.network.interfaces.Information;
import model.node.Message;
import model.node.Mesure;

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
                engine.getNode().addMessage(msg);
            } else if(info.getClass() == Mesure.class) {
                Mesure mes = (Mesure)info;
                mes.restoreProperties();
                engine.getNode().addMesure(mes);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(RequestManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        notifyAll();
    }

    @Override
    public Request createRequest(int target, Information data) {
        //Attention : la requête créée doit avoir dans son champ sender un objet de la classe Friend
        //Cet objet doit être une instance qui correspond au noeud courant
        return null;
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
