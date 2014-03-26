/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thecloudbook.interfaces;

import java.io.Serializable;

/**
 *
 * @author Gwendal
 * command object specification
 */
public interface ISendCommand extends Serializable {
    void call(IClientService servant);
    boolean guard();
}
