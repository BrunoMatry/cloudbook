package model.monitoring;

import model.engine.Engine;
import model.node.Mesure;

public class MonitoringType2 extends Monitoring{

    public MonitoringType2(Engine engine) {
        super(engine);
    }
    
    @Override
    protected Mesure genererMesure() {
        return new Mesure(engine.getNode(),
                getRandomInteger(40, 60),
                getRandomInteger(100, 140), 
                getRandomInteger(180, 220));
    }
}
