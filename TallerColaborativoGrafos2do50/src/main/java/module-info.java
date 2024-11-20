module co.edu.uptc.tallerindividuallistasenlazadasdobles {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens co.edu.uptc.ui to javafx.fxml;
    exports co.edu.uptc.ui;
    exports co.edu.uptc.persistence;
}