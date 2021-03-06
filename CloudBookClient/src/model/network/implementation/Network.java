package model.network.implementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
 * Network management root
 */
public class Network extends UnicastRemoteObject implements RemoteClient {
    
    protected String ip;
    protected int port;
    public static final RemoteServer STUB = initializeServer();
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
    
    /**
     * Retrieves the server address in the configuration file
     * @return the server object
     */
    private static RemoteServer initializeServer() {
        FileReader fr = null;
        RemoteServer stub = null;
        try {
            File serverConfig = new File("./serverConfig.txt");
            fr = new FileReader(serverConfig);
            BufferedReader reader = new BufferedReader(fr);
            String address = "";
            while("".equals(address) && address != null)
                address = reader.readLine();
            stub = (RemoteServer) Naming.lookup(address);
            return stub;
        } catch (FileNotFoundException | NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return stub;
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
        request.setClient(this);
        STUB.send(request, receiver);
    }

    @Override
    public RemoteServer getStub() throws RemoteException {
        return STUB;
    }

    /**
     * registers this Network client on the specified server
     * @throws RemoteException in case of remote access problem
     */
    @Override
    public void connect() throws RemoteException {
        STUB.connect(this);
        connectionState.setConnected(true);
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
        STUB.disconnect(this);
        connectionState.setConnected(false);
    }

    @Override
    public void broadcast(Sendable request) throws RemoteException {
        Information info = request.getInfo();
        if(info != null)
            info.saveProperties();
        request.setClient(this);
        STUB.broadcast(request);
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
