package carsimulator.car.engine;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;

public class Engine extends Thread {
    private BooleanProperty running = new SimpleBooleanProperty(false);
    private FloatProperty force = new SimpleFloatProperty(0);

    public boolean isRunning() {
        return running.get();
    }

    public void setRunning(boolean running) {
        this.running.set(running);
    }


    @Override
    public void run() {
        setRunning(true);
        while (isRunning()) {
            updateForce();
        }
    }

    private void updateForce() {
        // TODO
    }
}
