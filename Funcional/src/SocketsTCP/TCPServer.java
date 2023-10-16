package SocketsTCP;

import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;

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
                        out.println("Resultado para la expresión '" + expression + "': " + result);
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
        return expression.replaceAll("(?<=\\d)(?=[+\\-*/])|(?<=[+\\-*/])(?=\\d)", " ");
    }

    private static double evaluateExpression(String expression) {
        // Separar la expresión en operandos y operadores
        String[] tokens = expression.split("\\s+");
        if (tokens.length % 2 == 0) {
            throw new IllegalArgumentException("Expresión mal formada: Número incorrecto de operandos y operadores.");
        }

        double result = Double.parseDouble(tokens[0]);
        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            double operand = Double.parseDouble(tokens[i + 1]);

            switch (operator) {
                case "+":
                    result += operand;
                    break;
                case "-":
                    result -= operand;
                    break;
                case "*":
                    result *= operand;
                    break;
                case "/":
                    if (operand == 0) {
                        throw new ArithmeticException("División por cero.");
                    }
                    result /= operand;
                    break;
                default:
                    throw new IllegalArgumentException("Operador no reconocido: " + operator);
            }
        }

        return result;
    }
}
