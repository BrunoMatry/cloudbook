package test.modele.node;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.node.AppVector;
import model.node.Friend;
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
        friend = new Friend(1, 2, 1.5, new AppVector(1, 2, 3));
    }
    
    @After
    public void tearDown() {
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