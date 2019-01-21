package carsimulator.simulator.scene;

import carsimulator.car.BrakeTask;
import carsimulator.car.Car;
import carsimulator.car.RpmDownTask;
import carsimulator.car.RpmUpTask;
import carsimulator.car.pedal.Pedal;
import carsimulator.simulator.Simulator;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

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
        rootPane.setBottom(new PedalPane(simulator.getCar().getAccelerationPedal(),
                simulator.getCar().getBrakePedal()));
        rootPane.setCenter(new MetricsPane(simulator.getCar()));
        rootPane.setRight(new IgnitionPane(simulator.getCar()));
        rootPane.setLeft(new GearboxPane(simulator.getCar().getGearbox()));
    }

    private void setupKeyboardController(Car car) {
        addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            Thread taskThread;
            switch (keyEvent.getCode()) {
                case W:
                    car.getAccelerationPedal().setState(Pedal.State.PUSHING);
                    taskThread = new Thread(new RpmUpTask(car));
                    taskThread.setDaemon(true);
                    taskThread.start();
                    break;
                case S:
                    car.getAccelerationPedal().setState(Pedal.State.RELEASING);
                    taskThread = new Thread(new RpmDownTask(car));
                    taskThread.setDaemon(true);
                    taskThread.start();
                    break;
                case E:
                    car.getAccelerationPedal().setState(Pedal.State.IDLE);
                    break;
                case SPACE:
                    car.getBrakePedal().toggleState();
                    taskThread = new Thread(new BrakeTask(car));
                    taskThread.setDaemon(true);
                    taskThread.start();
                    break;
                case D:
                    car.getGearbox().gearUp();
                    break;
                case A:
                    car.getGearbox().gearDown();
                    break;
            }
        });

    }

}