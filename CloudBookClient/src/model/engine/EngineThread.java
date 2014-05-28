package model.engine;

import static com.sun.deploy.util.SessionState.save;
import static java.lang.Thread.sleep;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.friendmanager.IFriendManager;
import model.monitoring.Monitoring;
import model.network.interfaces.Information;
import model.network.interfaces.RemoteClient;
import model.node.Message;
import model.node.MyNode;
import model.node.friend.Friend;
import model.request.Request;

/**
 * Thread performing regular calls to the others modules.
 
 */
public class EngineThread extends Thread {
    
    private final static long nbSecUpdate = 3;
    private final static long nbSecSave = 20;
    private final static long nbSecShare = 5;
    private final static long nbSecClearFriends = 86400; // Once a day
    
    //Stop signal
    private boolean stopFlag;
    
    //Engine responsible of the thread
    private final Engine owner;
    
    //engine monitoring
    private final Monitoring monitoring;
    
    //engine network
    private final RemoteClient network;
    
    //engine friendManager
    private final IFriendManager friendManager;
    
    //engine node
    private final MyNode node;
    
    /**
     * Constructor
     * @param owner engine responsible of this thread
     */
    public EngineThread(Engine owner) {
        stopFlag = false;
        this.owner = owner;
        this.monitoring = (Monitoring)owner.getMonitoring();
        this.friendManager = owner.getFriendManager();
        this.network = owner.getNetwork();
        this.node = owner.getNode();
    }

    /**
     * Starts the communication with the network
     */
    @Override
    public void run() {
        if(monitoring != null && network != null) {
            try {
                network.connect();
                monitoring.start();
            } catch (RemoteException ex) {
                Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(EngineThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        stopFlag = false;
        int cpt = 0;
        int type = 0;
        while(!stopFlag) {
            try {
                sleep(1000);
                cpt++;
                if(cpt % nbSecUpdate == 0)
                    updateInformation();
                if(cpt % nbSecShare == 0)
                    shareInformation(type, 3, 3);
                if(cpt % nbSecSave == 0)
                    save();
                if(cpt % nbSecClearFriends == 0)
                    friendManager.clear();
                if(cpt >= Integer.MAX_VALUE)
                    cpt = Integer.MIN_VALUE;
            } catch (InterruptedException | UnknownHostException | RemoteException ex) {
                Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
            }
            type = (type + 1) % 3; 
        }
        try {
            network.disconnect();
            monitoring.stop();
        } catch (RemoteException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(EngineThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Update the current node with information from monitoring
     */
    protected void updateInformation(){
        monitoring.pushInformation();
    }
    
    /**
     * Share mesure with friends
     * @param type type of information index
     * @param nbMesures number of mesures to share
     * @param nbFriends max number of friends to target
     * @throws UnknownHostException unknown host
     * @throws RemoteException remote access problem
     */
    protected void shareInformation(int type, int nbMesures, int nbFriends) throws UnknownHostException, RemoteException {
        // Get the last information from the node (can be modified)
        ArrayList<Information> information;
        
        switch(type) {
            case 0:
                information = node.getMesures().getLastValues(nbMesures);
                break;
            case 1:
                information = node.getFriends().getLastValues(nbMesures);
                break;
            case 2:
                information = node.getStates().getLastValues(nbMesures);
                break;
            default:
                information = node.getMesures().getLastValues(nbMesures);
                break;
        }
        
        //generation of corresponding messages
        List<Message> messages = owner.makeMessages(information);
        
        // Creating requests
        List<Request> requests = owner.requestManager.createRequests(messages);
        
        //Retriving of all members to which the request is to be sent
        List<Friend> friends = friendManager.getRelevantFriends(nbFriends);
        
        if(friends == null || friends.isEmpty())
            owner.broadcast(requests);
        else
            owner.send(requests, friends);
    }
    
    /**
     * Setter
     * @param stopFlag stopFlag attribute
     */
    public void setStopFlag(boolean stopFlag) {
        this.stopFlag = stopFlag;
    }   
}