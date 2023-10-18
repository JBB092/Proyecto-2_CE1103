package SocketsTCP;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import DataStructures.NoHierarchical.StackTCP;

/**
 * Represents a TCP server that evaluates mathematical expressions received from clients.
 *
 * This server listens for incoming client connections and handles evaluation of mathematical expressions.
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

                // Set up input and output streams
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Read operations until the client terminates the connection
                String expression;
                while ((expression = in.readLine()) != null) {
                    // Normalize the expression (add spaces around operators)
                    String normalizedExpression = normalizeExpression(expression);

                    // Parse the expression and perform the operation
                    double result;
                    try {
                        result = evaluateExpression(normalizedExpression);

                        // Get the current date
                        String currentDate = dateFormat.format(new Date());

                        // Send the operation, result, and date to the client
                        out.println(expression + "," + result + "," + currentDate);
                    } catch (ArithmeticException | IllegalArgumentException e) {
                        out.println("Invalid operation for expression '" + expression + "': " + e.getMessage());
                    }
                }

                // Close the connection with the client
                clientSocket.close();
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
     * Normalizes the expression by adding spaces around operators.
     *
     * @param expression The input expression to normalize.
     * @return The normalized expression with spaces around operators.
     */
    private static String normalizeExpression(String expression) {
        return expression.replaceAll("(?<=\\d)(?=[+\\-*/()])|(?<=[+\\-*/()])(?=\\d)", " ");
    }

    /**
     * Evaluates the given expression and returns the result.
     *
     * @param expression The expression to evaluate.
     * @return The result of the evaluated expression.
     */
    private static double evaluateExpression(String expression) {
        // Split the expression into operands and operators
        String[] tokens = expression.split("\\s+");
        StackTCP operandStack = new StackTCP();
        StackTCP operatorStack = new StackTCP();

        for (String token : tokens) {
            if (token.matches("[0-9]+(\\.[0-9]+)?")) {
                operandStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    performOperation(operandStack, operatorStack);
                }
                operatorStack.pop(); // Remove the '('
            } else if (token.matches("[+\\-*/]")) {
                while (!operatorStack.isEmpty() && hasHigherOrEqualPrecedence(operatorStack.peek(), token)) {
                    performOperation(operandStack, operatorStack);
                }
                operatorStack.push(token);
            }
        }

        while (!operatorStack.isEmpty()) {
            performOperation(operandStack, operatorStack);
        }

        return Double.parseDouble(operandStack.pop());
    }

    /**
     * Checks if one operator has higher or equal precedence than another.
     *
     * @param op1 The first operator.
     * @param op2 The second operator.
     * @return True if op1 has higher or equal precedence, false otherwise.
     */
    private static boolean hasHigherOrEqualPrecedence(String op1, String op2) {
        if ((op1.equals("*") || op1.equals("/")) && (op2.equals("+") || op2.equals("-"))) {
            return false;
        }
        return true;
    }

    /**
     * Performs an operation using operands and operators from the stacks.
     *
     * @param operandStack The stack containing operands.
     * @param operatorStack The stack containing operators.
     */
    private static void performOperation(StackTCP operandStack, StackTCP operatorStack) {
        if (operandStack.isEmpty() || operatorStack.isEmpty()) {
            throw new RuntimeException("Insufficient operands or operators for the operation.");
        }

        double operand2 = Double.parseDouble(operandStack.pop());
        double operand1 = Double.parseDouble(operandStack.pop());
        String operator = operatorStack.pop();

        switch (operator) {
            case "+":
                operandStack.push((operand1 + operand2) + "");
                break;
            case "-":
                operandStack.push((operand1 - operand2) + "");
                break;
            case "*":
                operandStack.push((operand1 * operand2) + "");
                break;
            case "/":
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero.");
                }
                operandStack.push((operand1 / operand2) + "");
                break;
            default:
                throw new IllegalArgumentException("Unrecognized operator: " + operator);
        }
    }
}
