package CSVFile.Examples;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReadCSVFile {
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\joseb\\OneDrive\\Escritorio\\TEC\\II Semestre\\Datos I\\Proyectos\\#2\\Funcional\\src\\CSVFile\\RegistroOperaciones.csv"; // Nombre del archivo CSV

        // Formato de fecha para parsear la columna "Fecha"
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            // Leer encabezados (primera línea)
            line = br.readLine();
            if (line == null) {
                System.out.println("El archivo CSV está vacío.");
                return;
            }
            String[] headers = line.split(",");

            // Verificar que el archivo tenga las columnas requeridas
            if (headers.length < 3 || !headers[0].equals("Operacion") || !headers[1].equals("Resultado") || !headers[2].equals("Fecha")) {
                System.out.println("El archivo CSV no tiene las columnas requeridas.");
                return;
            }

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                // Recuperar los valores
                String operacion = data[0];
                double resultado = Double.parseDouble(data[1]);
                Date fecha = dateFormat.parse(data[2]);

                // Procesar los datos como desees
                System.out.println("Operacion: " + operacion);
                System.out.println("Resultado: " + resultado);
                System.out.println("Fecha: " + dateFormat.format(fecha));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
