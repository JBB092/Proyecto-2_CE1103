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
 * @author José Barquero
 */
public class Camera extends JFrame {

    private final JLabel cameraScreen;
    private final JButton btnCapture;
    private VideoCapture capture;
    private Mat image;
    private boolean clicked=false;

    /**
     * Initializes the camera application, sets up the user interface, and starts the camera.
     */
    public Camera (){

        setLayout(null);

        cameraScreen = new JLabel();
        cameraScreen.setBounds(0,0,640,480);
        add(cameraScreen);

        btnCapture =new JButton("capture");
        btnCapture.setBounds(300,480,80,40);
        add(btnCapture);

        btnCapture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicked = true;
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                capture.release();
                image.release();
                System.exit(0);
            }
        });

        setSize(new Dimension(640,560));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Starts the camera, captures video frames, and allows the user to capture images.
     */
    public void startCamera(){
        capture = new VideoCapture(0);
        image = new Mat();
        byte [] imageData;
        ImageIcon icon;

        while (true){
            capture.read(image);

            final MatOfByte buf = new MatOfByte();
            Imgcodecs.imencode(".jpg",image,buf);

            imageData = buf.toArray();

            icon = new ImageIcon(imageData);
            cameraScreen.setIcon(icon);

            if(clicked){
                String outputPath = "src/TextReader/Operations/operation.jpg";
                Imgcodecs.imwrite(outputPath, image);

                clicked=false;
            }
        }
    }

    /**
     * The entry point of the application.
     * Loads the OpenCV library, creates an instance of the Camera class, and starts the camera in a separate thread.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Camera camera = new Camera();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        camera.startCamera();
                    }
                }).start();
            }
        });
    }
}