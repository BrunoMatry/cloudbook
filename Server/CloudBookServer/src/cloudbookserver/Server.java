/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cloudbookserver;

import java.net.MalformedURLException;
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
public class Server extends UnicastRemoteObject implements RemoteServer {

    protected Map<String, RemoteClient> clients;
    protected String ip;
    
    //port on which the server is running
    protected int port;
    protected String url;
    
    /**
     * Constructor
     * @param ip inet address
     * @param port port
     * @throws RemoteException in case of remote access problem
     */
    public Server(String ip, int port) throws RemoteException {
        super(port);
        clients = new HashMap<>();
        this.ip = ip;
        this.port = port;
    }

    /**
     * delegates the execution to a servant
     * @param request request to send
     * @param receiver target of the request
     * @throws RemoteException in case of remote access problem
     */
    @Override
    public void send(Sendable request, String receiver) throws RemoteException {
        Servant servant = new Servant(request, receiver, this);
        servant.start();
    }

    @Override
    public void connect(RemoteClient rc) throws RemoteException {
        if(!clients.containsValue(rc)) {
            clients.put(rc.getId(), rc);
            System.out.println("Client added : " + rc.getId());
        }
    }

    @Override
    public void disconnect(RemoteClient rc) throws RemoteException {
        if(clients.containsValue(rc))
            clients.remove(rc.getId());
    }

    @Override
    public String getId() throws RemoteException {
        return ip + ":" + port;
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
            LocateRegistry.createRegistry(port);
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

    /**
     * Sends a request to every registered client
     * @param request request to send
     * @throws RemoteException in case of remote problems
     */
    @Override
    public void broadcast(Sendable request) throws RemoteException {
        RemoteClient from = request.getClient();
        for(String client : clients.keySet()) {
            if(!clients.get(client).equals(from))
                send(request, client);
        }
    }
    
}
