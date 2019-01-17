package carsimulator.car.gearbox;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


public class Gearbox {
    private ObjectProperty<Gear> currentGear = new SimpleObjectProperty<>(Gear.NEUTRAL);

    public enum Gear {
        G1, G2, G3, G4, G5, REVERSE, NEUTRAL
    }
}
