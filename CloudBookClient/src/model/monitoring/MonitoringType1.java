package model.monitoring;

import model.engine.Engine;
import model.node.Mesure;

public class MonitoringType1 extends Monitoring{

    public MonitoringType1(Engine engine) {
        super(engine);
    }
    
    @Override
    protected Mesure genererMesure() {
        return new Mesure(engine.getNode(),
                getRandomInteger(0, 20),
                getRandomInteger(220, 260), 
                getRandomInteger(400, 440));
    }
}
