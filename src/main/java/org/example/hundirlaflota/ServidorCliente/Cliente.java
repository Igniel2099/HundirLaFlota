package org.example.hundirlaflota.ServidorCliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente implements Runnable {

    private String name;

    private boolean isMessageServer = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsMessageServer() {
        return isMessageServer;
    }

    public void setIsMessageServer(boolean messageServer) {
        isMessageServer = messageServer;
    }

    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PUERTO = 5000;

    }

    @Override
    public synchronized void run() {
        String serverMessage =connectionToServer(" 192.168.1.29", 5000);
        if (serverMessage != null) {
            System.out.println(serverMessage);
            setIsMessageServer(true);
            notify();
        }
    }

    public String connectionToServer(String host, int puerto) {
        DataInputStream in = null;
        DataOutputStream out = null;
        Scanner sc = new Scanner(System.in);

        try{

            Socket socket = new Socket(host, puerto);
            System.out.println("Conectado al servidor");
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("Hola Soy " + getName());

            in = new DataInputStream(socket.getInputStream());

            return in.readUTF();
            //socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
