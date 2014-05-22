package model.monitoring;

import model.engine.Engine;
import model.node.Mesure;

public class MonitoringType4 extends Monitoring{

    public MonitoringType4(Engine engine) {
        super(engine);
    }
    
    @Override
    protected Mesure genererMesure() {
        return new Mesure(engine.getNode(),
                getRandomInteger(0, 10),
                getRandomInteger(180, 190), 
                getRandomInteger(200, 220));
    }
}
