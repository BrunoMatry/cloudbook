/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.result;

import hmi.content.HMIContent;

/**
 *
 * @author Bruno
 */
public class HMIContentResultTab extends HMIContent {
    private HMISuggestion suggestion;
    private HMIResultBoard board;
    
    public HMIContentResultTab() {
        this.suggestion = new HMISuggestion();
        this.board = new HMIResultBoard();
    }
}
