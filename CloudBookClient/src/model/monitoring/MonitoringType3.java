package model.monitoring;

import model.node.Mesure;
import model.node.MyNode;

public class MonitoringType3 extends Monitoring{

    public MonitoringType3(MyNode n) {
        super(n);
    }
    
    @Override
    protected Mesure genererMesure() {
        return new Mesure(node,
                getRandomInteger(30, 50),
                getRandomInteger(20, 60), 
                getRandomInteger(560, 600));
    }
}
