package carsimulator.car;

import carsimulator.car.engine.Engine;
import carsimulator.car.gearbox.Gearbox;
import javafx.application.Platform;
import javafx.concurrent.Task;

public class AdjustSpeedTask extends Task<Integer> {

    private static final int SPEED_DELTA = 3;
    private static final int REFRESH_RATE = 300;
    private Car car;

    public AdjustSpeedTask(Car car) {
        this.car = car;
    }

    @Override
    protected Integer call() throws Exception {

        while (true) {
            int goalSpeed = calcGoalSpeed(car.getGearbox().getCurrentGear(), car.getEngine());
            int currentSpeed = car.getSpeed();

            if (currentSpeed < goalSpeed) {
                Platform.runLater(() -> car.setSpeed(currentSpeed + SPEED_DELTA));
            } else if (currentSpeed > goalSpeed) {
                Platform.runLater(() -> car.setSpeed(currentSpeed - SPEED_DELTA));
            }

            Thread.sleep(REFRESH_RATE);
        }
    }

    private int calcGoalSpeed(Gearbox.Gear gear, Engine engine) {
        if (engine.isRunning()) {
            int speedDelta = gear.getMaxSpeed() - gear.getMinSpeed();
            int speedAddition = (int) (speedDelta * engine.rpmPercentageProperty.get());
            return gear.getMinSpeed() + speedAddition;
        } else {
            return 0;
        }
    }
}
