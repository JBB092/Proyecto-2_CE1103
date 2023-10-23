package TextReader;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**
 * This class demonstrates capturing an image from a camera and performing OCR using Tesseract.
 */
public class CamaraOCR {

    /**
     * Main method to capture an image from a camera and perform OCR on the captured image.
     *
     * @param args The command-line arguments (not used in this example).
     */
    public static void main(String[] args) {

        System.load("C:\\Users\\joseb\\Downloads\\opencv\\build\\java\\x64\\opencv_java480.dll"); //******** */

        // Load the native OpenCV libraries
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Initialize the camera
        VideoCapture camera = new VideoCapture(0);

        // Check if the camera opened successfully
        if (!camera.isOpened()) {
            System.out.println("Could not open the camera.");
            return;
        }

        Mat frame = new Mat();
        camera.read(frame);

        // Capture an image from the camera
        if (!frame.empty()) {
            Imgcodecs.imwrite("captured_image.png", frame);

            // Perform text recognition using Tesseract OCR
            Tesseract tesseract = new Tesseract();
            try {
                Mat grayImage = new Mat();
                Imgproc.cvtColor(frame, grayImage, Imgproc.COLOR_BGR2GRAY);
                Imgcodecs.imwrite("gray_image.png", grayImage);

                // Save the image to a temporary file
                File tempImage = new File("gray_image.png");

                // Process the image in grayscale
                tesseract.setTessVariable("tessedit_char_whitelist", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");
                tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR");

                String result = tesseract.doOCR(tempImage);
                System.out.println("Recognized Text: " + result);

                // Delete the temporary file
                tempImage.delete();
            } catch (TesseractException e) {
                System.err.println("Error performing OCR: " + e.getMessage());
            }
        } else {
            System.out.println("Could not capture an image from the camera.");
        }

        // Release the camera and close the display window
        camera.release();
        HighGui.destroyAllWindows();
    }
}