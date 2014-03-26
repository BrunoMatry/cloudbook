/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thecloudbook.interfaces;

/**
 *
 * @author Gwendal
 * receiving object specification
 */
public interface IScheduler {
    void dispatch(ISendCommand command);
    
    /**
     * method which should be called with rmi
     * manages a received command
     * @param command command to manage
     */
    void onReceived(ISendCommand command);
}
