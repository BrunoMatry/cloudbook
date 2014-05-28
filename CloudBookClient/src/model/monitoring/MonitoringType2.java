package model.monitoring;

import model.node.Mesure;
import model.node.MyNode;

public class MonitoringType2 extends Monitoring{

    public MonitoringType2(MyNode n) {
        super(n);
    }
    
    @Override
    protected Mesure genererMesure() {
        return new Mesure(node,
                getRandomInteger(40, 60),
                getRandomInteger(100, 140), 
                getRandomInteger(180, 220));
    }
}
