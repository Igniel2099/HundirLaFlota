package org.example.hundirlaflota.Window;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.example.hundirlaflota.Controllers.MainController;
import org.example.hundirlaflota.Controllers.StartController;

import java.io.IOException;
import java.util.List;

import static javafx.application.Application.launch;


public class StartWindow {


    /**
     * Este método te permite obtener el controlador de la ventana y mandarle a este mismo controlador
     * el Stage.
     * @param loader el loader de la escena
     * @param primaryStage el stage que tengo que pasarle al controlador
     */
    public void getController(FXMLLoader loader, Stage primaryStage, List<List<Integer[]>>  listAllCoords) {
        StartController controller = loader.getController();
        controller.setArraysShips(listAllCoords);
        controller.gridPaneShipFilling();

        controller.setPrimaryStage(primaryStage);
    }

    /**
     * Este método te permite cargar el fxml como la escena de un stage y mandarle el stage al controlador
     * @param pathScene la dirección de la escena
     * @param primaryStage el primer escenario
     * @return me devuelve la escena
     * @throws IOException controlar las posibles excepciones del programa
     */
    public Scene fxmlLoader(String pathScene,Stage primaryStage, List<List<Integer[]>>  listAllCoords) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathScene));
        Parent root = loader.load();
        getController(loader, primaryStage, listAllCoords);

        // Obtener el controlador y pasar el Stage principal

        return new Scene(root);
    }

    /**
     * Este método inicia la aplicación leyendo el Fxml inicial
     * @param stage Este es el escenario principal donde se va ha ejecutar
     * @throws IOException controla las posibles excepciones
     */

    public void start(Stage stage, List<List<Integer[]>> listAllCoords) throws IOException {
        Scene scene = fxmlLoader("/org/example/hundirlaflota/Scenes/startWindow.fxml",stage,listAllCoords);

        stage.setScene(scene);
        stage.show(); // mostrar el escenario
    }

    /**
     * En este main ejecuta la app
     * @param args posibles argumentos que pueda pasarle
     */
    public static void main(String[] args) {
        launch(args);
    }

}
