/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.friendmanager;

import java.io.Serializable;

/**
 *
 * @author Bruno
 */
public interface Information extends Serializable {
    public void saveProperties();
    public void restoreProperties();
}
