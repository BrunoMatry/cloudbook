/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test.network;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.network.implementation.Network;
import model.network.interfaces.RemoteBufferedServer;
import model.network.interfaces.RemoteClient;
import model.network.interfaces.Sendable;
import model.node.Message;
import model.request.Request;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Gwendal
 */
public class NetworkTest {
    
    private static RemoteBufferedServer server;
    private static Sendable msg;
    private static Network alice;
    private static Network me;
    private static Network bob;
    private static Message original;
    
    public NetworkTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            server = new TestServer();
            server.binding();
            alice = new Network("alice", 888);
            me = new Network(InetAddress.getLocalHost().getHostAddress(), 777);
            bob = new Network("bob", 50010);
            original = new Message();
            msg = new Request(original);
            alice.connect("rmi://" + InetAddress.getLocalHost().getHostAddress() + ":" + 50020 + "/TestServer");
            me.connect("rmi://" + InetAddress.getLocalHost().getHostAddress() + ":" + 50020 + "/TestServer");
        } catch (RemoteException | UnknownHostException ex) {
            Logger.getLogger(NetworkTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    @SuppressWarnings("empty-statement")
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testSend() {
        try {
            alice.send(msg, me.getId());
            Sendable recvd = server.getSendable(me.getId(), 0);
            Message msgRec = (Message)recvd.getInfo();
            msgRec.restoreProperties();
            Assert.assertEquals(original, msgRec);
        } catch (RemoteException ex) {
            fail(ex.getMessage());
            Logger.getLogger(NetworkTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testConnectByUrl() {
        try {
            Assert.assertTrue(server.getClient("bob:50010") == null);
            bob.connect("rmi://" + InetAddress.getLocalHost().getHostAddress() + ":" + 50020 + "/TestServer");
            Assert.assertTrue(server.getClient("bob:50010") != null);
        } catch (RemoteException ex) {
            fail(ex.getMessage());
            Logger.getLogger(NetworkTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            fail(ex.getMessage());
            Logger.getLogger(NetworkTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static class TestServer extends UnicastRemoteObject implements RemoteBufferedServer {

        //Message box
        private Map<String, List<Sendable>> msgBox;
        
        //Client references
        private Map<String, RemoteClient> clients;
        
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
            msgBox.put(rc.getId(), new ArrayList<Sendable>());
            clients.put(rc.getId(), rc);
        }

        /**
         * removes a client of the message box list
         * @param rc client to remove
         * @throws RemoteException cannot receive client properly
         */
        @Override
        public void disconnect(RemoteClient rc) throws RemoteException {
            msgBox.remove(rc.getId());
            clients.remove(rc.getId());
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
                url = "rmi://" + InetAddress.getLocalHost().getHostName() + ":" + 50020 + "/TestServer";
                LocateRegistry.createRegistry(50020);
                Naming.bind(url, this);
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
            msgBox.get(receiver).add(request);
        }

        /**
         * Gets the current inetaddress
         * @return local ip
         * @throws RemoteException cannot get distant reference
         */
        @Override
        public String getId() throws RemoteException {
            try {
                return InetAddress.getLocalHost().getHostAddress() + ":" + 50020;
            } catch (UnknownHostException ex) {
                Logger.getLogger(NetworkTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "";
        }

        @Override
        @SuppressWarnings("empty-statement")
        public Sendable getSendable(String receiver, int index) throws RemoteException {
            while(msgBox.get(receiver).isEmpty());
            return msgBox.get(receiver).get(index);
        }
        
    }
    
}
