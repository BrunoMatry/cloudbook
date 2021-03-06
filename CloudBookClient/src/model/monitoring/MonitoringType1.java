package model.monitoring;

import model.node.Mesure;
import model.node.MyNode;

public class MonitoringType1 extends Monitoring{

    /**
     * Constructor
     * @param n application for which the mesures have to be token.
     */
    public MonitoringType1(MyNode n) {
        super(n);
    }
    
    @Override
    protected Mesure genererMesure() {
        return new Mesure(node,
                getRandomInteger(0, 20),
                getRandomInteger(220, 260), 
                getRandomInteger(400, 440));
    }
}
