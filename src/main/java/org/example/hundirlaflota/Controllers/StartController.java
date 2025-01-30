package org.example.hundirlaflota.Controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class StartController {
    private List<List<Integer>> arrayShip;

    private Stage primaryStage;

    @FXML
    private GridPane yourGrid;

    public List<List<Integer>> getArrayShip() {
        return arrayShip;
    }

    public void setArrayShip(List<List<Integer>> arrayShip) {
        this.arrayShip = arrayShip;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public GridPane getYourGrid() {
        return yourGrid;
    }

    public void setYourGrid(GridPane yourGrid) {
        this.yourGrid = yourGrid;
    }

    @FXML
    private void pressedGang(MouseEvent event) {
        System.out.println("Gang");
    }
    private void handleCellClick(MouseEvent event, int row, int col) {
        System.out.println("Clic en celda: (" + row + ", " + col + ")");
    }

    @FXML
    private void initialize() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Pane pane = new Pane();
                pane.setPrefSize(37.6, 36.8); // Tamaño de cada celda

                // Agregar evento de clic
                final int finalRow = row;
                final int finalCol = col;
                pane.setOnMouseClicked(event -> handleCellClick(event, finalRow, finalCol));
                pane.getStyleClass().add("pane-style");
                // Agregar Pane al GridPane
                getYourGrid().add(pane, col, row);
            }
        }
    }
}
