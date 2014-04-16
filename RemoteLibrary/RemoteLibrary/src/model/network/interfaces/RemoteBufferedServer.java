/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.network.interfaces;

import java.rmi.Remote;
import java.util.List;

/**
 *
 * @author Gwendal
 * interface for a remote server which stores the received Sendables
 * producer/consumer
 */
public interface RemoteBufferedServer extends RemoteServer, Remote {
    List<Sendable> getSendable(String receiver);
}
