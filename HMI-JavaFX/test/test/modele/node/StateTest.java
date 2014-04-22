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
    State s1, s2, s3;
    
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
        s1 = new State(Cloud.DROPBOX);
        s2 = new State(Cloud.GDRIVE);
        s3 = new State(Cloud.SKYDRIVE);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConstructeur() {
        assertTrue(Cloud.values()[s1.cloudProperty().get()].equals(Cloud.DROPBOX));
        assertTrue(s1.currentProperty().get() == true);
    }
    
    @Test
    public void testNotCurrentAnymore() {
        assertTrue(s1.currentProperty().get() == true);
        s1.notCurrentAnymore();
        assertTrue(s1.currentProperty().get() == false);
    }
    
    @Test
    public void testGetCloud() {
        assertTrue(s1.getCloud().equals(Cloud.DROPBOX));
        assertTrue(s2.getCloud().equals(Cloud.GDRIVE));
        assertTrue(s3.getCloud().equals(Cloud.SKYDRIVE));
    }
    
    @Test
    public void testSerialization() {
        try {
            s1.saveProperties();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.serial"));
            oos.writeObject(s1);
            oos.flush();
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.serial"));
            State s4 = (State) ois.readObject();
            ois.close();
            s4.restoreProperties();
            assertFalse(s1 == null);
            assertFalse(s4 == null);
            assertTrue(s1.equals(s4));
        } catch (FileNotFoundException ex) {
            fail();
        } catch (IOException | ClassNotFoundException ex) {
            fail();
        }
    }
}
