package hmi.home;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;


/**
 * singleton
 */
public final class ObservableFileList extends Observable {

    public static final ObservableFileList INSTANCE = new ObservableFileList();
    
    //observed files
    protected List<File> files;
    
    /**
     * Constructor
     */
    private ObservableFileList() {
        super();
        this.files = new ArrayList<>();
        File folder = new File(".");
        File[] allFiles = folder.listFiles();
        this.files.addAll(Arrays.asList(allFiles));
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
     * Add a file serialization of an object
     * @param o object to be serialized
     * @param fileName name of the serialization file
     * @throws IOException in case of problem with serialization
     */
    public void add(Object o, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(o);
            oos.flush();
            oos.close();
        }
        File toBeAdded = new File(fileName);
        boolean found = false;
        for(File f : this.files) {
            if(f.getName().equals(toBeAdded.getName())) {
                f = toBeAdded;
                found = true;
            }
        }
        if(!found) {
            this.files.add(toBeAdded);
            setChanged();
            notifyObservers();
        }
        fos.flush();
        fos.close();
    }
    
    /**
     * Getter
     * @return file list
     */
    public final List<File> getFiles() {
        return files;
    }
    
}
