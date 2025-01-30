module org.example.hundirlaflota {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.hundirlaflota to javafx.fxml;
    exports org.example.hundirlaflota;
    exports org.example.hundirlaflota.Controllers;
    opens org.example.hundirlaflota.Controllers to javafx.fxml;
    exports org.example.hundirlaflota.Window;
    opens org.example.hundirlaflota.Window to javafx.fxml;
}