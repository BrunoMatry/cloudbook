package test.modele.node;

import model.node.Cloud;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CloudTest {
    
    Cloud cloud, cloud2, cloud3;
    
    public CloudTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        cloud = Cloud.AMAZON;
        cloud2 = Cloud.GOOGLE;
        cloud3 = Cloud.WINDOWS;
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConstructeur() {
        cloud.equals(Cloud.AMAZON);
        cloud2.equals(Cloud.GOOGLE);
        cloud3.equals(Cloud.WINDOWS);
    }
}
