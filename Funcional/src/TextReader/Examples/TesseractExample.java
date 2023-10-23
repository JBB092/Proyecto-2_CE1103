package TextReader.Examples;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * This class demonstrates how to perform Optical Character Recognition (OCR) using Tesseract.
 */
public class TesseractExample {

    /**
     * Main method to perform OCR on an image using Tesseract.
     *
     * @param args The command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        Tesseract tesseract = new Tesseract();

        try {
            // Replace with the actual image path
            String imagePath = "path/to/your/image.png";
            String result = tesseract.doOCR(new File(imagePath));
            System.out.println("Extracted text: " + result);
        } catch (TesseractException e) {
            System.err.println("Error performing OCR: " + e.getMessage());
        }
    }
}