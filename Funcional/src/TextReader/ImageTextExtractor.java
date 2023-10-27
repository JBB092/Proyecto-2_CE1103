package TextReader;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import java.io.File;

/**
 * This class provides a text extraction utility using the Tesseract OCR engine. It reads text from an image and returns the extracted text.
 *
 * @author José Barquero
 */
public class ImageTextExtractor {

    /**
     * Reads text from an image file and returns the extracted text.
     *
     * @return The extracted text from the image, or null if there was an error during processing.
     */
    public static String readText() {
        // Ruta de la imagen que deseas procesar
        String imagePath = "C:/Users/joseb/OneDrive/Escritorio/TEC/II Semestre/Datos I/Proyectos/#2/IntelliJ/Funcional/src/TextReader/Operations/operation.png";

        // Configura Tesseract
        ITesseract tesseract = new Tesseract();
        // Debes configurar la ubicación del ejecutable de Tesseract si no está en el PATH
        tesseract.setDatapath("C:/Users/joseb/OneDrive/Escritorio/TEC/II Semestre/Datos I/Proyectos/#2/IntelliJ/Funcional/Tess4J/tessdata");

        try {
            // Lee el texto de la imagen
            String result = tesseract.doOCR(new File(imagePath));

            // Filtra líneas en blanco y otros caracteres no deseados
            result = result.replaceAll("\\n+", "\n").trim(); // Elimina líneas en blanco y espacios innecesarios
            result = result.trim(); // Elimina espacios en blanco al principio y al final

            // Devuelve el texto extraído
            return result;
        } catch (Exception e) {
            System.err.println("Error al procesar la imagen: " + e.getMessage());
            // En caso de error, puedes devolver un valor nulo o una cadena vacía, según tus necesidades.
            return null;
        }
    }

    /**
     * The main entry point of the application. Calls the `readText` method and prints the extracted text if successful.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        String extractedText = readText();
        if (extractedText != null) {
            System.out.println("Texto extraído de la imagen:");
            System.out.println(extractedText);
        }
    }
}