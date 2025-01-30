package org.example.hundirlaflota.ServidorCliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente implements Runnable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PUERTO = 5000;

    }

    @Override
    public void run() {
        connectionToServer("localhost", 5000);
    }

    public void connectionToServer(String host, int puerto) {
        DataInputStream in = null;
        DataOutputStream out = null;
        Scanner sc = new Scanner(System.in);

        try{

            Socket socket = new Socket(host, puerto);
            System.out.println("Conectado al servidor");
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("Hola Soy " + getName());

            in = new DataInputStream(socket.getInputStream());
            System.out.println(in.readUTF());
            //socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
