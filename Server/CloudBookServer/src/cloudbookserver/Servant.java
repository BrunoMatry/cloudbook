package cloudbookserver;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.network.interfaces.RemoteClient;
import model.network.interfaces.Sendable;

public class Servant extends Thread {

    protected Sendable request;
    protected String receiver;
    protected Server server;
    
    public Servant(Sendable sendable, String target, Server server) {
        request = sendable;
        receiver = target;
        this.server = server;
    }
    
    @Override
    public void run() {
        try {
            RemoteClient client = this.server.getClient(receiver);
            if(client != null)
                client.handleRequest(request);
            else
                System.out.println("A client tries to send a request to a not registered client");
        } catch (RemoteException ex) {
            Logger.getLogger(Servant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
