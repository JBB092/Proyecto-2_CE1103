package TextReader;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class provides a simple camera application that captures and displays real-time video from the computer's camera using OpenCV.
 * Users can capture an image and save it with the name "operation.jpg" in the "src/TextReader/Operations" directory.
 *
 * @author José Barquero
 */
public class Camera extends JFrame {

    private final JLabel cameraScreen;
    private final JButton btnCapture;
    private JButton btnClose; // Botón Close
    private VideoCapture capture;
    private Mat image;
    private boolean clicked = false;

    /**
     * Initializes the camera application, sets up the user interface, and starts the camera.
     */
    public Camera() {
        setLayout(null);

        cameraScreen = new JLabel();
        cameraScreen.setBounds(0, 0, 640, 480);
        add(cameraScreen);

        btnCapture = new JButton("Capture");
        btnCapture.setBounds(300, 480, 80, 40);
        add(btnCapture);

        btnClose = new JButton("Close"); // Botón Close
        btnClose.setBounds(400, 480, 80, 40); // Posición del botón Close
        add(btnClose); // Agregar el botón Close

        btnCapture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicked = true;
            }
        });

        // Acción para el botón Close
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeCamera();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeCamera();
                dispose();
            }
        });

        setSize(new Dimension(640, 560));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Starts the camera, captures video frames, and allows the user to capture images.
     *
     * @param running Set to true to start the camera.
     */
    public void startCamera(boolean running) {
        capture = new VideoCapture(0);
        image = new Mat();
        byte[] imageData;
        ImageIcon icon;

        while (running) {
            if (capture.read(image)) {
                final MatOfByte buf = new MatOfByte();
                Imgcodecs.imencode(".png", image, buf);
                imageData = buf.toArray();
                icon = new ImageIcon(imageData);
                cameraScreen.setIcon(icon);

                if (clicked) {
                    String outputPath = "src/TextReader/Operations/operation.png";
                    Imgcodecs.imwrite(outputPath, image);
                    clicked = false;
                    running = false;
                }
            } else {
                System.err.println("Unable to capture image from the camera.");
                running = false;
            }
        }
    }

    /**
     * Stops the camera and releases resources.
     */
    private void closeCamera() {

        if (capture != null && capture.isOpened()) {
            capture.release();
        }
        dispose();
    }

    /**
     * Runs the camera application. Loads the OpenCV library, creates an instance of the Camera class, and starts the camera in a separate thread.
     *
     * @param running Set to true to start the camera.
     */
    public void runCamera(boolean running) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Camera camera = new Camera();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        camera.startCamera(running);
                    }
                }).start();
            }
        });
    }

    /**
     * The entry point of the application. Loads the OpenCV library, creates an instance of the Camera class, and starts the camera in a separate thread.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Camera camera = new Camera();
        boolean run=true;
        camera.runCamera(run);
    }
}