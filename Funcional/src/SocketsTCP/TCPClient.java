package SocketsTCP;

import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        String serverAddress = "192.168.100.17"; // Dirección IP del servidor
        int serverPort = 12345;

        try {
            Socket socket = new Socket(serverAddress, serverPort);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Hola, servidor!");

            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}