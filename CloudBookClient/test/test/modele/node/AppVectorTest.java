package test.modele.node;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.node.AppVector;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AppVectorTest {
    
    AppVector appVector;
    
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
        assertTrue(appVector.getAppType() == 1);
        assertTrue(appVector.getPerformanceNeed() == 2);
        assertTrue(appVector.getSpeedNeed() == 3);
    }
    
    @Test
    public void testCopy() {
        AppVector appVectorCopied = appVector.copy();
        appVector.setPerformanceNeed(3);
        assertTrue(appVector.getPerformanceNeed() == 3);
        assertTrue(appVectorCopied.getPerformanceNeed() == 2);
    }
    
    @Test
    public void testSerialization() {
        try {
            appVector.saveProperties();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.serial"));
            oos.writeObject(appVector);
            oos.flush();
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.serial"));
            AppVector appVectorSerial = (AppVector) ois.readObject();
            ois.close();
            appVectorSerial.restoreProperties();
            assertFalse(appVector == null);
            assertFalse(appVectorSerial == null);
            assertTrue(appVector.getPerformanceNeed() == 2);
            assertTrue(appVectorSerial.getPerformanceNeed() == 2);
            assertTrue(appVectorSerial.equals(appVector));
        } catch (FileNotFoundException ex) {
            fail();
        } catch (IOException | ClassNotFoundException ex) {
            fail();
        }
    }
}
