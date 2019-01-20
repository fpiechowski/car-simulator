package carsimulator.simulator.scene;

import carsimulator.car.Car;
import carsimulator.car.gearbox.Gearbox;
import carsimulator.car.pedal.Pedal;
import carsimulator.simulator.Simulator;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.StringConverter;

import java.util.Arrays;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class SimulatorScene extends Scene {

    private Simulator simulator;

    public SimulatorScene(Simulator simulator) {
        super(new BorderPane(), 800, 600);

        this.simulator = simulator;
        setupRoot();
        setupKeyboardController(simulator.getCar());
    }

    private void setupRoot() {
        BorderPane rootPane = ((BorderPane) getRoot());
        rootPane.setMinSize(800, 600);
        rootPane.setBottom(new PedalPane(simulator.getCar().getAccelerationPedal(), simulator.getCar().getBrakePedal(),
                simulator.getCar().getClutchPedal()));
        rootPane.setCenter(new MetricsPane(simulator.getCar()));
        rootPane.setRight(new IgnitionPane(simulator.getCar()));
        rootPane.setLeft(new GearboxPane(simulator.getCar().getGearbox()));
    }

    private void setupKeyboardController(Car car) {
        addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            switch (keyEvent.getCode()) {
                case ENTER:
                    car.getAccelerationPedal().togglePush();
                    break;
                case SPACE:
                    car.getBrakePedal().togglePush();
                    break;
                case CONTROL:
                    car.getClutchPedal().togglePush();
                    break;
                case PLUS:
                    car.getGearbox().gearUp();
                    break;
                case MINUS:
                    car.getGearbox().gearDown();
                    break;
            }
        });
    }

    private class PedalPane extends HBox {
        private LightIndicator accelerationPedalLightIndicator = new LightIndicator();
        private LightIndicator brakePedalLightIndicator = new LightIndicator();
        private LightIndicator clutchPedalLightIndicator = new LightIndicator();

        PedalPane(Pedal accelerationPedal, Pedal brakePedal, Pedal clutchPedal) {
            bindControls(accelerationPedal, brakePedal, clutchPedal);

            setAlignment(Pos.CENTER);
            setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
            setSpacing(50);

            getChildren().add(clutchPedalLightIndicator);
            getChildren().add(brakePedalLightIndicator);
            getChildren().add(accelerationPedalLightIndicator);
        }

        private void bindControls(Pedal accelerationPedal, Pedal brakePedal, Pedal clutchPedal) {
            clutchPedalLightIndicator.activeProperty.bind(clutchPedal.pushedProperty);
            brakePedalLightIndicator.activeProperty.bind(brakePedal.pushedProperty);
            accelerationPedalLightIndicator.activeProperty.bind(accelerationPedal.pushedProperty);
        }
    }

    private class MetricsPane extends GridPane {
        private ProgressBar rpmBar = new ProgressBar(0.0);
        private ProgressBar speedBar = new ProgressBar(0.0);
        private TextArea loggerTextArea = new TextArea();

        MetricsPane(Car car) {
            rpmBar.progressProperty().bind(car.getEngine().rpmProperty);
            speedBar.progressProperty().bind(car.speedProperty);

            setupPane();
            setupControls();

            add(rpmBar, 2, 1);
            add(speedBar, 2, 2);
            addLabels();
            add(loggerTextArea, 1, 3, 2, 1);
        }

        private void setupPane() {
            setAlignment(Pos.CENTER);
            setPrefWidth(400);
            setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
            setHgap(10);
            setVgap(10);
        }

        private void addLabels() {
            Label rpmLabel = new Label("RPM");
            rpmLabel.setFont(Font.font(20));

            Label speedLabel = new Label("Speed");
            speedLabel.setFont(Font.font(20));

            add(rpmLabel, 1, 1);
            add(speedLabel, 1, 2);
        }

        private void setupControls() {
            for (ProgressBar bar : Arrays.asList(rpmBar, speedBar)) {
                bar.setPrefWidth(300);
                bar.setPrefHeight(50);
            }

            setupLoggerTextArea();
        }

        private void setupLoggerTextArea() {
            loggerTextArea.setPrefWidth(300);
            loggerTextArea.setPrefColumnCount(20);
            loggerTextArea.setWrapText(true);
            loggerTextArea.setEditable(false );

            addLoggerHandler();
        }

        private void addLoggerHandler() {
            Logger.getGlobal().addHandler(new Handler() {
                @Override
                public void publish(LogRecord logRecord) {
                    loggerTextArea.appendText(logRecord.getMessage() + "\n");
                }

                @Override
                public void flush() {
                    loggerTextArea.setText("");
                }

                @Override
                public void close() throws SecurityException {

                }
            });
        }
    }

    private class IgnitionPane extends VBox {
        private LightIndicator ignitionLightIndicator = new LightIndicator();
        private Button ignitionButton = new Button("Ignite");

        IgnitionPane(Car car) {
            setAlignment(Pos.CENTER);
            setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
            setPrefWidth(200);
            setSpacing(20);

            setupControls(car);

            getChildren().add(ignitionLightIndicator);
            getChildren().add(ignitionButton);
        }

        private void setupControls(Car car) {
            ignitionLightIndicator.activeProperty.bind(car.getEngine().runningProperty);

            ignitionButton.disableProperty().bind(car.getEngine().runningProperty);
            ignitionButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> car.start());
        }
    }

    private class GearboxPane extends VBox{
        private Label gearLabel = new Label();

        GearboxPane(Gearbox gearbox) {
            setupGearLabel(gearbox);

            setBackground(new Background(new BackgroundFill(Color.PURPLE, null, null)));
            setAlignment(Pos.CENTER);
            setPrefWidth(200);

            getChildren().add(gearLabel);
        }

        private void setupGearLabel(Gearbox gearbox) {
            gearLabel.textProperty().bindBidirectional(gearbox.currentGearProperty,
                    new StringConverter<Gearbox.Gear>() {
                        @Override
                        public String toString(Gearbox.Gear gear) {
                            return gear.toString();
                        }

                        @Override
                        public Gearbox.Gear fromString(String s) {
                            return Gearbox.Gear.valueOf(s);
                        }
                    });
            gearLabel.setFont(Font.font(40));
        }
    }
}