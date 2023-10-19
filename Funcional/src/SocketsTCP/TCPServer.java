package SocketsTCP;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

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

        /**
         * Constructs a ClientHandler.
         *
         * @param clientSocket The client's socket.
         * @param dateFormat The date format for timestamping.
         */
        public ClientHandler(Socket clientSocket, SimpleDateFormat dateFormat) {
            this.clientSocket = clientSocket;
            this.dateFormat = dateFormat;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String request;
                while ((request = in.readLine()) != null) {
                    if (request.equalsIgnoreCase("Historial") || request.equalsIgnoreCase("historial")){
                        sendHistorialData(out);
                    }
                    else{
                        // Normalize the expression (add spaces around operators)
                        String normalizedExpression = normalizeExpression(request);

                        // Parse the expression and perform the operation using InfixToPostfixAndEval
                        int result;
                        try {
                            result = evaluateExpression(normalizedExpression);
                            String currentDate = dateFormat.format(new Date());

                            // Send the operation, result, and date to the client
                            out.println(request + "," + result + "," + currentDate);

                            // Append the data to the CSV file
                            appendToCSVFile(request, result, currentDate);
                        } catch (ArithmeticException | IllegalArgumentException e) {
                            out.println("Invalid operation for expression '" + request + "': " + e.getMessage());
                        }
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

        /**
         * Appends the operation, result, and date to a CSV file.
         *
         * @param operation The mathematical operation.
         * @param result The result of the operation.
         * @param currentDate The current date in "dd/MM/yyyy" format.
         */
        private void appendToCSVFile(String operation, int result, String currentDate) {
            String currentDirectory = System.getProperty("user.dir");
            String csvFile = currentDirectory+"/src/CSVFile/RegistroOperaciones.csv";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true))) {
                // Create a new line with the data and write it to the file
                String newLine = operation + "," + result + "," + currentDate;
                writer.write(newLine);
                writer.newLine(); // Add a line break
                System.out.println("Data added to the CSV file.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Sends historical data to the client.
         *
         * @param out The PrintWriter for sending data to the client.
         */
        private void sendHistorialData(PrintWriter out) {
            String currentDirectory = System.getProperty("user.dir");
            String csvFilePath= currentDirectory+"/src/CSVFile/RegistroOperaciones.csv";

            DefaultTableModel tableModel = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
            };

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (tableModel.getColumnCount() == 0) {
                        // Add table headers
                        for (String header : data) {
                            tableModel.addColumn(header);
                        }
                    } else {
                        // Add data rows
                        tableModel.addRow(data);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                out.println("Error reading historical data.");
                return;
            }

            // Create a JTable to display the data
            JTable table = new JTable(tableModel);

            // Create a cell renderer to center-align the data
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);

            // Apply the center renderer to all columns
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            JScrollPane scrollPane = new JScrollPane(table);

            JFrame frame = new JFrame("History of Operations");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(scrollPane, BorderLayout.CENTER);

            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> frame.dispose());
            frame.add(closeButton, BorderLayout.SOUTH);

            frame.setPreferredSize(new Dimension(800, 400));
            frame.pack();
            frame.setVisible(true);
        }    
    }
}