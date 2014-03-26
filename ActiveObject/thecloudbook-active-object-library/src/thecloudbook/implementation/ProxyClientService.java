/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thecloudbook.implementation;

import thecloudbook.interfaces.IClientService;
import thecloudbook.interfaces.IScheduler;
import thecloudbook.interfaces.ISendCommand;
import thecloudbook.interfaces.Sendable;

/**
 *
 * @author Gwendal
 */
public class ProxyClientService implements IClientService {

    //distant scheduler, which must be called using RMI
    protected IScheduler scheduler;
    
    public ProxyClientService(IScheduler sched) {
        scheduler = sched;
    }
    
    @Override
    public void send(Sendable sendable) {
        ISendCommand command = new SendCommand();
        scheduler.onReceived(command);
    }
    
}
