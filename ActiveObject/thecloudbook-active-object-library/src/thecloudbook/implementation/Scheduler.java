/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thecloudbook.implementation;

import thecloudbook.interfaces.IClientService;
import thecloudbook.interfaces.IScheduler;
import thecloudbook.interfaces.ISendCommand;
import thecloudbook.interfaces.ServantFactory;

/**
 *
 * @author Gwendal
 */
public class Scheduler implements IScheduler {

    protected ServantFactory sFactory;
    
    public Scheduler(ServantFactory sf) {
        sFactory = sf;
    }
    
    @Override
    public void dispatch(ISendCommand command) {
        IClientService s = sFactory.makeServant();
        command.call(s);
    }

    @Override
    public void onReceived(ISendCommand command) {
        if(command.guard())
            dispatch(command);
    }
    
}
