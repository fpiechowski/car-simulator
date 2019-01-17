package carsimulator.car;

import carsimulator.car.brake.Brake;
import carsimulator.car.engine.Engine;
import carsimulator.car.gearbox.Gearbox;
import carsimulator.car.pedal.Pedal;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

public class Car extends Thread {
    private int mass = 1000;
    private FloatProperty speed = new SimpleFloatProperty(0);
    private FloatProperty acceleration = new SimpleFloatProperty(0);

    private Engine engine = new Engine();
    private Pedal accelerationPedal = new Pedal();
    private Pedal brakePedal = new Pedal();
    private Pedal clutchPedal = new Pedal();
    private Gearbox gearbox = new Gearbox();
    private Brake brake = new Brake();

    @Override
    public void run() {
        engine.start();
        while(engine.isRunning()) {
            updateVelocity();
        }
    }

    private void updateVelocity() {
        // TODO
    }
}
