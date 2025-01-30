package org.example.hundirlaflota;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PruebasMatriz extends Application {

    public List<List<Integer>> array(){
        List<List<Integer>> matrix = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0, 0, 0, 0, 0)),
            new ArrayList<>(Arrays.asList(0, 0, 0, 1, 1, 1, 1, 0, 0)),
            new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0, 0, 0, 0, 0)),
            new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0, 0, 1, 0, 0)),
            new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0)),
            new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0, 0, 1, 0, 0)),
            new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0, 0, 1, 0, 0)),
            new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0)),
            new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0, 0, 1, 0, 0))
        ));
        return matrix;
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        List<List<Integer>> matrix = array();

        gridPane = convertArrayToGridPane(matrix, gridPane);
        try {
            List<List<Integer>> matrix2 = convertGridToArray(gridPane);
            for (List<Integer> row : matrix) {
                System.out.println(Arrays.toString(row.toArray()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("GridPane 9x9");
        primaryStage.show();
    }

    public GridPane convertArrayToGridPane(List<List<Integer>> matrix, GridPane gridPane) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Pane pane = new Pane();
                pane.setPrefSize(37.6, 36.8); // Tamaño de cada celda

                // Estilo opcional para visualizar el GridPane

                if (matrix.get(row).get(col) == 1) {
                    pane.setStyle("-fx-background-color: blue");
                }else {
                    pane.setStyle("-fx-background-color: #473D7A; -fx-border-color: black;");
                }
                // Agregar evento de clic
                final int finalRow = row;
                final int finalCol = col;
                //pane.setOnMouseClicked(event -> handleCellClick(finalRow, finalCol));

                // Agregar Pane al GridPane
                gridPane.add(pane, col, row);
            }
        }
        return gridPane;
    }

    public List<List<Integer>> convertGridToArray(GridPane gridPane) throws  Exception{
        List<List<Integer>> matrix = new ArrayList<>();
        ArrayList<Integer> buffer = new ArrayList<>();

        String colorWaterSearch = "-fx-background-color: blue";
        String colorShipSearch = "-fx-background-color: #473D7A; -fx-border-color: black;";

        for (javafx.scene.Node node : gridPane.getChildren()) {

            Integer num = node.getStyle().equals(colorWaterSearch)
                    ? Integer.valueOf(0)
                    : node.getStyle().equals(colorShipSearch)
                    ? 1
                    : null;

            if (num == null) {
                throw new Exception("Tiene un color no registrado " + node.getStyle());
            }

            buffer.add(num);

            if (buffer.size() == 9){
                matrix.add(buffer);
                buffer = new ArrayList<>();
            }

        }
        return matrix;
    }



    // Método que maneja el clic en una celda
    private void handleCellClick(int row, int col) {
        System.out.println("Clic en celda: (" + row + "," + col + ")");
    }

    public static void main(String[] args) {
        launch(args);
    }




}
