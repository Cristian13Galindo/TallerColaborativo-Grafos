package co.edu.uptc.ui;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import co.edu.uptc.logic.GraphLogic;
import co.edu.uptc.model.Substation;

public class Main extends Application {
    private GraphLogic graphLogic = new GraphLogic();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Optimización de Rutas Eléctricas");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        Label nameLabel = new Label("Nombre Subestación:");
        TextField nameField = new TextField();
        Button addSubstationButton = new Button("Agregar Subestación");

        Label sourceLabel = new Label("Origen:");
        TextField sourceField = new TextField();
        Label targetLabel = new Label("Destino:");
        TextField targetField = new TextField();
        Label resistanceLabel = new Label("Resistencia:");
        TextField resistanceField = new TextField();
        Button addConnectionButton = new Button("Agregar Conexión");

        TextArea outputArea = new TextArea();

        addSubstationButton.setOnAction(e -> {
            Substation substation = new Substation(nameField.getText());
            graphLogic.addSubstation(substation);
            outputArea.appendText("Subestación agregada: " + substation + "\n");
            nameField.clear();
        });

        addConnectionButton.setOnAction(e -> {
            Substation source = new Substation(sourceField.getText());
            Substation target = new Substation(targetField.getText());
            double resistance = Double.parseDouble(resistanceField.getText());
            graphLogic.addConnection(source, target, resistance);
            outputArea.appendText("Conexión agregada: " + source + " -> " + target + " (" + resistance + ")\n");
            sourceField.clear();
            targetField.clear();
            resistanceField.clear();
        });

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(addSubstationButton, 2, 0);

        grid.add(sourceLabel, 0, 1);
        grid.add(sourceField, 1, 1);
        grid.add(targetLabel, 0, 2);
        grid.add(targetField, 1, 2);
        grid.add(resistanceLabel, 0, 3);
        grid.add(resistanceField, 1, 3);
        grid.add(addConnectionButton, 2, 3);

        grid.add(outputArea, 0, 4, 3, 1);

        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

