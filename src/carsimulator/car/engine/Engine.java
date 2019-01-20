package carsimulator.car.engine;

import carsimulator.car.Car;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Engine {

    private static final int MIN_RUNNING_RPM = 10;

    public IntegerProperty rpmProperty = new SimpleIntegerProperty(0);
    public BooleanProperty runningProperty = new SimpleBooleanProperty(false);
    private Car car;

    public Engine(Car car) {
        this.car = car;
    }

    public void ignite() throws EngineIgnitionException {
        if (!car.getClutchPedal().isPushed()) {
            throw new EngineIgnitionException("Clutch pedal not pushed on engine ignition!");
        }

        runningProperty.set(true);
        rpmProperty.set(MIN_RUNNING_RPM);
    }

    public class EngineIgnitionException extends RuntimeException {
        EngineIgnitionException(String s) {
            super(s);
        }
    }
}
