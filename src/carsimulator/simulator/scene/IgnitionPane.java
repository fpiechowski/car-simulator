package carsimulator.simulator.scene;

import carsimulator.car.Car;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

class IgnitionPane extends VBox {
    private LightIndicator ignitionLightIndicator = new LightIndicator();
    private Button startButton = new Button("Start");
    private Button stopButton = new Button("Stop");

    IgnitionPane(Car car) {
        setAlignment(Pos.CENTER);
        setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        setPrefWidth(200);
        setSpacing(20);

        setupControls(car);

        getChildren().addAll(ignitionLightIndicator, startButton, stopButton);
    }

    private void setupControls(Car car) {
        ignitionLightIndicator.activeProperty.bind(car.getEngine().runningProperty);

        startButton.disableProperty().bind(car.getEngine().runningProperty);
        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> car.start());

        stopButton.disableProperty().bind(car.getEngine().runningProperty.not());
        stopButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> car.stop());
    }
}
