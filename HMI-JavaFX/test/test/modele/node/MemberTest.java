package test.modele.node;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    Member member;
    
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
        member = new Member(1, 2, 1.5, new AppVector(1, 2, 3));
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testConstructor() {
        assertTrue(member.getId() == 1);
        assertTrue(member.confidenceProperty().get() == 2);
        assertTrue(member.relevanceProperty().get() == 1.5);
        assertTrue(member.getAppVector().equals(new AppVector(1, 2, 3)));
    }
    
    @Test
    public void testDaySinceLastConnexion() {
        assertTrue(member.daysSinceLastConnection() == 0);
    }
    
    @Test
    public void testSetters() {
        Member member2 = new Member(4, 3, 1.2, new AppVector(1, 5, 3));
        assertTrue(member2.getId() == 4);
        assertTrue(member2.confidenceProperty().get() == 3);
        member2.setConfidence(4);
        assertTrue(member2.confidenceProperty().get() == 4);
        assertTrue(member2.relevanceProperty().get() == 1.2);
        member2.setRelevance(3.6);
        assertTrue(member2.relevanceProperty().get() == 3.6);
        assertTrue(member2.getAppVector().equals(new AppVector(1, 5, 3)));
        member2.setVector(new AppVector(2, 4, 9));
        assertTrue(member2.getAppVector().equals(new AppVector(2, 4, 9)));
    }

    @Test
    public void testSerialisation() {
        try {
            member.saveProperties();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.serial"));
            oos.writeObject(member);
            oos.flush();
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.serial"));
            Member memberSerial = (Member) ois.readObject();
            ois.close();
            memberSerial.restoreProperties();
            assertFalse(member == null);
            assertTrue(member.equals(memberSerial));
        } catch (FileNotFoundException ex) {
            fail();
        } catch (IOException | ClassNotFoundException ex) {
            fail();
        }
    }
}