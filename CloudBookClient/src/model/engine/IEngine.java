package model.engine;

import java.io.IOException;
import model.network.interfaces.Information;
import model.network.interfaces.RemoteClient;
import model.network.interfaces.Sendable;

public interface IEngine {
    
    /**
     * Capture a query that is returned by the network block
     * @param req the query
     */
    void handleRequest(Sendable req);
    
    /**
     * Save the current node in a file
     * @throws IOException 
     */
    void save() throws IOException;
    
    /**
     * Deprecated - Set information manually
     * @param info the information to add in the node
     */
    void setInformation(Information info);
    
    /**
     * Setter - modifiy the current network
     * @param network new network
     */
    void setNetwork(RemoteClient network);
}
