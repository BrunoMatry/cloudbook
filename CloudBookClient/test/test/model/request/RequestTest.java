package test.model.request;

import java.io.File;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.engine.Engine;
import model.network.implementation.Network;
import model.network.interfaces.RemoteClient;
import model.network.interfaces.Sender;
import model.node.AppVector;
import model.node.Cloud;
import model.node.MyNode;
import model.node.friend.Member;
import model.request.Request;
import model.request.RequestManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RequestTest {
    
    Request<AppVector> instance;
    RequestManager requestManager;
    AppVector appVector, info;
    
    public RequestTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try {
            MyNode node = new MyNode(new File("name"), "test", Cloud.GOOGLE, 0, 2, 2, 2);
            requestManager = new RequestManager(null, new Engine(node));
            info = new AppVector(2, 2, 3);
            instance = new Request<>(info, requestManager);
            appVector = node.getVector();
        } catch (RemoteException | UnknownHostException ex) {
            Logger.getLogger(RequestTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSender method, of class Request.
     */
    @Test
    public void testGetSender() {
        Member result = (Member) instance.getSender();
        assertEquals(result.getVector(), appVector);
    }

    /**
     * Test of getDate method, of class Request.
     */
    @Test
    public void testGetDate() {
        assertTrue(instance.getDate() != null);
    }

    /**
     * Test of getInfo method, of class Request.
     */
    @Test
    public void testGetInfo() {
        assertEquals(instance.getInfo(), info);
    }

    /**
     * Test of getRebounds method, of class Request.
     */
    @Test
    public void testGetRebounds() {
        assertTrue(instance.getRebounds() >= 0);
    }

    /**
     * Test of getRecipent method, of class Request.
     */
    @Test
    public void testGetRecipent() {
        assertTrue(instance.getRecipent() >= 0);
    }

    /**
     * Test of getId method, of class Request.
     */
    @Test
    public void testGetId() throws RemoteException {
        assertTrue(instance.getId() >= 0);
    }

    /**
     * Test of setSender method, of class Request.
     */
    @Test
    public void testSetSender() {
        Member sender = new Member("", appVector, Cloud.GOOGLE);
        instance.setSender(sender);
        assertEquals(sender, instance.getSender());
    }

    /**
     * Test of setDate method, of class Request.
     */
    @Test
    public void testSetDate() {
        Date date = new Date();
        instance.setDate(date);
        assertEquals(date, instance.getDate());
    }

    /**
     * Test of setInfo method, of class Request.
     */
    @Test
    public void testSetInfo() {
        AppVector vect = new AppVector(10, 15, 20);
        instance.setInfo(vect);
        assertEquals(vect, instance.getInfo());
    }

    /**
     * Test of setRebounds method, of class Request.
     */
    @Test
    public void testSetRebounds() {
        int rebounds = 5;
        instance.setRebounds(rebounds);
        assertEquals(rebounds, instance.getRebounds());
        rebounds = -1;
        instance.setRebounds(rebounds);
        assertEquals(0, instance.getRebounds());
    }

    /**
     * Test of setRecipent method, of class Request.
     */
    @Test
    public void testSetRecipent() {
        int recipent = 3;
        instance.setRecipent(recipent);
        assertEquals(recipent, instance.getRecipent());
    }

    /**
     * Test of setClient method, of class Request.
     * @throws java.rmi.RemoteException
     */
    @Test
    public void testSetClient() throws RemoteException {
        RemoteClient client = new Network("", 1, null);
        instance.setClient(client);
        assertEquals(client, instance.getClient());
    }
}