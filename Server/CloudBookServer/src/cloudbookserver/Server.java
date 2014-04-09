/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cloudbookserver;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.network.interfaces.RemoteClient;
import model.network.interfaces.RemoteServer;
import model.network.interfaces.Sendable;

/**
 *
 * @author Gwendal
 */
public final class Server extends UnicastRemoteObject implements RemoteServer {

    public static final Server INSTANCE = makeServerScheduler();
    
    private Map<String, RemoteClient> clients;
    private String ip;
    
    //port on which the server is running
    private int port;
    private String url;
    
    private Server(String ip) throws RemoteException {
        clients = new HashMap<>();
        this.ip = ip;
        port = PORT;
    }
    
    private static Server makeServerScheduler() {
        try {
            String ip = InetAddress.getLocalHost().getHostName();
            Server res = new Server(ip);
            return res;
        } catch (UnknownHostException | RemoteException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void send(Sendable request, String receiver) throws RemoteException {
        Servant servant = new Servant(request, receiver);
        servant.start();
    }

    @Override
    public void connect(RemoteClient rc) throws RemoteException {
        if(!clients.containsValue(rc)) {
            clients.put(rc.getIp(), rc);
            System.out.println("Client added : " + rc.getIp());
        }
    }

    @Override
    public void disconnect(RemoteClient rc) throws RemoteException {
        if(clients.containsValue(rc))
            clients.remove(rc.getIp());
    }

    @Override
    public String getIp() throws RemoteException {
        return ip;
    }

    @Override
    public RemoteClient getClient(String client) throws RemoteException {
        if(clients.containsKey(client))
            return clients.get(client);
        return null;
    }

    @Override
    public void binding() throws RemoteException {
        try {
            url = "rmi://" + ip + ":" + port + "/" + NAME;
            LocateRegistry.createRegistry(50100);
            Naming.bind(url, this);
        } catch (AlreadyBoundException | MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getUrl() throws RemoteException {
        return url;
    }

    /**
     * setter
     * @param port value
     */
    public void setPort(int port) {
        this.port = port;
    }
    
}
