package carsimulator.car.gearbox;

import carsimulator.car.Car;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Gearbox {
    public ObjectProperty<Gear> currentGearProperty = new SimpleObjectProperty<>(Gear.N);
    private Car car;

    public Gearbox(Car car) {
        this.car = car;
    }

    public void gearUp() {
        for (Gear gear : Gear.values()) {
            if (gear.order == currentGearProperty.get().order + 1) {
                currentGearProperty.set(gear);
                int newRpm = (int) (car.getEngine().getRpm() * 0.8);
                car.getEngine().setRpm(newRpm);
                break;
            }
        }
    }

    public void gearDown() {
        for (Gear gear : Gear.values()) {
            if (gear.order == currentGearProperty.get().order - 1) {
                currentGearProperty.set(gear);
                int newRpm = (int) (car.getEngine().getRpm() * 1.2);
                car.getEngine().setRpm(newRpm);
                break;
            }
        }
    }

    public Gear getCurrentGear() {
        return currentGearProperty.get();
    }

    public enum Gear {
        R(0, 20, -1),
        N(0, 0, 0),
        G1(0, 20, 1),
        G2(10, 60, 2),
        G3(30, 80, 3),
        G4(50, 110, 4),
        G5(60, 140, 5);

        private final int minSpeed;
        private final int maxSpeed;
        private final int order;

        Gear(int minSpeed, int maxSpeed, int order) {
            this.minSpeed = minSpeed;
            this.maxSpeed = maxSpeed;
            this.order = order;
        }

        public int getMinSpeed() {
            return minSpeed;
        }

        public int getMaxSpeed() {
            return maxSpeed;
        }
    }
}
