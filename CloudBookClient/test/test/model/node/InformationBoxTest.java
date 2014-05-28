package test.model.node;

import junit.framework.Assert;
import model.network.interfaces.Information;
import model.node.InformationBox;
import org.junit.BeforeClass;
import org.junit.Test;

public class InformationBoxTest {
    
    public InformationBoxTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }

    @Test
    public void PushTest() {
        InformationBox<Information> infoBox = new InformationBox<>();
        Assert.assertTrue(infoBox.empty());
        Assert.assertEquals(InformationBox.EMPTY_FLAG, infoBox.descriptionProperty().get());
        infoBox.push(new TestInformation("Alice"));
        Assert.assertFalse(infoBox.empty());
        Assert.assertEquals("Alice", infoBox.descriptionProperty().get());
        infoBox.push(new TestInformation("Bob"));
        Assert.assertFalse(infoBox.empty());
        Assert.assertEquals("Bob", infoBox.descriptionProperty().get());
    }
    
    @Test
    public void PopTest() {
        InformationBox<Information> infoBox = new InformationBox<>();
        Information alice = new TestInformation("Alice");
        Information bob = new TestInformation("Bob");
        infoBox.push(alice);
        infoBox.push(bob);
        Information first = infoBox.pop();
        Assert.assertEquals(bob, first);
        Assert.assertEquals("Alice", infoBox.descriptionProperty().get());
        Information second = infoBox.pop();
        Assert.assertEquals(alice, second);
        Assert.assertEquals(InformationBox.EMPTY_FLAG, infoBox.descriptionProperty().get());
        Assert.assertTrue(infoBox.empty());
    }
    
    @Test
    public void RemoveTest() {
        InformationBox<Information> infoBox = new InformationBox<>();
        Information alice = new TestInformation("Alice");
        Information bob = new TestInformation("Bob");
        infoBox.push(alice);
        infoBox.push(bob);
        infoBox.remove(alice);
        Assert.assertEquals("Bob", infoBox.descriptionProperty().get());
        infoBox.remove(bob);
        Assert.assertEquals(InformationBox.EMPTY_FLAG, infoBox.descriptionProperty().get());
        Assert.assertTrue(infoBox.empty());
    }
    
    private static final class TestInformation implements Information {

        private String label;
        
        public TestInformation(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
        
        @Override
        public void saveProperties() {
        }

        @Override
        public void restoreProperties() {
        }
        
    }
}
