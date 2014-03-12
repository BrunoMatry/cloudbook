/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.engine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.friendmanager.IFriendManager;
import model.monitoring.IMonitoring;
import model.request.IRequestManager;
import model.request.Request;
import modele.node.CloudBookNode;
import modele.node.Information;

/**
 *
 * @author Bruno
 */
public class Engine implements IEngine {
    private IRequestManager requestManager;
    private IFriendManager friendManager;
    private IMonitoring monitoring;
    private CloudBookNode node;

    public Engine() {
        requestManager = AppMounter.mountRequestManager();
        friendManager = AppMounter.mountFriendManager();
        monitoring = AppMounter.mountMonitoring();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("node_save.ser"));
            this.node = (CloudBookNode) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            this.node = new CloudBookNode();
        }
    }
    
    @Override
    public void save() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("node_save.ser"))) {
            oos.writeObject(this.node);
            oos.flush();
        }
    }

    @Override
    public void setInformation(Information info) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handleRequest(Request req) {
        requestManager.handleRequest(req);
    }
    
    private void shareInformation(){
        /* TODO */
    }
    
    private void updateInformation(){
        monitoring.pushInformation();
    }
}
