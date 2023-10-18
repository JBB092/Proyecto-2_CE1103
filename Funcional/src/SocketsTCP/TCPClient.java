package SocketsTCP;

import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Cambiar esto a la dirección IP del servidor si es diferente
        int serverPort = 12345;

        try (Socket socket = new Socket(serverAddress, serverPort);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            while (true) {
                System.out.print("Ingresa la expresión matemática (o 'exit' para salir): ");
                String expression = userInput.readLine();

                // Enviar la expresión al servidor
                out.println(expression);

                // Si el cliente desea salir, cerramos la conexión
                if (expression.equalsIgnoreCase("exit")) {
                    break;
                }

                // Leer y mostrar la respuesta del servidor
                String response = in.readLine();
                System.out.println("Respuesta del servidor: " + response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}