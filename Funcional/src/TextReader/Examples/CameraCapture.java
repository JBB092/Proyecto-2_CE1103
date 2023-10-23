package TextReader.Examples;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;

/**
 * A Java application for capturing real-time video from the computer's camera using OpenCV,
 * displaying it in a JFrame, and allowing the user to capture an image from the video stream.
 * @author José Barquero
 */
public class CameraCapture extends JFrame {
    private VideoCapture capture;
    private JPanel videoPanel;
    private JButton captureButton;

    /**
     * Initializes OpenCV, creates a window, and sets up the camera capture.
     */
    public CameraCapture() {
        // Initialize OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Create a window
        setTitle("Real-Time Camera Capture");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Initialize the camera
        capture = new VideoCapture(0); // 0 represents the default camera

        if (!capture.isOpened()) {
            System.err.println("Error opening the camera.");
            System.exit(1);
        }

        // Create the video panel
        videoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Mat frame = new Mat();
                capture.read(frame);
                Mat2BufferedImage(frame);
                super.paintComponent(g);
                g.drawImage(Mat2BufferedImage(frame), 0, 0, this);
                repaint();
            }
        };
        videoPanel.setPreferredSize(new Dimension(640, 480));

        // Create the capture button
        captureButton = new JButton("Capture Image");
        captureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mat frame = new Mat();
                capture.read(frame);

                // Save the captured image to the specified location
                String outputPath = "C:\\Users\\joseb\\OneDrive\\Desktop\\Funcional\\src\\TextReader\\Operations\\operation.jpg";
                Imgcodecs.imwrite(outputPath, frame);
                System.out.println("Image saved to: " + outputPath);
            }
        });

        // Add components to the window
        add(videoPanel, BorderLayout.CENTER);
        add(captureButton, BorderLayout.SOUTH);

        // Show the window
        setVisible(true);
    }

    /**
     * Converts an OpenCV Mat object to a BufferedImage.
     *
     * @param mat The Mat object to convert.
     * @return A BufferedImage representing the Mat.
     */
    public BufferedImage Mat2BufferedImage(Mat mat) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = mat.channels() * mat.cols() * mat.rows();
        byte[] bytes = new byte[bufferSize];
        mat.get(0, 0, bytes);
        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(bytes, 0, targetPixels, 0, bytes.length);
        return image;
    }

    /**
     * The entry point of the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CameraCapture());
    }
}