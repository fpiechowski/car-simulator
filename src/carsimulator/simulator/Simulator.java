package carsimulator.simulator;

import carsimulator.car.Car;
import carsimulator.simulator.scene.SimulatorScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class Simulator extends Application {

    private Car car = new Car();

    public static void main(String[] args) {
        launch(Simulator.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setResizable(false);
        stage.setScene(new SimulatorScene(this));
        stage.show();
    }

    public Car getCar() {
        return car;
    }
}