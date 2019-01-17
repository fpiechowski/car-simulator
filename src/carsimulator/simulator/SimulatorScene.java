/*
package carsimulator.simulator;

import carsimulator.car.Car;
import carsimulator.car.gearbox.Gearbox;
import carsimulator.car.pedal.Pedal;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.util.StringConverter;

import java.util.Arrays;

class SimulatorScene extends Scene {

    private Simulator simulator;

    SimulatorScene(Simulator simulator) {
        super(new BorderPane(), 800, 600);

        this.simulator = simulator;
        setupRoot();
        addKeyboardInputHandlers(simulator.getCar());
    }

    private void setupRoot() {
        BorderPane rootPane = ((BorderPane) getRoot());
        rootPane.setPrefWidth(800);
        rootPane.setPrefHeight(600);
        rootPane.setBottom(new PedalPane(simulator.getCar().getAccelerationPedal(), simulator.getCar().getBrakePedal(),
                simulator.getCar().getClutchPedal()));
        rootPane.setCenter(new MetricsPane(simulator.getCar()));
        rootPane.setRight(new IgnitionPane(simulator.getCar().getEngine()));
        rootPane.setLeft(new GearboxPane(simulator.getCar().getGearbox()));
    }

    private void addKeyboardInputHandlers(Car car) {
        addKeyPressedEventHandler(car);
        addKeyReleasedEventHandler(car);
    }

    private void addKeyReleasedEventHandler(Car car) {
        addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            switch (keyEvent.getCode()) {
                case ENTER:
                    car.getAccelerationPedal().release();
                    break;
                case SPACE:
                    car.getBrakePedal().release();
                    break;
                case CONTROL:
                    car.getClutchPedal().release();
            }
        });
    }

    private void addKeyPressedEventHandler(Car car) {
        addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            switch (keyEvent.getCode()) {
                case ENTER:
                    car.getAccelerationPedal().push();
                    break;
                case SPACE:
                    car.getBrakePedal().push();
                    break;
                case CONTROL:
                    car.getClutchPedal().push();
            }
        });
    }

    private static class LightIndicator extends Circle {
        static private final Color OFF_COLOR = Color.GRAY;
        static private final Color ON_COLOR = Color.GREENYELLOW;
        private Property<Boolean> activeProperty = new SimpleBooleanProperty();

        LightIndicator() {
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

    private class PedalPane extends TilePane {
        private LightIndicator accelerationPedalLightIndicator = new LightIndicator();
        private LightIndicator brakePedalLightIndicator = new LightIndicator();
        private LightIndicator clutchPedalLightIndicator = new LightIndicator();

        PedalPane(Pedal accelerationPedal, Pedal brakePedal, Pedal clutchPedal) {
            clutchPedalLightIndicator.activeProperty.bind(clutchPedal.pushedProperty());
            brakePedalLightIndicator.activeProperty.bind(brakePedal.pushedProperty());
            accelerationPedalLightIndicator.activeProperty.bind(accelerationPedal.pushedProperty());

            setAlignment(Pos.CENTER);

            HBox layout = new HBox(clutchPedalLightIndicator, brakePedalLightIndicator, accelerationPedalLightIndicator);
            layout.setSpacing(50);

            getChildren().add(layout);
        }
    }

    private class MetricsPane extends TilePane {
        private ProgressBar rpmBar = new ProgressBar(0.0);
        private ProgressBar speedBar = new ProgressBar(0.0);

        MetricsPane(Car car) {
            for (ProgressBar bar : Arrays.asList(rpmBar, speedBar)) {
                bar.setPrefWidth(300);
                bar.setPrefHeight(50);
                bar.setRotate(-90);
            }

            rpmBar.progressProperty().bind(car.getEngine().rpmProperty());
            speedBar.progressProperty().bind(car.speedProperty());

            setAlignment(Pos.CENTER);
            setWidth(400);

            HBox layout = new HBox(rpmBar, speedBar);
            layout.setAlignment(Pos.CENTER);

            getChildren().add(layout);
        }
    }

    private class IgnitionPane extends Pane {
        private LightIndicator ignitionLightIndicator = new LightIndicator();
        private Button ignitionButton = new Button("Ignite");

        IgnitionPane(Car car) {
            ignitionLightIndicator.activeProperty.bind(car.getEngine().runningProperty());

            ignitionButton.disableProperty().bind(car.getEngine().runningProperty());
            ignitionButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> car.start());

            setWidth(200);

            getChildren().add(new VBox(ignitionLightIndicator, ignitionButton));
        }
    }

    private class GearboxPane extends TilePane {
        private Label gearLabel = new Label();

        GearboxPane(Gearbox gearbox) {
            gearLabel.textProperty().bindBidirectional(gearbox.currentGearProperty(), new StringConverter<Gearbox.Gear>() {
                @Override
                public String toString(Gearbox.Gear gear) {
                    return gear.toString();
                }

                @Override
                public Gearbox.Gear fromString(String s) {
                    return Gearbox.Gear.valueOf(s);
                }
            });
            gearLabel.setFont(Font.font(20));

            setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
            setAlignment(Pos.CENTER);
            setWidth(200);

            getChildren().add(gearLabel);
        }
    }
}
*/
