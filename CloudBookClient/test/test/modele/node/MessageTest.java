package test.modele.node;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import model.node.AppVector;
import model.node.Cloud;
import model.node.Message;
import model.node.Mesure;
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
    Message message;
    
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
        message = new Message("1", new AppVector(1, 4, 3), new State(Cloud.GOOGLE), true);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() {
        assertTrue(message.getIdSender().equals("1"));
        assertTrue(message.getContent().equals(new State(Cloud.GOOGLE)));
        assertTrue(message.getVector().equals(new AppVector(1, 4, 3)));
    }
    
    @Test
    public void testSerialisation() {
        try {
            message.saveProperties();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.serial"));
            oos.writeObject(message);
            oos.flush();
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.serial"));
            Message messageSerial = (Message) ois.readObject();
            ois.close();
            messageSerial.restoreProperties();
            assertFalse(message == null);
            assertFalse(messageSerial == null);
            assertTrue(messageSerial.equals(message));
        } catch (FileNotFoundException ex) {
            fail();
        } catch (IOException | ClassNotFoundException ex) {
            fail();
        }
    }

}