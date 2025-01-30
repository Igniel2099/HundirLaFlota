package org.example.hundirlaflota.Controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.hundirlaflota.ServidorCliente.Cliente;

public class UploadController {
    private Stage primaryStage;

    private String userName;

    private Cliente cliente =  new Cliente();

    @FXML
    private ImageView imageViewRotate;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ImageView getImageViewRotate() {
        return imageViewRotate;
    }

    public void setImageViewRotate(ImageView imageViewRotate) {
        this.imageViewRotate = imageViewRotate;
    }

    @FXML
    private void initialize() {
        Timeline timeline = new Timeline();

        // Crear un KeyFrame que haga girar la imagen
        KeyFrame keyFrame = new KeyFrame(Duration.millis(50), e -> {
            // Incrementar la rotación en 1 grado cada vez
            getImageViewRotate().setRotate(getImageViewRotate().getRotate() + 15);
        });

        // Añadir el KeyFrame al Timeline
        timeline.getKeyFrames().add(keyFrame);

        // Configurar el Timeline para que se ejecute indefinidamente
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true); // Hace que la animación se invierta automáticamente

        // Iniciar la animación
        timeline.play();

        if (cliente != null) {
            Thread thread = new Thread(getCliente());
            thread.start();
        }


    }



}
