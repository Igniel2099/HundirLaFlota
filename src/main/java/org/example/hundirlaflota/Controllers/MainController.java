package org.example.hundirlaflota.Controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.*;

public class MainController {

    private Integer shipSpace;

    private Stage primaryStage;

    private List<Integer[]> listCoordinates = new ArrayList<>();

    @FXML
    private Label quantityShipFour;

    @FXML
    private Label quantityShipThree;

    @FXML
    private Label quantityShipTwo;

    @FXML
    private Label quantityShipOne;

    @FXML
    private ImageView shipOne;

    @FXML
    private ImageView shipTwo;

    @FXML
    private ImageView shipThree;

    @FXML
    private ImageView shipFour;

    @FXML
    private GridPane myGrid;

    public Integer getShipSpace() {
        return shipSpace;
    }

    public void setShipSpace(Integer shipSpace) {
        this.shipSpace = shipSpace;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public List<Integer[]> getListCoordinates() {
        return listCoordinates;
    }

    public void setListCoordinates(List<Integer[]> listCoordinates) {
        this.listCoordinates = listCoordinates;
    }

    public Label getQuantityShipFour() {
        return quantityShipFour;
    }

    public void setQuantityShipFour(Label quantityShipFour) {
        this.quantityShipFour = quantityShipFour;
    }

    public Label getQuantityShipThree() {
        return quantityShipThree;
    }

    public void setQuantityShipThree(Label quantityShipThree) {
        this.quantityShipThree = quantityShipThree;
    }

    public Label getQuantityShipTwo() {
        return quantityShipTwo;
    }

    public void setQuantityShipTwo(Label quantityShipTwo) {
        this.quantityShipTwo = quantityShipTwo;
    }

    public Label getQuantityShipOne() {
        return quantityShipOne;
    }

    public void setQuantityShipOne(Label quantityShipOne) {
        this.quantityShipOne = quantityShipOne;
    }

    public ImageView getShipOne() {
        return shipOne;
    }

    public void setShipOne(ImageView shipOne) {
        this.shipOne = shipOne;
    }

    public ImageView getShipTwo() {
        return shipTwo;
    }

    public void setShipTwo(ImageView shipTwo) {
        this.shipTwo = shipTwo;
    }

    public ImageView getShipThree() {
        return shipThree;
    }

    public void setShipThree(ImageView shipThree) {
        this.shipThree = shipThree;
    }

    public ImageView getShipFour() {
        return shipFour;
    }

    public void setShipFour(ImageView shipFour) {
        this.shipFour = shipFour;
    }

    public GridPane getMyGrid() {
        return myGrid;
    }

    public void setMyGrid(GridPane myGrid) {
        this.myGrid = myGrid;
    }

    @FXML
    private void pressedStart(MouseEvent event) {
        if (getQuantityShipFour().getText().equals("0") &&
            getQuantityShipThree().getText().equals("0") &&
            getQuantityShipTwo().getText().equals("0")) {

            System.out.println("Ha terminado");
        }
    }

    @FXML
    private void pressedShip(MouseEvent event){
        ImageView shipImage = (ImageView) event.getSource();
        String idShip = shipImage.getId();

        Map<String,Integer> map = new HashMap<>();
        map.put(getShipOne().getId(),1);
        map.put(getShipTwo().getId(),2);
        map.put(getShipThree().getId(),3);
        map.put(getShipFour().getId(),4);

        setShipSpace(map.get(idShip));
        System.out.println(idShip);
        System.out.println(getShipSpace());
    }



    public List<Integer[]> sorterCords() throws Exception {
        if (getListCoordinates().size() != 2) {
            throw new Exception("No puedo hacer nada con esta lista de arrays Integer ");
        }

        Integer[] firstArray = getListCoordinates().getFirst();
        Integer[] lastArray = getListCoordinates().getLast();


       return firstArray[0] > lastArray[0] || firstArray[1] > lastArray[1]
                ? new ArrayList<>(Arrays.asList(lastArray, firstArray))
                : firstArray[0] < lastArray[0] || firstArray[1] < lastArray[1]
                ? getListCoordinates()
                : new ArrayList<>(Arrays.asList(lastArray, firstArray));
    }

    public List<Integer[]> getCoordinatesBetween(Integer[] start, Integer[] end) throws Exception {
        List<Integer[]> coordinates = new ArrayList<>();

        // Asegurar que el movimiento es en línea recta
        if (!start[0].equals(end[0]) && !start[1].equals(end[1])) {
            throw new Exception("No puedes hacer movimientos en diagonal");
        }

        // Movimiento horizontal (misma fila, diferente columna)
        if (start[0].equals(end[0])) {
            int row = start[0];
            int minCol = Math.min(start[1], end[1]);
            int maxCol = Math.max(start[1], end[1]);

            for (int col = minCol; col <= maxCol; col++) {
                coordinates.add(new Integer[]{row, col}); // ✅ Corrección aquí
            }
        }
        // Movimiento vertical (misma columna, diferente fila)
        else if (start[1].equals(end[1])) {
            int col = start[1];
            int minRow = Math.min(start[0], end[0]);
            int maxRow = Math.max(start[0], end[0]);

            for (int row = minRow; row <= maxRow; row++) {
                coordinates.add(new Integer[]{row, col}); // ✅ Corrección aquí
            }
        }

        return coordinates;
    }

    private boolean comprobateCoords(List<Integer[]> coords) {
        List<Integer[]> list = new ArrayList<>();
        try {
             list = getCoordinatesBetween(coords.getFirst(),coords.getLast());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Integer[] coord : list) {
            System.out.println(coord[0] + " " + coord[1]);
        }
        return list.size() == getShipSpace();
    }

    private boolean mergeCells(GridPane gridPane, List<Integer[]> coords) {
        if (!comprobateCoords(coords)){

            for (Integer[] coord : coords) {
                for (Node node : gridPane.getChildren()) {
                    Integer nodeRow = GridPane.getRowIndex(node);
                    Integer nodeCol = GridPane.getColumnIndex(node);

                    if (Objects.equals(nodeRow, coord[0]) && Objects.equals(nodeCol, coord[1])) {
                        node.setStyle("-fx-background-color:  #2C4080; -fx-border-color: black");
                    }
                }

            }
            return false;
        }

        if (coords.isEmpty()) return false;
        //if () return false;


        for (Integer[] coord : coords) {
            System.out.println(Arrays.toString(coord));
        }
        // Obtener la primera y última coordenada
        Integer[] first = coords.getFirst();
        Integer[] last = coords.getLast();

        int startCol = first[1]; // Columna inicial
        int startRow = first[0]; // Fila inicial
        int endCol = last[1]; // Última columna
        int endRow = last[0]; // Última fila

        // Crear un Pane unificado
        Pane mergedPane = new Pane();
        mergedPane.setStyle("-fx-background-color: lightblue; -fx-border-color: black;");
        mergedPane.setPrefSize((endCol - startCol + 1) * 37.6, (endRow - startRow + 1) * 36.8);

        // Remover las celdas individuales
        coords.forEach(coord -> {
            gridPane.getChildren().removeIf(node ->
                    GridPane.getRowIndex(node) == coord[0] && GridPane.getColumnIndex(node) == coord[1]
            );
        });

        // Agregar el Pane grande en la posición inicial
        gridPane.add(mergedPane, startCol, startRow);

        // Aplicar rowSpan o colSpan
        if (startRow == endRow) {
            GridPane.setColumnSpan(mergedPane, endCol - startCol + 1);
        } else if (startCol == endCol) {
            GridPane.setRowSpan(mergedPane, endRow - startRow + 1);
        }
        return true;
    }

    private void handleCellClick(MouseEvent event, int row, int col) {
        System.out.println("Clic en celda: (" + row + ", " + col + ")");

        Map<Integer,Label> map = new HashMap<>();
        map.put(4,getQuantityShipFour());
        map.put(3,getQuantityShipThree());
        map.put(2,getQuantityShipTwo());
        map.put(1,getQuantityShipOne());
        System.out.println(map.get(getShipSpace()).getText());
        if (getShipSpace() != null && !map.get(getShipSpace()).getText().equals("0")){
            Pane clickedPane = (Pane) event.getSource();
            clickedPane.setStyle("-fx-background-color: blue;"); // Cambia color al hacer clic

            getListCoordinates().add(new Integer[]{row, col});
            if(getListCoordinates().size() == 2){
                try{
                    sorterCords();
                    boolean action = mergeCells(getMyGrid(), sorterCords());
                    if (action) {
                        System.out.println(getShipSpace());

                        Label electionLabel = map.get(getShipSpace());
                        electionLabel.setText(String.valueOf(Integer.parseInt(electionLabel.getText()) - 1));

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                setListCoordinates(new ArrayList<>());
            }
        }
    }

    @FXML
    public void initialize(){
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Pane pane = new Pane();
                pane.setPrefSize(37.6, 36.8); // Tamaño de cada celda

                // Agregar evento de clic
                final int finalRow = row;
                final int finalCol = col;
                pane.setOnMouseClicked(event -> handleCellClick(event, finalRow, finalCol));

                // Agregar Pane al GridPane
                getMyGrid().add(pane, col, row);
            }
        }
    }


}