package Interfaz;

import SocketsTCP.TCPClient;
import SocketsTCP.TCPServer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Interfaz extends Application {

    private TextField operacionTextField;
    private Label resultadoLabel;
    private TCPClient cliente; // Referencia al cliente
    private TCPServer server; // Referencia al servidor

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Configurar la ventana
        primaryStage.setTitle("Calculadora");

        // Crear los componentes
        operacionTextField = new TextField();
        Button aceptarButton = new Button("Aceptar");
        resultadoLabel = new Label();

        // Configurar el evento del botón
        aceptarButton.setOnAction(e -> {
            String operacion = operacionTextField.getText();
            String resultado = enviarOperacionAlServidor(operacion);
            resultadoLabel.setText(resultado);
        });

        // Configurar el diseño de la ventana
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(operacionTextField, aceptarButton, resultadoLabel);

        // Mostrar la ventana
        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String enviarOperacionAlServidor(String operacion) {
        try {
            // Establecer una conexión con el servidor
            Socket socket = new Socket("localhost", 12345);

            // Obtener los flujos de entrada y salida del socket
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Enviar la operación al servidor
            out.println(operacion);

            // Recibir el resultado del servidor
            String resultado = in.readLine();

            // Cerrar la conexión y los flujos de entrada/salida
            out.close();
            in.close();
            socket.close();

            return resultado;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: No se pudo conectar con el servidor";
             }
        }
    }