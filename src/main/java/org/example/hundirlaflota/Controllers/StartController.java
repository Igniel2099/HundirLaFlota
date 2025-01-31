package org.example.hundirlaflota.Controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.hundirlaflota.ConvertMatrix;
import org.example.hundirlaflota.ServidorCliente.Cliente;
import org.example.hundirlaflota.ServidorCliente.ClienteGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartController {

    private ClienteGame clienteGame = new ClienteGame();

    private List<List<Integer[]>> arraysShips;

    private Stage primaryStage;

    private List<Integer> positionGang;

    @FXML
    private GridPane yourGrid;

    @FXML
    private GridPane myGrid;

    public ClienteGame getClienteGame() {
        return clienteGame;
    }

    public void setClienteGame(ClienteGame clienteGame) {
        this.clienteGame = clienteGame;
    }

    public List<List<Integer[]>> getArraysShips() {
        return arraysShips;
    }

    public void setArraysShips(List<List<Integer[]>> arraysShips) {
        this.arraysShips = arraysShips;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public List<Integer> getPositionGang() {
        return positionGang;
    }

    public void setPositionGang(List<Integer> positionGang) {
        this.positionGang = positionGang;
    }

    public GridPane getYourGrid() {
        return yourGrid;
    }

    public void setYourGrid(GridPane yourGrid) {
        this.yourGrid = yourGrid;
    }

    public GridPane getMyGrid() {
        return myGrid;
    }

    public void setMyGrid(GridPane myGrid) {
        this.myGrid = myGrid;
    }

    @FXML
    private void pressedGang(MouseEvent event) {
        System.out.println("Gang in " + getPositionGang());
        ImageView imageView = (ImageView) event.getSource();
        imageView.setImage(new Image(getClass().getResource("/org/example/hundirlaflota/Images/button.png").toExternalForm()));
        ((Node) event.getSource()).setDisable(true);

        synchronized (getClienteGame()){
            getClienteGame().run();
            while (!getClienteGame().getIsMessageServer()){
                try{
                    getClienteGame().wait();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            imageView.setImage(new Image(getClass().getResource("/org/example/hundirlaflota/Images/buttonBang.png").toExternalForm()));
            ((Node) event.getSource()).setDisable(false);
        }

    }

    public Pane getPaneFromGrid(GridPane grid, List<Integer> position) {
        int row = position.get(0); // Fila de getPosition
        int col = position.get(1); // Columna de getPosition

        for (Node node : grid.getChildren()) {
            if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null) {
                if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                    return (Pane) node; // Retorna el Pane en esa posición
                }
            }
        }
        return null; // Si no encuentra el Pane
    }

    private void handleCellClick(MouseEvent event, int row, int col) {
        System.out.println("Clic en celda: (" + row + ", " + col + ")");

        if (getPositionGang() != null){
            Pane pane = getPaneFromGrid(getYourGrid(),getPositionGang());
            pane.setStyle("");
        }

        setPositionGang(new ArrayList<>(Arrays.asList(row, col)));
        Pane pane = (Pane) event.getSource();
        pane.setStyle("-fx-background-color: red");
    }

    public void paneFillingYourGridPane(){
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

    public void gridPaneShipFilling(){
        ConvertMatrix convertMatrix = new ConvertMatrix();
        GridPane newGridPane = convertMatrix.reBuildGridPane(getArraysShips(),getMyGrid());
        setMyGrid(newGridPane);
    }

    @FXML
    private void initialize() {
        paneFillingYourGridPane();

    }
}
