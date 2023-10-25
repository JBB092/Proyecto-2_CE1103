package LogicOperations;

public class BinaryNOT {
    public static void main(String[] args) {
        String binaryStr = "110101"; // Número binario como cadena

        String result = binaryStringNOT(binaryStr); // Operación NOT

        System.out.println("Número binario original: " + binaryStr);
        System.out.println("Resultado de la operación NOT: " + result);
    }

    public static String binaryStringNOT(String binaryStr) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < binaryStr.length(); i++) {
            char bit = binaryStr.charAt(i);
            if (bit == '0') {
                result.append('1');
            } else if (bit == '1') {
                result.append('0');
            } else {
                // Manejo de errores si la cadena contiene caracteres no válidos
                return "Error: Entrada no válida";
            }
        }

        return result.toString();
    }
}