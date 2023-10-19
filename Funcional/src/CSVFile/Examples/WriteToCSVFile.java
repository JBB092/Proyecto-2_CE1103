package CSVFile.Examples;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteToCSVFile {
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\joseb\\OneDrive\\Escritorio\\TEC\\II Semestre\\Datos I\\Proyectos\\#2\\Funcional\\src\\CSVFile\\RegistroOperaciones.csv"; // Nombre del archivo CSV

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String operacion = "5*5";
            double resultado = 25;
            Date fecha = new Date(); // Usar la fecha actual

            // Formatear la fecha en el formato adecuado
            String fechaFormateada = dateFormat.format(fecha);

            // Crear una nueva línea con los datos y escribirla en el archivo
            String newLine = operacion + "," + resultado + "," + fechaFormateada;
            writer.write(newLine);
            writer.newLine(); // Agregar un salto de línea

            System.out.println("Datos agregados al archivo CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}