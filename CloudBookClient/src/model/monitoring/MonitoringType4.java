package model.monitoring;

import model.node.Mesure;
import model.node.MyNode;

public class MonitoringType4 extends Monitoring{

    public MonitoringType4(MyNode n) {
        super(n);
    }
    
    @Override
    protected Mesure genererMesure() {
        return new Mesure(node,
                getRandomInteger(0, 10),
                getRandomInteger(180, 190), 
                getRandomInteger(200, 220));
    }
}
