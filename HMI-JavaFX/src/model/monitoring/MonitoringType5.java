package model.monitoring;

import model.engine.Engine;
import model.node.Mesure;

public class MonitoringType5 extends Monitoring{

    public MonitoringType5(Engine engine) {
        super(engine);
    }
    
    @Override
    protected Mesure genererMesure() {
        return new Mesure(engine.getNode(),
                getRandomInteger(80, 100),
                getRandomInteger(800, 840), 
                getRandomInteger(10, 30));
    }
}
