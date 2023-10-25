package LogicOperations;

public class BinaryXOR {
    public static void main(String[] args) {
        String binaryStr1 = "1001"; // Número binario como cadena
        String binaryStr2 = "10"; // Número binario como cadena

        int intResult = Integer.parseInt(binaryStr1, 2) ^ Integer.parseInt(binaryStr2, 2); // Operación XOR

        String binaryResult = Integer.toBinaryString(intResult);

        System.out.println("Número binario 1: " + binaryStr1);
        System.out.println("Número binario 2: " + binaryStr2);
        System.out.println("Resultado de la operación XOR: " + binaryResult);
    }
}