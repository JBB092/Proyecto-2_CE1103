package TextReader.Examples;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ProcesamientoDeImagen {
    public static void main(String[] args) {
        // Carga la biblioteca OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Ruta de la imagen de entrada y salida
        String rutaImagenEntrada = "ruta/imagen_entrada.jpg";
        String rutaImagenSalida = "ruta/imagen_salida.jpg";

        // Carga la imagen de entrada
        Mat imagenEntrada = Imgcodecs.imread(rutaImagenEntrada);

        // Verifica si la imagen se cargó correctamente
        if (imagenEntrada.empty()) {
            System.err.println("No se pudo cargar la imagen de entrada: " + rutaImagenEntrada);
            return;
        }

        // Aplica un filtro de desenfoque a la imagen
        Mat imagenSalida = new Mat();
        Imgproc.GaussianBlur(imagenEntrada, imagenSalida, new org.opencv.core.Size(5, 5), 0);

        // Guarda la imagen de salida en el disco
        Imgcodecs.imwrite(rutaImagenSalida, imagenSalida);

        // Verifica si la imagen se guardó correctamente
        if (!imagenSalida.empty()) {
            System.out.println("Procesamiento de imagen completado. La imagen se guardó en: " + rutaImagenSalida);
        } else {
            System.err.println("No se pudo guardar la imagen de salida.");
        }
    }
}
