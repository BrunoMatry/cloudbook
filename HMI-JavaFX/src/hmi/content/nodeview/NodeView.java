/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.nodeview;

import hmi.content.HMIContent;
import java.util.ArrayList;
import model.friendmanager.CloudBookNode;

/**
 *
 * @author Gwendal
 */
public class NodeView extends HMIContent {
    
    //TODO : use 
    //model representation of the current node view
    private CloudBookNode model;
    private ArrayList<NodeComponentView> components;
    
}
