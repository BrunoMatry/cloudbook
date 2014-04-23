/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import cloudbookserver.Server;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.network.interfaces.RemoteBufferedClient;
import model.network.interfaces.RemoteServer;
import model.network.interfaces.Sendable;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 *
 * @author Gwendal
 */
public class ServerTest {
    
    private Server server;
    
    public ServerTest() throws RemoteException, UnknownHostException {
        server = new Server(InetAddress.getLocalHost().getHostName(), 50300);
    }

    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     * Test class for a simple client
     */
    private static class TestClient extends UnicastRemoteObject implements RemoteBufferedClient {

        private String id;
        private RemoteServer stub;
        private List<Sendable> msgBox;
        
        /**
         * Constructor
         * @param id identifier of the client
         * @throws RemoteException in case of remote access problem
         */
        public TestClient(String id) throws RemoteException {
            this.id = id;
            msgBox = new ArrayList<>();
        }
        
        /**
         * Getter
         * @return stub
         * @throws RemoteException in case of remote access problem
         */
        @Override
        public RemoteServer getStub() throws RemoteException {
            return this.stub;
        }

        /**
         * Registers the current client in the server
         * @param serverId identifier of the server
         * @throws RemoteException in case of remote access problem
         */
        @Override
        public void connect(String serverId) throws RemoteException {
            try {
                stub = (RemoteServer)Naming.lookup("rmi://" + serverId + "/" + RemoteServer.NAME);
                stub.connect(this);
            } catch (NotBoundException | MalformedURLException ex) {
                Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /**
         * removes the current client of its server
         * @throws RemoteException in case of remote access problem
         */
        @Override
        public void disconnect() throws RemoteException {
            if(stub != null) {
                stub.disconnect(this);
                stub = null;
            }
        }

        /**
         * Strores the received request
         * @param request request received from the server
         * @throws RemoteException in case of remote access problem
         */
        @Override
        public void handleRequest(Sendable request) throws RemoteException {
            msgBox.add(request);
        }

        /**
         * Sends a request to another client
         * @param request request to send
         * @param receiver target of the request
         * @throws RemoteException in case of remote access problem
         */
        @Override
        public void send(Sendable request, String receiver) throws RemoteException {
            if(stub != null)
                stub.send(request, receiver);
        }

        /**
         * Getter
         * @return the identifier
         * @throws RemoteException in case of remote access problem
         */
        @Override
        public String getId() throws RemoteException {
            return id;
        }

        /**
         * Getter
         * @param index index of the Sendable in the list
         * @return requested Sendable
         * @throws RemoteException in case of remote access problem
         */
        @Override
        public Sendable getSendable(int index) throws RemoteException {
            return msgBox.get(index);
        }
    
    }
}
