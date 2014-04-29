package test.modele.node;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.node.CloudBookNode;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class CloudBookNodeTest {
    CloudBookNode n1;
    
    public CloudBookNodeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        // n1 = new CloudBookNode(new Image(), null, Cloud.GDRIVE, null, port, appType, performance, speed);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSerialisation() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.serial"));
            oos.writeObject(n1);
            oos.flush();
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.serial"));
            CloudBookNode n2 = (CloudBookNode) ois.readObject();
            ois.close();
            assertFalse(n1 == null);
            assertTrue(n2.equals(n2));
        } catch (FileNotFoundException ex) {
            fail();
        } catch (IOException | ClassNotFoundException ex) {
            fail();
        }
    }

}