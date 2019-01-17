/*
package carsimulator.simulator;

import carsimulator.car.Car;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Properties;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;

public class Simulator extends Application {

    private Car car = new Car();

    public static void main(String[] args) {
        launch(Simulator.class, args);
    }

    public Car getCar() {
        return car;
    }

    @Override
    public void start(Stage stage) throws Exception {

        Optional.ofNullable(getParameters().getNamed().get("configFileName"))
                .ifPresent(fileName -> configure(loadConfigFromFile(fileName)));

        stage.setWidth(800);
        stage.setHeight(600);
        stage.setResizable(false);
        stage.setScene(new SimulatorScene(this));
        stage.show();
    }

    private Preferences loadConfigFromFile(String fileName) {
        try {
            Preferences.importPreferences(Files.newInputStream(Paths.get(fileName)));
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Can't open config file: " + e.getLocalizedMessage()).show();
        } catch (InvalidPreferencesFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid config format: " + e.getLocalizedMessage()).show();
        }

        return Preferences.userRoot();
    }

    private void configure(Preferences config) {
        // Car.setX(config.get("x"))
    }

    private Properties openConfigFile(String fileName) throws IOException {
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get(fileName)));
        return properties;
    }
}
*/
