/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thecloudbook.interfaces;

import model.request.Sendable;

/**
 *
 * @author Gwendal
 */
public interface IServant {
    public void send(Sendable sendable);
}
