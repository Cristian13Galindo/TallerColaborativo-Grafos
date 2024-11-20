package co.edu.uptc.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import co.edu.uptc.logic.GraphLogic;
import co.edu.uptc.model.Substation;

public class MainWindow extends Application {
    private GraphLogic graphLogic = new GraphLogic();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Optimización de Rutas Eléctricas");

        HBox mainHBox = new HBox(10);
        mainHBox.setPadding(new Insets(10));

        VBox leftVBox = new VBox(10);
        HBox topHBox = new HBox(10);
        HBox bottomHBox = new HBox(10);

        VBox leftTopVBox = new VBox(10);
        Label nameLabel = new Label("Nombre Subestación:");
        TextField nameField = new TextField();
        Button addSubstationButton = new Button("Agregar Subestación");
        leftTopVBox.getChildren().addAll(nameLabel, nameField, addSubstationButton);

        VBox centerTopVBox = new VBox(10);
        Label sourceLabel = new Label("Origen:");
        TextField sourceField = new TextField();
        Label targetLabel = new Label("Destino:");
        TextField targetField = new TextField();
        Label resistanceLabel = new Label("Distancia:");
        TextField resistanceField = new TextField();
        Button addConnectionButton = new Button("Agregar Conexión");
        centerTopVBox.getChildren().addAll(sourceLabel, sourceField, targetLabel, targetField, resistanceLabel, resistanceField, addConnectionButton);

        VBox rightTopVBox = new VBox(10);
        Label calculateLabel = new Label("Calcular Rutas desde:");
        TextField calculateField = new TextField();
        Button calculateRoutesButton = new Button("Calcular Rutas");
        rightTopVBox.getChildren().addAll(calculateLabel, calculateField, calculateRoutesButton);

        topHBox.getChildren().addAll(leftTopVBox, centerTopVBox, rightTopVBox);

        ImageView imageView = new ImageView();
        imageView.setImage(new Image(getClass().getResource("/images/placeholder.png").toExternalForm()));
        imageView.setFitWidth(350);
        imageView.setFitHeight(350);
        imageView.setPreserveRatio(true);
        bottomHBox.getChildren().add(imageView);
        bottomHBox.setStyle("-fx-alignment: center;");

        leftVBox.getChildren().addAll(topHBox, bottomHBox);

        VBox rightVBox = new VBox(10);
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        rightVBox.getChildren().add(outputArea);
        VBox.setVgrow(outputArea, Priority.ALWAYS);

        mainHBox.getChildren().addAll(leftVBox, rightVBox);
        HBox.setHgrow(rightVBox, Priority.ALWAYS);

        addSubstationButton.setOnAction(e -> {
            Substation substation = new Substation(nameField.getText().toUpperCase());
            graphLogic.addSubstation(substation);
            outputArea.appendText("Subestación agregada: " + substation + "\n");
            nameField.clear();
        });

        addConnectionButton.setOnAction(e -> {
            String sourceName = sourceField.getText().toUpperCase();
            String targetName = targetField.getText().toUpperCase();

            Substation source = graphLogic.getGraph().keySet()
                    .stream()
                    .filter(s -> s.getName().equals(sourceName))
                    .findFirst()
                    .orElse(null);

            Substation target = graphLogic.getGraph().keySet()
                    .stream()
                    .filter(s -> s.getName().equals(targetName))
                    .findFirst()
                    .orElse(null);

            if (source == null || target == null) {
                outputArea.appendText("Error: Una o ambas subestaciones no existen.\n");
                return;
            }

            try {
                double resistance = Double.parseDouble(resistanceField.getText());
                graphLogic.addConnection(source, target, resistance);
                outputArea.appendText("Conexión agregada: " + source + " -> " + target + " (" + resistance + ")\n");
            } catch (NumberFormatException ex) {
                outputArea.appendText("Error: Resistencia inválida.\n");
            }

            sourceField.clear();
            targetField.clear();
            resistanceField.clear();
        });

        calculateRoutesButton.setOnAction(e -> {
            String startName = calculateField.getText().toUpperCase();

            Substation start = graphLogic.getGraph().keySet()
                    .stream()
                    .filter(s -> s.getName().equals(startName))
                    .findFirst()
                    .orElse(null);

            if (start == null) {
                outputArea.appendText("Error: La subestación " + startName + " no existe en el grafo.\n");
                return;
            }

            outputArea.appendText("Calculando rutas óptimas desde " + startName + "...\n");
            var distances = graphLogic.dijkstra(start);
            distances.forEach((substation, distance) -> {
                outputArea.appendText(startName + " -> " + substation.getName() + ": " + distance + "\n");
            });

            calculateField.clear();
        });

        Scene scene = new Scene(mainHBox, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


