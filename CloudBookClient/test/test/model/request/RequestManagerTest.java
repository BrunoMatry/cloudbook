package test.model.request;

import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.engine.Engine;
import model.network.interfaces.Information;
import model.network.interfaces.Sendable;
import model.node.AppVector;
import model.node.MyNode;
import model.request.Request;
import model.request.RequestManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RequestManagerTest {
    private RequestManager instance;
    
    public RequestManagerTest() {
        try {
            instance = new RequestManager(null, new Engine(new MyNode()));
        } catch (UnknownHostException | RemoteException ex) {
            Logger.getLogger(RequestManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of handleRequest method, of class RequestManager.
     */
    @Test
    public void testHandleRequest() {
        // Method impossible to test
    }

    /**
     * Test of createRequest method, of class RequestManager.
     */
    @Test
    public void testCreateRequest() {
        Information data = new AppVector(0, 0, 0);
        try {
            Request expResult = new Request(data, instance);
            Request result = instance.createRequest(data);
            assertEquals(expResult, result);
        } catch (RemoteException | UnknownHostException ex) {
            Logger.getLogger(RequestManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createRequests method, of class RequestManager.
     */
    @Test
    public void testCreateRequests() {
        System.out.println("createRequests");
        RequestManager instance = null;
        List<Request> expResult = null;
        List<Request> result = instance.createRequests(null);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInbox method, of class RequestManager.
     */
    @Test
    public void testGetInbox_0args() {
        System.out.println("getInbox");
        RequestManager instance = null;
        List<Information> expResult = null;
        List<Information> result = instance.getInbox();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInbox method, of class RequestManager.
     */
    @Test
    public void testGetInbox_int() {
        System.out.println("getInbox");
        int i = 0;
        RequestManager instance = null;
        Information expResult = null;
        Information result = instance.getInbox(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEngine method, of class RequestManager.
     */
    @Test
    public void testGetEngine() {
        System.out.println("getEngine");
        RequestManager instance = null;
        Engine expResult = null;
        Engine result = instance.getEngine();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
