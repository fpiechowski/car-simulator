package carsimulator.simulator.scene;

import carsimulator.car.pedal.Pedal;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

class PedalPane extends HBox {
    private LightIndicator accelerationPedalLightIndicator = new LightIndicator();
    private LightIndicator brakePedalLightIndicator = new LightIndicator();

    PedalPane(Pedal accelerationPedal, Pedal brakePedal) {
        bindControls(accelerationPedal, brakePedal);

        setAlignment(Pos.CENTER);
        setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
        setSpacing(50);

        getChildren().add(brakePedalLightIndicator);
        getChildren().add(accelerationPedalLightIndicator);
    }

    private void bindControls(Pedal accelerationPedal, Pedal brakePedal) {
        brakePedalLightIndicator.activeProperty.bind(brakePedal.activeProperty);
        accelerationPedalLightIndicator.activeProperty.bind(accelerationPedal.activeProperty);
    }
}
