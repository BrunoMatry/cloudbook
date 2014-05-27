package test.model.request;

import java.io.File;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.engine.Engine;
import model.network.interfaces.Information;
import model.node.AppVector;
import model.node.Cloud;
import model.node.Message;
import model.node.Mesure;
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
            instance = new RequestManager(null, new Engine(node));
        } catch (UnknownHostException | RemoteException ex) {
            Logger.getLogger(RequestManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        Request result = instance.createRequest(data);
        assertEquals(result.getInfo(),data);
    }

    /**
     * Test of createRequests method, of class RequestManager.
     */
    @Test
    public void testCreateRequests() {
        List<Information> infos = new ArrayList<>();
        infos.add(new AppVector(0,0,0));
        infos.add(new Mesure(null, 0, 0, 0));
        List<Request> result = instance.createRequests(infos);
        assertEquals(result.get(0).getInfo(), infos.get(0));
        assertEquals(result.get(1).getInfo(), infos.get(1));
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
