package carsimulator.car;

import carsimulator.car.pedal.Pedal;
import javafx.application.Platform;
import javafx.concurrent.Task;

import java.util.logging.Logger;

public class RpmDownTask extends Task<Integer> {

    /**
     * Wartość o którą będą malały obroty.
     */
    private static final int RPM_DELTA = 100;
    /**
     * Częstotliwość spadania obrotóœ w milisekundach.
     */
    private static final long REFRESH_RATE = 200;

    private final Car car;

    public RpmDownTask(Car car) {
        this.car = car;
    }

    @Override
    protected Integer call() throws InterruptedException {
        Logger.getGlobal().info("RPM lowering...");

        while (car.getEngine().isRunning() && car.getAccelerationPedal().getState().equals(Pedal.State.RELEASING)) {
            if (car.getEngine().getRpm() > 0) {
                int currentRpm = car.getEngine().getRpm();
                Platform.runLater(() -> car.getEngine().setRpm(currentRpm - RPM_DELTA));

            } else {
                Platform.runLater(() -> car.getEngine().setRpm(0));
            }

            Thread.sleep(REFRESH_RATE);
        }

        return car.getEngine().getRpm();
    }
}
