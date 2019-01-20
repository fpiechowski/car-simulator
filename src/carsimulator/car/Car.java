package carsimulator.car;

import carsimulator.car.engine.Engine;
import carsimulator.car.gearbox.Gearbox;
import carsimulator.car.pedal.Pedal;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Car extends Thread {
    public IntegerProperty speedProperty = new SimpleIntegerProperty(0);
    private Pedal accelerationPedal = new Pedal();
    private Pedal brakePedal = new Pedal();
    private Pedal clutchPedal = new Pedal();
    private Gearbox gearbox = new Gearbox();
    private Engine engine = new Engine(this);

    public Pedal getAccelerationPedal() {
        return accelerationPedal;
    }

    public Pedal getBrakePedal() {
        return brakePedal;
    }

    public Pedal getClutchPedal() {
        return clutchPedal;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public Engine getEngine() {
        return engine;
    }

    @Override
    public void start() {
        try {
            engine.ignite();
            Logger.getGlobal().info("Car started.");
        } catch (Engine.EngineIgnitionException e) {
            Logger.getGlobal().warning("Can't ignite engine. " + e.getLocalizedMessage());
        }
    }
}
