package test.modele.node;

import modele.node.AppVector;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AppVectorTest {
    
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
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConstructeur() {
        AppVector appVector = new AppVector(1, 2, 3);
        assertTrue(appVector.appTypeProperty().get() == 1);
        assertTrue(appVector.performanceProperty().get() == 2);
        assertTrue(appVector.speedProperty().get() == 3);
    }
    
    @Test
    public void testCopy() {
        AppVector appVector = new AppVector(1, 2, 3);
        AppVector appVector2 = appVector.copy();
        appVector.performanceProperty().set(3);
        assertTrue(appVector.performanceProperty().get() == 3);
        assertTrue(appVector2.performanceProperty().get() == 2);
    }
}
