package co.edu.uptc.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class MainWindow extends Application {
    private DogManager manager = new DogManager();

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Gestión de Perros");
        TextField idField = new TextField();
        idField.setPromptText("ID");
        TextField nameField = new TextField();
        nameField.setPromptText("Nombre");
        TextField ageField = new TextField();
        ageField.setPromptText("Edad");
        TextField indexField = new TextField();
        indexField.setPromptText("Índice (para getObject)");

        Button addButton = new Button("Añadir Perro");
        Button deleteButton = new Button("Eliminar Perro");
        Button findButton = new Button("Buscar Perro");
        Button showForwardButton = new Button("Mostrar Ascendente");
        Button showBackwardButton = new Button("Mostrar Descendente");
        Button getFirstButton = new Button("Obtener Primer Perro");
        Button getLastButton = new Button("Obtener Último Perro");
        Button getObjectButton = new Button("Obtener Perro por Índice");

        VBox leftVBox = new VBox(10, label, idField, nameField, ageField, indexField, addButton);
        VBox rightVBox = new VBox(10, deleteButton, findButton, showForwardButton, showBackwardButton, getFirstButton, getLastButton, getObjectButton);
        HBox mainHBox = new HBox(20, leftVBox, rightVBox);

        addButton.setOnAction(e -> {
            try {
                manager.addDog(idField.getText(), nameField.getText(), Integer.parseInt(ageField.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Perro añadido exitosamente");
                alert.show();

                idField.clear();
                nameField.clear();
                ageField.clear();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                alert.show();
            }
        });

        deleteButton.setOnAction(e -> {
            try {
                manager.deleteDog(idField.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Perro eliminado exitosamente");
                alert.show();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                alert.show();
            }
        });

        findButton.setOnAction(e -> {
            List<Dog> dogs = manager.showDogs(true);
            if (dogs != null && !dogs.isEmpty()) {
                StringBuilder dogsStringBuilder = new StringBuilder();
                dogs.forEach(dog -> dogsStringBuilder.append(dog.toString()).append("\n"));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, dogsStringBuilder.toString());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No se encontraron perros.");
                alert.showAndWait();
            }
        });

        showForwardButton.setOnAction(e -> {
            List<Dog> dogs = manager.showDogs(true);
            if (dogs != null && !dogs.isEmpty()) {
                StringBuilder dogsStringBuilder = new StringBuilder();
                dogs.forEach(dog -> dogsStringBuilder.append(dog.toString()).append("\n"));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, dogsStringBuilder.toString());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No se encontraron perros.");
                alert.showAndWait();
            }
        });

        showBackwardButton.setOnAction(e -> {
            List<Dog> dogs = manager.showDogs(false);
            if (dogs != null && !dogs.isEmpty()) {
                StringBuilder dogsStringBuilder = new StringBuilder();
                dogs.forEach(dog -> dogsStringBuilder.append(dog.toString()).append("\n"));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, dogsStringBuilder.toString());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No se encontraron perros.");
                alert.showAndWait();
            }
        });

        getFirstButton.setOnAction(e -> {
            Dog dog = manager.getFirstDog();
            if (dog != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Primer Perro: " + dog);
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No se encontraron perros.");
                alert.show();
            }
        });

        getLastButton.setOnAction(e -> {
            Dog dog = manager.getLastDog();
            if (dog != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Último Perro: " + dog);
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No se encontraron perros.");
                alert.show();
            }
        });

        getObjectButton.setOnAction(e -> {
            try {
                int index = Integer.parseInt(indexField.getText());
                Dog dog = manager.getDogAt(index);
                if (dog != null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Perro en índice " + index + ": " + dog);
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "No se encontró perro en el índice " + index);
                    alert.show();
                }
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Índice inválido.");
                alert.show();
            }
        });

        Scene scene = new Scene(mainHBox, 500, 300);
        primaryStage.setTitle("Gestión de Perros");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}