package carsimulator.car.engine;

import carsimulator.car.Car;
import javafx.beans.property.*;

import java.util.logging.Logger;

public class Engine {

    public static final int MAX_RPM = 5000;
    private static final int MIN_RUNNING_RPM = 1000;

    public IntegerProperty rpmProperty = new SimpleIntegerProperty(0);
    public DoubleProperty rpmPercentageProperty = new SimpleDoubleProperty(0);
    public BooleanProperty runningProperty = new SimpleBooleanProperty(false);

    private Car car;

    public Engine(Car car) {
        this.car = car;

        rpmProperty.addListener((observableValue, oldValue, newValue) -> rpmPercentageProperty.set(newValue.doubleValue() / MAX_RPM));
    }

    public void ignite() {
        runningProperty.set(true);
        rpmProperty.set(MIN_RUNNING_RPM);

        Logger.getGlobal().info("Engine ignited.");
    }

    public void stub() {
        runningProperty.set(false);
        rpmProperty.set(0);

        Logger.getGlobal().info("Engine stubbed.");
    }

    public boolean isRunning() {
        return runningProperty.get();
    }

    public int getRpm() {
        return rpmProperty.get();
    }

    public void setRpm(int rpm) {
        rpmProperty.set(rpm);
    }
}
