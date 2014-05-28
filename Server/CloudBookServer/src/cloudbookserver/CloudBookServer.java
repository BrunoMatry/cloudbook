package cloudbookserver;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class
 * @author Gwendal
 */
public class CloudBookServer {

    /**
     * Main programm
     * @param args <port>
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        try {
            int port = Integer.parseInt(args[0]);
            Server server = new Server(InetAddress.getLocalHost().getHostAddress() , port);
            server.binding();
            System.out.println("The server is running");
            System.out.println("RMI address : " + server.getUrl());
            while(true);
        } catch (RemoteException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(CloudBookServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
