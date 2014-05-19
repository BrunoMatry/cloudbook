package hmi.tab;

import hmi.content.result.ContentResultTab;

public class ResultTab extends Tab {
    
    public ResultTab(ContentResultTab c) {
        this.content = c;
        this.getChildren().add(content);
    }
}
