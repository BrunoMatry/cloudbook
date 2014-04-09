package test.modele.node;

import model.node.Cloud;
import model.node.State;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
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
}
