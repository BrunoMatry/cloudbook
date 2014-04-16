/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test.network;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.network.implementation.Network;
import model.network.interfaces.RemoteClient;
import model.network.interfaces.RemoteServer;
import model.network.interfaces.Sendable;
import model.node.Message;
import model.request.Request;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Gwendal
 */
public class NetworkTest {
    
    private RemoteServer server;
    private Sendable msg;
    private Network alice;
    private Network me;
    private Network bob;
    
    public NetworkTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    @SuppressWarnings("empty-statement")
    public void setUp() {
        try {
            RemoteServer distant = new TestServer();
            distant.binding();
            alice = new Network("alice", 888);
            me = new Network(InetAddress.getLocalHost().getHostAddress(), 777);
            bob = new Network("bob", 12345);
            msg = new Request(new Message("Hi guys !"));
            while(!TestServer.READY);
            server = (RemoteServer)Naming.lookup("rmi://" + InetAddress.getLocalHost().getHostAddress() + ":" + 500020 + "/TestServer");
            server.connect(alice);
            server.connect(me);
        } catch (NotBoundException | MalformedURLException | RemoteException | UnknownHostException ex) {
            Logger.getLogger(NetworkTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testSend() {
        try {
            me.send(msg, alice.getIp());
            
        } catch (RemoteException ex) {
            fail(ex.getMessage());
            Logger.getLogger(NetworkTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static class TestServer extends UnicastRemoteObject implements RemoteServer {

        //Message box
        private Map<String, Sendable> msgBox;
        
        //Client references
        private Map<String, RemoteClient> clients;
        
        //Indicate if the server is connected
        public static boolean READY = false;
        
        //rmi url
        private String url;

        /**
         * Constructor
         * @throws RemoteException cannot connect
         */
        public TestServer() throws RemoteException {
            msgBox = new HashMap<>();
            clients = new HashMap<>();
        }
        
        /**
         * Adds a client to the message box list
         * @param rc client to add to the server
         * @throws RemoteException cannot receive client properly
         */
        @Override
        public void connect(RemoteClient rc) throws RemoteException {
            msgBox.put(rc.getIp(), null);
            clients.put(rc.getIp(), rc);
        }

        /**
         * removes a client of the message box list
         * @param rc client to remove
         * @throws RemoteException cannot receive client properly
         */
        @Override
        public void disconnect(RemoteClient rc) throws RemoteException {
            msgBox.remove(rc.getIp());
            clients.remove(rc.getIp());
        }

        /**
         * Retrieves the client which corresponds to its ip address
         * @param client ip address corresponding to the client
         * @return client at the ip address
         * @throws RemoteException cannot return the client reference proerly
         */
        @Override
        public RemoteClient getClient(String client) throws RemoteException {
            return clients.get(client);
        }

        /**
         * Registers the server at a test url
         * @throws RemoteException cannot bind
         */
        @Override
        public void binding() throws RemoteException {
            try {
                url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":" + 500020 + "/TestServer";
                LocateRegistry.createRegistry(50100);
                Naming.bind(url, this);
                READY = true;
            } catch (AlreadyBoundException | MalformedURLException | UnknownHostException ex) {
                Logger.getLogger(NetworkTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /**
         * getter
         * @return url
         * @throws RemoteException cannot get distant reference
         */
        @Override
        public String getUrl() throws RemoteException {
            return url;
        }

        /**
         * Puts the request to the appropriate message box
         * @param request object to store
         * @param receiver target client
         * @throws RemoteException  cannot get distant reference
         */
        @Override
        public void send(Sendable request, String receiver) throws RemoteException {
            msgBox.put(receiver, request);
        }

        /**
         * Gets the current inetaddress
         * @return local ip
         * @throws RemoteException cannot get distant reference
         */
        @Override
        public String getIp() throws RemoteException {
            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException ex) {
                Logger.getLogger(NetworkTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "";
        }

        @Override
        public List<Sendable> getSendable(String receiver) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}
