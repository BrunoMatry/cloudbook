package test.modele.node;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.node.AppVector;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AppVectorTest {
    
    AppVector appVector, appVector2;
    
    public AppVectorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        appVector = new AppVector(1, 2, 3);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConstructeur() {
        assertTrue(appVector.appTypeProperty().get() == 1);
        assertTrue(appVector.performanceProperty().get() == 2);
        assertTrue(appVector.speedProperty().get() == 3);
    }
    
    @Test
    public void testCopy() {
        appVector2 = appVector.copy();
        appVector.performanceProperty().set(3);
        assertTrue(appVector.performanceProperty().get() == 3);
        assertTrue(appVector2.performanceProperty().get() == 2);
    }
    
    @Test
    public void testSerialization() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.serial"));
            oos.writeObject(appVector);
            oos.flush();
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.serial"));
            appVector2 = (AppVector) ois.readObject();
            ois.close();
            assertTrue(appVector2.equals(appVector));
        } catch (FileNotFoundException ex) {
            fail();
        } catch (IOException | ClassNotFoundException ex) {
            fail();
        }
    }
}
