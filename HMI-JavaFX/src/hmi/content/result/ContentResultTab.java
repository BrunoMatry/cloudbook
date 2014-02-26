/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.result;

import hmi.content.Content;

/**
 *
 * @author Bruno
 */
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
