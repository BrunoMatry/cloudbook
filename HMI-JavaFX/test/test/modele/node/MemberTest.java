package test.modele.node;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import model.node.AppVector;
import model.node.Member;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class MemberTest {
    Member m1, m2;
    
    public MemberTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        m1 = new Member(1, 2, 1.5, new AppVector(1, 2, 3));
        m2 = new Member(4, 3, 1.2, new AppVector(1, 5, 3));
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSerialisation() throws InterruptedException {
        try {
            m1.saveProperties();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.serial"));
            oos.writeObject(m1);
            oos.flush();
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.serial"));
            Member m3 = (Member) ois.readObject();
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