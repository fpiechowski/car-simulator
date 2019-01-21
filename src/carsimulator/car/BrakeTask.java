package carsimulator.car;

import carsimulator.car.pedal.Pedal;
import javafx.application.Platform;
import javafx.concurrent.Task;

import java.util.logging.Logger;

public class BrakeTask extends Task<Void> {
    /**
     * Częstotliwość hamowania w milisekundach.
     */
    private static final long REFRESH_RATE = 200;

    /**
     * Wartość o którą będzie spadała prędkość.
     */
    private static final int SPEED_DELTA = 5;
    private Car car;

    public BrakeTask(Car car) {
        this.car = car;
    }

    @Override
    protected Void call() throws Exception {
        Logger.getGlobal().info("Braking...");

        while (car.getBrakePedal().getState().equals(Pedal.State.PUSHING)) {
            if (car.getSpeed() > 0) {
                int currentSpeed = car.getSpeed();
                Platform.runLater(() -> car.setSpeed(currentSpeed - BrakeTask.SPEED_DELTA));
            } else {
                Platform.runLater(() -> car.setSpeed(0));
            }

            Thread.sleep(REFRESH_RATE);
        }

        return null;
    }
}
