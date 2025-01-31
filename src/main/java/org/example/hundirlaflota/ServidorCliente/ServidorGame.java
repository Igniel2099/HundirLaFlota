package org.example.hundirlaflota.ServidorCliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorGame {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket1 = null;
        Socket socket2 = null;

        DataInputStream in1;
        DataOutputStream out1;
        DataInputStream in2;
        DataOutputStream out2;

        final int PUERTO = 5001;

        try{
            System.out.println("Servidor Game cargado");

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
