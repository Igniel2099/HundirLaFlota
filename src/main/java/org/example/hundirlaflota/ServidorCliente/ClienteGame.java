package org.example.hundirlaflota.ServidorCliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class ClienteGame implements Runnable {

    private String name;
    private Integer[] shoot;
    private boolean isMessageServer;
    private String host = "192.168.1.29";
    private int puerto = 5001;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Integer[] getShoot() {
        return shoot;
    }

    public void setShoot(Integer[] shoot) {
        this.shoot = shoot;
    }

    public boolean getIsMessageServer() {
        return isMessageServer;
    }

    public void setIsMessageServer(boolean messageServer) {
        isMessageServer = messageServer;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    @Override
    public void run() {
        /// / nose de mmomento
    }

    public void connectionToServer() {
        DataInputStream in = null;
        DataOutputStream out = null;

        try{
            Socket socket = new Socket(getHost(), getPuerto());
            System.out.println("Conectado al servidor");
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("Hola soy " + getName() + "te he disparado en :" + Arrays.toString(getShoot()));


            in = new DataInputStream(socket.getInputStream());
            String message = in.readUTF();
            String[] split = message.split(":");
            System.out.println(message);
            // Apartir de aquie tengo que poner en shoot el disparo con el formato esperado para que depues
            // me lo recoja la pantalla startWindow tambien tengo que settear isMessageServer a true para
            // que pueda parar el wait :) y hacerlo al reves en el otro cliente, prepararlo para que reciba y despues mande
            // ey hacer otro StartWindow en el que este esperando para recibir.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
