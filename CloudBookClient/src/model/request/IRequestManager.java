package model.request;

import java.util.List;
import model.network.interfaces.Sendable;
import model.network.interfaces.Information;

public interface IRequestManager {
    
    /**
     * Create a request
     * @param data the data to put in the request
     * @return the request
     */
    Request createRequest(Information data);
    
    /**
     * Create a list of requests
     * @param <I> information or hertiage of information
     * @param l list of inofrmations to put in requests
     * @return a list of request
     */
    <I extends Information> List<Request> createRequests(List<I> l);
    
    /**
     * Handle a request and do what it had to do
     * @param req the request to handle
     */
    void handleRequest(Sendable req);
}
