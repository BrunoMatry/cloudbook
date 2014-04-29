package test.modele.node;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.node.Cloud;
import model.node.State;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StateTest {
    State state1, state2, state3;
    
    public StateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        state1 = new State(Cloud.DROPBOX);
        state2 = new State(Cloud.GDRIVE);
        state3 = new State(Cloud.SKYDRIVE);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConstructeur() {
        assertTrue(Cloud.values()[state1.cloudProperty().get()].equals(Cloud.DROPBOX));
        assertTrue(state1.currentProperty().get());
    }
    
    @Test
    public void testNotCurrentAnymore() {
        assertTrue(state1.currentProperty().get());
        state1.notCurrentAnymore();
        assertFalse(state1.currentProperty().get());
    }
    
    @Test
    public void testGetCloud() {
        assertTrue(state1.getCloud().equals(Cloud.DROPBOX));
        assertTrue(state2.getCloud().equals(Cloud.GDRIVE));
        assertTrue(state3.getCloud().equals(Cloud.SKYDRIVE));
    }
    
    @Test
    public void testSerialization() {
        try {
            state1.saveProperties();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.serial"));
            oos.writeObject(state1);
            oos.flush();
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.serial"));
            State stateSerial = (State) ois.readObject();
            ois.close();
            stateSerial.restoreProperties();
            assertFalse(state1 == null);
            assertFalse(stateSerial == null);
            assertTrue(state1.equals(stateSerial));
        } catch (FileNotFoundException ex) {
            fail();
        } catch (IOException | ClassNotFoundException ex) {
            fail();
        }
    }
}
