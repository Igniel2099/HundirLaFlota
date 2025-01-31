package org.example.hundirlaflota.ServidorCliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket1 = null;
        Socket socket2 = null;

        DataInputStream in1;
        DataOutputStream out1;
        DataInputStream in2;
        DataOutputStream out2;

        final int PUERTO = 5000;
        try{
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor conectado");
            while(true){

                for (int i = 0; i < 2;i++){
                    socket1 = serverSocket.accept();
                    socket2 = serverSocket.accept();
                    System.out.println("Dos clientes se conectaron al servidor");
                    // Agregar Logica de prog
                    in1 = new DataInputStream(socket1.getInputStream());
                    in2 = new DataInputStream(socket2.getInputStream());

                    out1 = new DataOutputStream(socket1.getOutputStream());
                    out2 = new DataOutputStream(socket2.getOutputStream());

                    String message1 = in1.readUTF();
                    String message2 = in2.readUTF();
                    out2.writeUTF(message1);
                    out1.writeUTF(message2);
                }
                ServidorGame.main(null);



            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
