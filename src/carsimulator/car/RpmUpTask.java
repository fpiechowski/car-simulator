package carsimulator.car;

import carsimulator.car.engine.Engine;
import carsimulator.car.pedal.Pedal;
import javafx.application.Platform;
import javafx.concurrent.Task;

import java.util.logging.Logger;

public class RpmUpTask extends Task<Integer> {

    /**
     * Wartość o którą będą wzrastały obroty.
     */
    private static final int RPM_DELTA = 100;
    /**
     * Częstotliwość wzrastania obrotóœ w milisekundach.
     */
    private static final long REFRESH_RATE = 200;

    private final Car car;

    public RpmUpTask(Car car) {
        this.car = car;
    }

    @Override
    protected Integer call() throws InterruptedException {
        Logger.getGlobal().info("RPM rising...");

        while (car.getEngine().isRunning() && car.getAccelerationPedal().getState().equals(Pedal.State.PUSHING)) {
            if (car.getEngine().getRpm() < Engine.MAX_RPM) {
                int currentRpm = car.getEngine().getRpm();
                Platform.runLater(() -> car.getEngine().setRpm(currentRpm + RPM_DELTA));

            } else {
                Platform.runLater(() -> car.getEngine().setRpm(Engine.MAX_RPM));
            }

            Thread.sleep(REFRESH_RATE);
        }

        return car.getEngine().getRpm();
    }
}
