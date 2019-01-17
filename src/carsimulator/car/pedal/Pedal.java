package carsimulator.car.pedal;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;

/**
 * Represents car's pedal that can be gradually pushed and released instantly.
 */
public class Pedal extends Thread {
    private FloatProperty pushPercentage = new SimpleFloatProperty(0);
    private BooleanProperty pushed = new SimpleBooleanProperty(false);
    private long pushedTimeMillis = 0;

    float getPushPercentage() {
        return pushPercentage.get();
    }

    public void push() {
        pushed.setValue(true);
        pushedTimeMillis = System.currentTimeMillis();
        start();
    }

    public void release() {
        pushed.setValue(false);
        pushPercentage.setValue(0);
    }

    public boolean isPushed() {
        return pushed.get();
    }

    /**
     * Thread run when a pedal is pushed.
     *
     * It changes pedal's push percentage value linearly for a given amount of time as long as the pedal is in pushed
     * state. This thread finishes when the pedal is released.
     */
    class PedalPushThread extends Thread {
        private static final long FULL_PUSH_DURATION_MILLIS = 3000;

        @Override
        public void run() {
            while (Pedal.this.isPushed()) {
                if (getPushPercentage() < 1) {
                    long pushDuration = (System.currentTimeMillis() - pushedTimeMillis) / 1000;
                    pushPercentage.setValue(1. / (FULL_PUSH_DURATION_MILLIS / 1000) * pushDuration);
                } else {
                    pushPercentage.setValue(1);
                }
            }
        }
    }
}