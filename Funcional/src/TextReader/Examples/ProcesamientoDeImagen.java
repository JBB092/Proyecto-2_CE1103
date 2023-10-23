package TextReader.Examples;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * This class demonstrates image processing using OpenCV library.
 */
public class ProcesamientoDeImagen {

    /**
     * Main method to perform image processing.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        // Load the OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Input and output image paths
        String inputImagePath = "input/image.jpg";
        String outputImagePath = "output/image.jpg";

        // Load the input image
        Mat inputImage = Imgcodecs.imread(inputImagePath);

        // Check if the image was loaded successfully
        if (inputImage.empty()) {
            System.err.println("Failed to load the input image: " + inputImagePath);
            return;
        }

        // Apply a Gaussian blur filter to the image
        Mat outputImage = new Mat();
        Imgproc.GaussianBlur(inputImage, outputImage, new org.opencv.core.Size(5, 5), 0);

        // Save the output image to disk
        Imgcodecs.imwrite(outputImagePath, outputImage);

        // Check if the image was saved successfully
        if (!outputImage.empty()) {
            System.out.println("Image processing completed. The image was saved to: " + outputImagePath);
        } else {
            System.err.println("Failed to save the output image.");
        }
    }
}