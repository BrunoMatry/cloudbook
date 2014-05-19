package hmi.content.result;

import hmi.content.Content;

public class ContentResultTab extends Content {
    private SuggestionView suggestion;
    private ResultBoardView board;
    
    public ContentResultTab() {
        this.suggestion = new SuggestionView();
        this.board = new ResultBoardView();
        this.getChildren().add(suggestion);
        this.getChildren().add(board);
    }
}
