package hmi.content.node;

import model.node.MyNode;

/**
 * methods shared by all customized graphical components
 */
public interface IComponentView {
    
    /**
     * Updates the view component to be related to the specified node
     * @param node node to be represented
     */
    void bind(MyNode node);
    
}
