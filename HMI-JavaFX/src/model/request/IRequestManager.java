package model.request;

import java.util.List;
import model.network.interfaces.Sendable;
import model.network.interfaces.Information;

public interface IRequestManager {
    
    Request createRequest(Information data);
    List<Request> createRequests(List<Information> l);
    void handleRequest(Sendable req);
}
