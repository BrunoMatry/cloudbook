package hmi.tab;

import hmi.content.result.ContentResultTab;

/**
 *
 * @author Bruno
 */
public class ResultTab extends Tab {
    
    public ResultTab(ContentResultTab c) {
        this.content = c;
        this.getChildren().add(content);
    }
}
