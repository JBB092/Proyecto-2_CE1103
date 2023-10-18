package SocketsTCP;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import DataStructures.NoHierarchical.Stack;

public class TCPServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Servidor escuchando en el puerto 12345...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde: " + clientSocket.getInetAddress());

                // Configurar los streams de entrada y salida
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Leer operaciones hasta que el cliente termine la conexión
                String expression;
                while ((expression = in.readLine()) != null) {
                    // Normalizar la expresión (agregar espacios alrededor de operadores)
                    String normalizedExpression = normalizeExpression(expression);

                    // Parsear la expresión y realizar la operación
                    double result;
                    try {
                        result = evaluateExpression(normalizedExpression);

                        // Obtener la fecha actual
                        String currentDate = dateFormat.format(new Date());

                        // Enviar la operación, resultado y fecha al cliente
                        out.println(expression + "," + result + "," + currentDate);
                    } catch (ArithmeticException | IllegalArgumentException e) {
                        out.println("Operación inválida para la expresión '" + expression + "': " + e.getMessage());
                    }
                }

                // Cerrar la conexión con el cliente
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

    private static String normalizeExpression(String expression) {
        // Agregar espacios alrededor de operadores para que la expresión esté bien formada
        return expression.replaceAll("(?<=\\d)(?=[+\\-*/()])|(?<=[+\\-*/()])(?=\\d)", " ");
    }

    private static double evaluateExpression(String expression) {
        // Separar la expresión en operandos y operadores
        String[] tokens = expression.split("\\s+");
        Stack operandStack = new Stack();
        Stack operatorStack = new Stack();

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
    
    private static boolean hasHigherOrEqualPrecedence(String op1, String op2) {
        if ((op1.equals("*") || op1.equals("/")) && (op2.equals("+") || op2.equals("-"))) {
            return false;
        }
        return true;
    }

    private static void performOperation(Stack operandStack, Stack operatorStack) {
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