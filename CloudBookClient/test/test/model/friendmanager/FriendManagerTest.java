package test.model.friendmanager;

import java.io.File;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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

    /**
     * Test of isFriend method, of class FriendManager.
     */
    @Test
    public void testIsFriend() {
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

    /**
     * Test of getRelevantFriends method, of class FriendManager.
     */
    @Test
    public void testGetRelevantFriends() {
        System.out.println("getRelevantFriends");
        int nb = 0;
        FriendManager instance = new FriendManager();
        List<Friend> expResult = null;
        List<Friend> result = instance.getRelevantFriends(nb);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTrustedFriends method, of class FriendManager.
     */
    @Test
    public void testGetTrustedFriends() {
        System.out.println("getTrustedFriends");
        int nb = 0;
        FriendManager instance = new FriendManager();
        List<Friend> expResult = null;
        List<Friend> result = instance.getTrustedFriends(nb);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSomeFriends method, of class FriendManager.
     */
    @Test
    public void testGetSomeFriends() {
        System.out.println("getSomeFriends");
        int nb = 0;
        FriendManager instance = new FriendManager();
        List<Friend> expResult = null;
        List<Friend> result = instance.getSomeFriends(nb);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of bestCloud method, of class FriendManager.
     */
    @Test
    public void testBestCloud() {
        System.out.println("bestCloud");
        FriendManager instance = new FriendManager();
        Cloud expResult = null;
        Cloud result = instance.bestCloud();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of scores method, of class FriendManager.
     */
    @Test
    public void testScores() {
        System.out.println("scores");
        FriendManager instance = new FriendManager();
        Map<Cloud, Double> expResult = null;
        Map<Cloud, Double> result = instance.scores();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of relevanceSum method, of class FriendManager.
     */
    @Test
    public void testRelevanceSum() {
        System.out.println("relevanceSum");
        Cloud cloud = null;
        FriendManager instance = new FriendManager();
        double expResult = 0.0;
        double result = instance.relevanceSum(cloud);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
