package model.request;

import java.util.List;
import model.network.interfaces.Sendable;
import model.network.interfaces.Information;

public interface IRequestManager {
    
    Request createRequest(Information data);
    <I extends Information> List<Request> createRequests(List<I> l);
    void handleRequest(Sendable req);
}
