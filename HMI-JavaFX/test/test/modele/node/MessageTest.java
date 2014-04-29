package test.modele.node;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.node.AppVector;
import model.node.Cloud;
import model.node.Message;
import model.node.State;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MessageTest {
    Message m1, m2;
    
    public MessageTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        m1 = new Message(1, new AppVector(1, 4, 3), new State(Cloud.GDRIVE), true);
        m2 = new Message(2, new AppVector(1, 2, 3), new State(Cloud.GDRIVE), true);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSerialisation() {
        try {
            m1.saveProperties();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.serial"));
            oos.writeObject(m1);
            oos.flush();
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.serial"));
            Message m3 = (Message) ois.readObject();
            ois.close();
            m3.restoreProperties();
            assertFalse(m1 == null);
            assertTrue(m1.equals(m3));
            assertFalse(m1.equals(m2));
        } catch (FileNotFoundException ex) {
            fail();
        } catch (IOException | ClassNotFoundException ex) {
            fail();
        }
    }

}