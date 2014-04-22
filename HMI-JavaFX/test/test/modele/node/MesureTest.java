package test.modele.node;

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
    Mesure m;
    
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
        m = new Mesure(1, 2, 3);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConstructeur() {
        assertTrue(m.mesure1Property().get() == 1);
        assertTrue(m.mesure2Property().get() == 2);
        assertTrue(m.mesure3Property().get() == 3);
    }
    
    @Test
    public void testSerialization() {
        try {
            m.saveProperties();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.serial"));
            oos.writeObject(m);
            oos.flush();
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.serial"));
            Mesure m1 = (Mesure) ois.readObject();
            ois.close();
            m1.restoreProperties();
            assertFalse(m == null);
            assertTrue(m1.equals(m));
        } catch (FileNotFoundException ex) {
            fail();
        } catch (IOException | ClassNotFoundException ex) {
            fail();
        }
    }
}
