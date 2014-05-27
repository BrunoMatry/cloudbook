package test.model.node;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.node.Mesure;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MesureTest {
    Mesure mesure;
    
    public MesureTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        mesure = new Mesure(null, 1, 2, 3);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConstructeur() {
        assertTrue(mesure.mesure1Property().get() == 1);
        assertTrue(mesure.mesure2Property().get() == 2);
        assertTrue(mesure.mesure3Property().get() == 3);
    }
    
    @Test
    public void testSerialization() {
        try {
            mesure.saveProperties();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.serial"));
            oos.writeObject(mesure);
            oos.flush();
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.serial"));
            Mesure mesureSerial = (Mesure) ois.readObject();
            ois.close();
            mesureSerial.restoreProperties();
            assertFalse(mesure == null);
            assertTrue(mesureSerial.equals(mesure));
        } catch (FileNotFoundException ex) {
            fail();
        } catch (IOException | ClassNotFoundException ex) {
            fail();
        }
    }
}
