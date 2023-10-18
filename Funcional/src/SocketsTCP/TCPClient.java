package SocketsTCP;

import java.io.*;
import java.net.*;

/**
 * Represents a TCP client that communicates with a server over a TCP connection.
 *
 * This client allows the user to send mathematical expressions to the server for evaluation.
 * It also provides the ability to exit the client by entering 'exit'.
 *
 * @author José Barquero
 */
public class TCPClient {
    /**
     * The main method to start the TCP client.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Change this to the server's IP address if different
        int serverPort = 12345;

        try (Socket socket = new Socket(serverAddress, serverPort);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            while (true) {
                System.out.print("Enter the mathematical expression (or 'exit' to quit): ");
                String expression = userInput.readLine();

                // Send the expression to the server
                out.println(expression);

                // If the client wants to exit, close the connection
                if (expression.equalsIgnoreCase("exit")) {
                    break;
                }

                // Read and display the server's response
                String response = in.readLine();
                System.out.println("Server response: " + response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}