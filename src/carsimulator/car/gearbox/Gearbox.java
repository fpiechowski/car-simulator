package carsimulator.car.gearbox;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Gearbox {
    public ObjectProperty<Gear> currentGearProperty = new SimpleObjectProperty<>(Gear.N);

    public void gearUp() {
        throw new NotImplementedException();
    }

    public void gearDown() {
        throw new NotImplementedException();
    }

    public enum Gear {
        G1, G2, G3, G4, G5, R, N;
    }
}
