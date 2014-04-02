/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.network.implementation.clientServer.ClientServerFactory;
import model.request.Request;
import thecloudbook.implementation.ProxyClientService;
import thecloudbook.implementation.Servant;

/**
 *
 * @author Gwendal
 */
public class ServerServant extends Servant {

    @Override
    public void run() {
        Request req = (Request) request;
        String url = "rmi://" + req.getRecipent() + ":" +
                ClientServerFactory.PORT + "/" + ClientServerFactory.NAME;
        try {
            ProxyClientService serverForwarder = new ProxyClientService(url);
            serverForwarder.send(req);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(ServerServant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
