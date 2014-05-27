package test.model.friendmanager;

import java.io.File;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.friendmanager.FriendManager;
import model.node.AppVector;
import model.node.Cloud;
import model.node.MyNode;
import model.node.friend.Friend;
import model.node.friend.Member;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FriendManagerTest {
    
    private FriendManager instance;
    
    public FriendManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws UnknownHostException, RemoteException {
        MyNode node = new MyNode(new File("name"), "test", Cloud.GOOGLE, 0, 2, 2, 2);
        instance = new FriendManager(node);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of clear method, of class FriendManager.
     */
    @Test
    public void testClear() {
        instance.clear();
        //Untestable method        
    }

    @Test
    public void testFriends() {
        String id = "totototo";
        Friend f = new Friend(id, 5, 5, new AppVector(2,2,2), Cloud.GOOGLE);
        assertFalse(instance.isFriend(id));
        instance.update(f); //friend relevant and not in friends --> call add
        assertTrue(instance.isFriend(id));
        f = new Friend(id, 5, 5, new AppVector(0,2,2), Cloud.GOOGLE);
        instance.update(f); //friend in friends and still relevant --> update
        assertTrue(instance.isFriend(id));
        f = new Friend(id, 5, 5, new AppVector(200,200,200), Cloud.GOOGLE);
        instance.update(f); //friend in friends but not relevant anymore --> call remove
        assertFalse(instance.isFriend(id));
    }

    /**
     * Test of relevance method, of class FriendManager.
     */
    @Test
    public void testRelevance() {
        AppVector v = new AppVector(0,2,2);
        double expResult = 2.0;
        double result = instance.relevance(v);
        assertTrue(expResult == result);
    }
}
