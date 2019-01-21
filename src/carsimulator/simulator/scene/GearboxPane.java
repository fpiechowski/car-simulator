package carsimulator.simulator.scene;

import carsimulator.car.gearbox.Gearbox;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.StringConverter;

class GearboxPane extends VBox {
    private Label gearLabel = new Label();

    GearboxPane(Gearbox gearbox) {
        setupGearLabel(gearbox);

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
