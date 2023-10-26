package TextReader;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import java.io.File;

public class ImageTextExtractor {
    public static void main(String[] args) {
        // Ruta de la imagen que deseas procesar
        String imagePath = "C:/Users/joseb/OneDrive/Escritorio/TEC/II Semestre/Datos I/Proyectos/#2/IntelliJ/Funcional/src/TextReader/Operations/operation.jpg";

        // Configura Tesseract
        ITesseract tesseract = new Tesseract();
        // Debes configurar la ubicación del ejecutable de Tesseract si no está en el PATH
        tesseract.setDatapath("C:/Users/joseb/OneDrive/Escritorio/TEC/II Semestre/Datos I/Proyectos/#2/IntelliJ/Funcional/Tess4J/tessdata");

        try {
            // Lee el texto de la imagen
            String result = tesseract.doOCR(new File(imagePath));
            // Muestra el texto en la terminal
            System.out.println("Texto extraído de la imagen:");
            System.out.println(result);
        } catch (Exception e) {
            System.err.println("Error al procesar la imagen: " + e.getMessage());
        }
    }
}