package carsimulator.simulator.scene;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class LightIndicator extends Circle {
    static private final Color OFF_COLOR = Color.GRAY;
    static private final Color ON_COLOR = Color.GREENYELLOW;
    Property<Boolean> activeProperty = new SimpleBooleanProperty();

    public LightIndicator() {
        setFill(OFF_COLOR);
        setRadius(50.0);

        activeProperty.addListener(((observableValue, oldValue, newValue) -> {
            if (newValue) {
                setFill(ON_COLOR);
            } else {
                setFill(OFF_COLOR);
            }
        }));
    }
}
