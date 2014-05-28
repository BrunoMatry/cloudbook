package model.node;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.engine.Engine;


/**
 * Design pattern : singleton
 */
public final class FileEngineRelation extends Observable {

    public static final FileEngineRelation INSTANCE = new FileEngineRelation();
    
    //observed files
    private Map<File, Engine> relation;
    
    //Current node selected by the user
    private Engine currentEngine;
    
    /**
     * Constructor
     * Reads the file system at the root of the application and assosciates
     * engines to the files which are serialized versions of nodes
     */
    private FileEngineRelation() {
        super();
        this.relation = new HashMap<>();
        File folder = new File(".");
        File[] allFiles = folder.listFiles();
        for (File f : allFiles) {
            try {
                put(f);
            } catch (    IOException | ClassNotFoundException ex) {
                Logger.getLogger(FileEngineRelation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Remove the specified file from the system and the current list
     * Observers are notified
     * @param file file to be removed
     */
    public void remove(File file) {
        File toBeDeleted = null;
        for(File f : this.relation.keySet()) {
            if(f.getName().equals(file.getName()))
                toBeDeleted = f;
        }
        if(toBeDeleted != null) {
            this.relation.remove(toBeDeleted);
            toBeDeleted.delete();
            setChanged();
            notifyObservers();
        }
    }
    
    /**
     * Saves an object in a file of specified name
     * The file is saved in the file system and in the application
     * @param o object to be serialized
     * @param fileName name of the serialization file
     * @throws IOException in case of problem with serialization
     * @throws java.lang.ClassNotFoundException The serialization file doesn't correspond to the class MyNode
     */
    public void save(Object o, String fileName) throws IOException, ClassNotFoundException {
        
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(o);
                oos.flush();
                oos.close();
            }
            fos.flush();
        }
        
        /* If the file is registered in the application,
           the engine is kept and only the file change.
           Otherwise, a new entry is added.
        */
        File toBeAdded = new File(fileName);
        File existing = getFileByName(fileName);
        if(existing == null) { 
            put(toBeAdded);
            setChanged();
            notifyObservers();
        } else {
            Engine engine = this.relation.get(existing);
            this.relation.remove(existing);
            this.relation.put(toBeAdded, engine);
        }
    }
    
    /**
     * Puts a serialized node file and associates the corresponding engine
     * The method does nothing if the extension is not ".ser"
     * @param key file to be added
     */
    private void put(File key) throws IOException, ClassNotFoundException {
        String name = key.getName();
        if(name.endsWith(".ser")) {
            MyNode node = MyNode.load(name);
            Engine engine = new Engine(node);
            this.relation.put(key, engine);
        }
    }
    
    /**
     * Returns the set of serialization files
     * @return the set of serialization files
     */
    public Set<File> keySet() {
        return this.relation.keySet();
    }
    
    /**
     * Tells if the file of the specified name is in the serialization files
     * @param name name of the lookes for file
     * @return true if the file of the specified name is present, false otherwise
     */
    public boolean containsFileOfName(String name) {
        for(File f : this.relation.keySet()) {
            if(f.getName().equals(name))
                return true;
        }
        return false;
    }
    
    /**
     * Looks for the file with the specified name
     * @param name name of the looked for file
     * @return the file with the specified name if it exists, otherwise returns null
     */
    public File getFileByName(String name) {
        for(File f : this.relation.keySet()) {
            if(f.getName().equals(name))
                return f;
        }
        return null;
    }
    
    /**
     * Selects the specified node as the current one
     * @param name name of the node to select
     * @return selected node
     */
    public Engine select(String name) {
        for(Engine engine : this.relation.values()) {
            if(engine.getNode().nameProperty().get().equals(name)) {
                currentEngine = engine;
                break;
            }
        }
        return currentEngine;
    }

    /**
     * Getter
     * @return get the current node
     */
    public Engine getCurrentEngine() {
        return currentEngine;
    }

    /**
     * Verifies if the node is related to an engine in the list
     * @param node node to look for
     * @return true if there is an engine related to the node, false otherwise
     */
    public boolean containsNode(MyNode node) {
        for(Engine engine : this.relation.values()) {
            if(node.nameProperty().get().equals(engine.getNode().nameProperty().get()))
                return true;
        }
        return false;
    }
    
    /**
     * Removes the file and associated engine of name name
     * @param name name of the file which has to be removed
     */
    public void removeByName(String name) {
        for(File f : this.relation.keySet()) {
            if(f.getName().equals(name)) {
                remove(f);
                break;
            }
        }
    }
    
    /**
     * Returns all the registered engines
     * @return all the registerd engines
     */
    public Collection<Engine> values() {
        return this.relation.values();
    }
    
}
