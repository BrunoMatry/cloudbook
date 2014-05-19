package hmi.content.node;

/**
 * methods shared by all customized graphical components
 */
public interface IComponentView {
    
    /**
     * display the component
     */
    public void display();
    
    /**
     * hide the component
     */
    public void hide();
    
    /**
     * update the component
     */
    public void update();
    
}
