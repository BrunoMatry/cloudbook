/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.request;

import model.network.interfaces.Sendable;
import model.network.interfaces.Information;

/**
 *
 * @author Bruno
 */
public interface IRequestManager {
    Request createRequest(String target, Information data);
    void handleRequest(Sendable req);
}
