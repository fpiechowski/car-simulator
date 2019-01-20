package carsimulator.car.pedal;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Pedal {

    public BooleanProperty pushedProperty = new SimpleBooleanProperty(false);

    public void togglePush() {
        throw new NotImplementedException();
    }

    public boolean isPushed() {
        return pushedProperty.get();
    }
}
