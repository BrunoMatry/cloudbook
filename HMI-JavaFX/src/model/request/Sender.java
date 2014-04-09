package model.request;

import model.network.interfaces.ISender;
import model.node.AppVector;


public class Sender implements ISender {
    private int _id;
    private AppVector _vector;
    
    public int getId() {
        return _id;
    }
    
    public AppVector getVector() {
        return _vector;
    }
}
