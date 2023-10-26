package Interfaz;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import TextReader.CamaraOCR;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.Socket;
import java.util.Base64;

public class Interfaz extends Application {

    private Button btnTakePhoto;
    private ImageView cameraView;
    private Button btnCloseCamera;
    private TextField entryText;
    private CamaraOCR camaraOCR;
    private TCPClient tcpClient;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Camera");
        primaryStage.setWidth(900);
        primaryStage.setHeight(600);

        // Create the UI components
        cameraView = new ImageView();
        btnTakePhoto = new Button("Take Photo");
        btnCloseCamera = new Button("Close Camera");
        entryText = new TextField();
        Button btnSend = new Button("Send");

        // Set event handlers for the buttons
        btnTakePhoto.setOnAction(e -> {
            // Capture and display the image
            Image image = camaraOCR.captureImage();
            cameraView.setImage(image);
        });

        btnCloseCamera.setOnAction(e -> {
            // Close the camera
            camaraOCR.closeCamera();
        });

        btnSend.setOnAction(e -> {
            // Get the captured image as base64 string
            Image capturedImage = camaraOCR.getCapturedImage();
            String imageBase64 = getImageAsBase64(capturedImage);

            // Send the image to the server using TCPClient
            tcpClient.sendImage(imageBase64);
        });

        // Create the layout
        HBox buttonsContainer = new HBox(10, btnTakePhoto, btnCloseCamera);
        buttonsContainer.setAlignment(Pos.CENTER);

        HBox entryContainer = new HBox(10, entryText, btnSend);
        entryContainer.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(cameraView);
        root.setBottom(buttonsContainer);
        root.setTop(entryContainer);

        // Create the scene and show the stage
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Initialize the CamaraOCR and TCPClient
        camaraOCR = new CamaraOCR();
        tcpClient = new TCPClient();
    }

    private String getImageAsBase64(Image image) {
        // Convert the image to base64 string
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] imageBytes = outputStream.toByteArray();
        String base64String = Base64.getEncoder().encodeToString(imageBytes);

        return base64String;
        }


    private class TCPClient {

        private Socket socket;
        private OutputStream outputStream;
        private BufferedReader reader;

        public TCPClient() {
            try {
                // Connect to the server
                socket = new Socket("localhost", 8080);

                // Get the output stream to send data to the server
                outputStream = socket.getOutputStream();

                // Create a reader to read data from the server
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendImage(String imageBase64) {
            try {
                // Send the image to the server
                outputStream.write(imageBase64.getBytes());
                outputStream.flush();

                // Read the response from the server
                String response = reader.readLine();

                // Display the response in the text field
                entryText.setText(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}