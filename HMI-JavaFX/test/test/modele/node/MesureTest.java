package test.modele.node;

import model.node.Mesure;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MesureTest {
    
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
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConstructeur() {
        Mesure m = new Mesure(1, 2, 3);
        assertTrue(m.mesure1Property().get() == 1);
        assertTrue(m.mesure2Property().get() == 2);
        assertTrue(m.mesure3Property().get() == 3);
    }

}
