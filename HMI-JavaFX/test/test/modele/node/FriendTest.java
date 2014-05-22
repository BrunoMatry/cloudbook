package test.modele.node;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.node.AppVector;
import model.node.friend.Friend;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FriendTest {
    Friend friend;
    
    public FriendTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        friend = new Friend("1", 2, 1.5, new AppVector(1, 2, 3));
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() {
        assertTrue(friend.idProperty().get().equals("1"));
        assertTrue(friend.confidenceProperty().get() == 2);
        assertTrue(friend.relevanceProperty().get() == 1.5);
        assertTrue(friend.getVector().equals(new AppVector(1, 2, 3)));
    }
    
    @Test
    public void testDaySinceLastConnexion() {
        assertTrue(friend.daysSinceLastConnection() == 0);
    }
    
    @Test
    public void testSetters() {
        Friend member2 = new Friend("4", 3, 1.2, new AppVector(1, 5, 3));
        assertTrue(member2.idProperty().get().equals("4"));
        assertTrue(member2.confidenceProperty().get() == 3);
        member2.confidenceProperty().set(4);
        assertTrue(member2.confidenceProperty().get() == 4);
        assertTrue(member2.relevanceProperty().get() == 1.2);
        member2.relevanceProperty().set(3.6);
        assertTrue(member2.relevanceProperty().get() == 3.6);
        assertTrue(member2.getVector().equals(new AppVector(1, 5, 3)));
        member2.setVector(new AppVector(2, 4, 9));
        assertTrue(member2.getVector().equals(new AppVector(2, 4, 9)));
    }
    
    @Test
    public void testSerialisation() {
        try {
            friend.saveProperties();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.serial"));
            oos.writeObject(friend);
            oos.flush();
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.serial"));
            Friend friendSerial = (Friend) ois.readObject();
            ois.close();
            friendSerial.restoreProperties();
            assertFalse(friend == null);
            assertTrue(friend.equals(friendSerial));
        } catch (FileNotFoundException ex) {
            fail();
        } catch (IOException | ClassNotFoundException ex) {
            fail();
        }
    }
}