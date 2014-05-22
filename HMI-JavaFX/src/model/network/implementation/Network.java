/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.network.implementation;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.engine.Engine;
import model.network.interfaces.Information;
import model.network.interfaces.RemoteClient;
import model.network.interfaces.RemoteServer;
import model.network.interfaces.Sendable;
import model.node.friend.Member;

/**
 * 
 * @author Gwendal
 * 
 * Network management root
 */
public class Network extends UnicastRemoteObject implements RemoteClient {
    
    protected String ip;
    protected int port;
    protected RemoteServer stub;
    protected ConnectionState connectionState;
    protected Engine engine;
    
    /**
     * Constructor
     * @param ip ip of the server
     * @param port port of the server
     * @param engine engine running this Network instance
     * @throws RemoteException required by RMI
     */
    public Network(String ip, int port, Engine engine) throws RemoteException {
        this.ip = ip;
        this.port = port;
        this.engine = engine;
        this.connectionState = new ConnectionState(false);
    }
    
    @Override
    public void handleRequest(Sendable request) throws RemoteException {
        engine.handleRequest(request);
    }
    
    @Override
    public void send(Sendable request, String receiver) throws RemoteException {
        Information info = request.getInfo();
        if(info != null)
            info.saveProperties();
        Member mem = (Member)request.getSender();
        mem.beforeSerialization();
        stub.send(request, receiver);
    }

    @Override
    public RemoteServer getStub() throws RemoteException {
        return stub;
    }

    /**
     * registers this Network client on the specified server
     * @param serverId identifier of the server
     * @throws RemoteException in case of remote access problem
     */
    @Override
    public void connect(String serverId) throws RemoteException {
        try {
            stub = (RemoteServer)Naming.lookup("rmi://" + serverId + "/" + RemoteServer.NAME);
            stub.connect(this);
            connectionState.setConnected(true);
        } catch (NotBoundException | MalformedURLException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * gets the identifier
     * @return identifier of the Network
     * @throws RemoteException in case of problem of remote access
     */
    @Override
    public String getId() throws RemoteException {
        return ip + ":" + port;
    }

    /**
     * disconnects of the server
     * stub is set to null
     * @throws RemoteException in case of remote access problem
     */
    @Override
    public void disconnect() throws RemoteException {
        stub.disconnect(this);
        stub = null;
        connectionState.setConnected(false);
    }

    @Override
    public void broadcast(Sendable request) throws RemoteException {
        Information info = request.getInfo();
        if(info != null)
            info.saveProperties();
        stub.broadcast(request);
    }

    /**
     * Getter
     * @return connectionState field
     */
    public ConnectionState getConnectionState() {
        return connectionState;
    }

    /**
     * Getter
     * @return engine field
     */
    public Engine getEngine() {
        return engine;
    }
    
}
