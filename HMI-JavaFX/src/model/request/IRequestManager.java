/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.request;

import modele.node.Information;

/**
 *
 * @author Bruno
 */
public interface IRequestManager {
    Request createRequest(int target, Information data);
    void handleRequest(Request req);
}
