package model.monitoring;

import model.node.Mesure;
import model.node.MyNode;

public class MonitoringType5 extends Monitoring{

    public MonitoringType5(MyNode n) {
        super(n);
    }
    
    @Override
    protected Mesure genererMesure() {
        return new Mesure(node,
                getRandomInteger(80, 100),
                getRandomInteger(800, 840), 
                getRandomInteger(10, 30));
    }
}
