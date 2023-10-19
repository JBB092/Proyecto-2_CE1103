package SocketsTCP;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import DataStructures.Hierarchical.BinaryExpressionTree;
import DataStructures.Hierarchical.InfixToPostfixAndEval;

/**
 * This class represents a TCP server that evaluates mathematical expressions received from clients.
 * It listens for incoming client connections and handles the evaluation of mathematical expressions.
 *
 * @author José Barquero
 */
public class TCPServer {
    /**
     * The main method to start the TCP server.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Server listening on port 12345...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected from: " + clientSocket.getInetAddress());

                // Use a thread to handle each client connection
                Thread clientThread = new Thread(new ClientHandler(clientSocket, dateFormat));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Inner class to handle client connections in separate threads.
     */
    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private SimpleDateFormat dateFormat;

        public ClientHandler(Socket clientSocket, SimpleDateFormat dateFormat) {
            this.clientSocket = clientSocket;
            this.dateFormat = dateFormat;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String expression;
                while ((expression = in.readLine()) != null) {
                    // Normalize the expression (add spaces around operators)
                    String normalizedExpression = normalizeExpression(expression);

                    // Parse the expression and perform the operation using InfixToPostfixAndEval
                    int result;
                    try {
                        result = evaluateExpression(normalizedExpression);
                        String currentDate = dateFormat.format(new Date());

                        // Send the operation, result, and date to the client
                        out.println(expression + "," + result + "," + currentDate);
                    } catch (ArithmeticException | IllegalArgumentException e) {
                        out.println("Invalid operation for expression '" + expression + "': " + e.getMessage());
                    }
                }

                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Normalizes the expression by adding spaces around operators.
         *
         * @param expression The input expression to normalize.
         * @return The normalized expression with spaces around operators.
         */
        private String normalizeExpression(String expression) {
            return expression.replaceAll("(?<=\\d)(?=[+\\-*/()])|(?<=[+\\-*/()])(?=\\d)", " ");
        }

        /**
         * Evaluates the given expression and returns the result.
         *
         * @param expression The expression to evaluate.
         * @return The result of the evaluated expression.
         */
        private int evaluateExpression(String expression) {
            String postfix = InfixToPostfixAndEval.infixToPostfix(expression);
            BinaryExpressionTree expressionTree = new BinaryExpressionTree();
            expressionTree.buildTreeFromPostfix(postfix);
            return expressionTree.evaluate();
        }
    }
}