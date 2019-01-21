package carsimulator.car.pedal;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Pedal {

    public ObjectProperty<State> stateProperty = new SimpleObjectProperty<>(State.IDLE);
    public BooleanProperty activeProperty = new SimpleBooleanProperty(false);

    public State getState() {
        return stateProperty.get();
    }

    public void setState(State state) {
        stateProperty.set(state);
        if (state.equals(State.IDLE)) {
            activeProperty.set(false);
        } else {
            activeProperty.set(true);
        }
    }

    public void toggleState() {
        if (getState().equals(State.PUSHING)) {
            setState(State.IDLE);
        } else {
            setState(State.PUSHING);
        }
    }

    public enum State {
        PUSHING, RELEASING, IDLE;
    }
}
