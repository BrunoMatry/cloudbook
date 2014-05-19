/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.home;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;


/**
 *
 * @author Gwendal
 */
public class ObservableFileList extends Observable {

    //observed files
    protected List<File> files;
    
    /**
     * Constructor
     * @param files files to be added to the list
     */
    public ObservableFileList(File[] files) {
        super();
        this.files = new ArrayList<>();
        this.files.addAll(Arrays.asList(files));
    }
    
    /**
     * Remove the specified file from the system and the current list
     * Observers are notified
     * @param file file to be removed
     */
    public void remove(File file) {
        File toBeDeleted = null;
        for(File f : this.files) {
            if(f.getName().equals(file.getName()))
                toBeDeleted = f;
        }
        if(toBeDeleted != null) {
            this.files.remove(toBeDeleted);
            toBeDeleted.delete();
            setChanged();
            notifyObservers();
        }
    }
    
    /**
     * Getter
     * @return file list
     */
    public final List<File> getFiles() {
        return files;
    }
    
}
