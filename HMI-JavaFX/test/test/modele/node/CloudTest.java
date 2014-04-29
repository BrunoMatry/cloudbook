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
        cloud = Cloud.DROPBOX;
        cloud2 = Cloud.GDRIVE;
        cloud3 = Cloud.SKYDRIVE;
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConstructeur() {
        cloud.equals(Cloud.DROPBOX);
        cloud2.equals(Cloud.GDRIVE);
        cloud3.equals(Cloud.SKYDRIVE);
    }
}
