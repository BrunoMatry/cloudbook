package model.monitoring;

import model.engine.Engine;
import model.node.Mesure;

public class MonitoringType3 extends Monitoring{

    public MonitoringType3(Engine engine) {
        super(engine);
    }
    
    @Override
    protected Mesure genererMesure() {
        return new Mesure(engine.getNode(),
                getRandomInteger(30, 50),
                getRandomInteger(20, 60), 
                getRandomInteger(560, 600));
    }
}
