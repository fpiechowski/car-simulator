package carsimulator.simulator.scene;

import carsimulator.car.Car;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.StringConverter;
import javafx.util.converter.FormatStringConverter;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

class MetricsPane extends GridPane {
    private ProgressBar rpmBar = new ProgressBar(0.0);
    private ProgressBar speedBar = new ProgressBar(0.0);
    private TextArea loggerTextArea = new TextArea();

    private Car car;

    MetricsPane(Car car) {
        this.car = car;

        setupPane();
        setupControls();

        add(rpmBar, 1, 0);
        add(speedBar, 1, 1);
        addLabels();
        add(loggerTextArea, 0, 2, 3, 1);
    }

    private void setupPane() {
        setAlignment(Pos.CENTER);
        setPrefWidth(400);
        setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        setHgap(10);
        setVgap(10);
        setGridLinesVisible(true);
    }

    private void addLabels() {
        Label rpmLabel = new Label("RPM");
        rpmLabel.setFont(Font.font(20));

        Label speedLabel = new Label("Speed");
        speedLabel.setFont(Font.font(20));

        Label speedValueLabel = new Label();
        speedValueLabel.textProperty().bindBidirectional(car.speedProperty, new StringConverter<Number>() {
            @Override
            public String toString(Number number) {
                return String.format("%d km/h", number.intValue());
            }

            @Override
            public Number fromString(String s) {
                return new Integer(s);
            }
        });

        Label rpmValueLabel = new Label();
        rpmValueLabel.textProperty().bindBidirectional(car.getEngine().rpmProperty, new StringConverter<Number>() {
            @Override
            public String toString(Number number) {
                return String.format("%d x100", number.intValue() / 100);
            }

            @Override
            public Number fromString(String s) {
                return new Integer(s);
            }
        });

        add(rpmLabel, 0, 0);
        add(speedLabel, 0, 1);
        add(rpmValueLabel, 2, 0);
        add(speedValueLabel, 2, 1);
    }

    private void setupControls() {
        rpmBar.progressProperty().bind(car.getEngine().rpmPercentageProperty);
        speedBar.progressProperty().bind(car.speedPercentageProperty);

        for (ProgressBar bar : Arrays.asList(rpmBar, speedBar)) {
            bar.setPrefWidth(250);
            bar.setPrefHeight(50);
        }

        setupLoggerTextArea();
    }

    private void setupLoggerTextArea() {
        loggerTextArea.setWrapText(true);
        loggerTextArea.setEditable(false);

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
