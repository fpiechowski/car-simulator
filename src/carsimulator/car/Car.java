package carsimulator.car;

import carsimulator.car.engine.Engine;
import carsimulator.car.gearbox.Gearbox;
import carsimulator.car.pedal.Pedal;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Car {
    public static final int MAX_SPEED = 160;

    public IntegerProperty speedProperty = new SimpleIntegerProperty(0);
    public DoubleProperty speedPercentageProperty = new SimpleDoubleProperty(0);
    private Pedal accelerationPedal = new Pedal();
    private Pedal brakePedal = new Pedal();
    private Gearbox gearbox = new Gearbox(this);
    private Engine engine = new Engine(this);

    public Car() {
        speedProperty.addListener((observableValue, oldValue, newValue) -> speedPercentageProperty.set(newValue.doubleValue() / MAX_SPEED));

        Thread speedTaskThread = new Thread(new AdjustSpeedTask(this));
        speedTaskThread.setDaemon(true);
        speedTaskThread.start();
    }

    public Pedal getAccelerationPedal() {
        return accelerationPedal;
    }

    public Pedal getBrakePedal() {
        return brakePedal;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public Engine getEngine() {
        return engine;
    }


    public int getSpeed() {
        return speedProperty.get();
    }

    public synchronized void setSpeed(int speed) {
        speedProperty.set(speed);
    }

    public void start() {
        engine.ignite();
    }

    public void stop() {
        engine.stub();
    }
}