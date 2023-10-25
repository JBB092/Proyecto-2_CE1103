package LogicOperations;

public class BinaryAND {
    public static void main(String[] args) {
        String binaryStr1 = "110101"; // Número binario como cadena
        String binaryStr2 = "101010"; // Número binario como cadena

        int intResult = Integer.parseInt(binaryStr1, 2) & Integer.parseInt(binaryStr2, 2); // Operación AND

        String binaryResult = Integer.toBinaryString(intResult);

        System.out.println("Número binario 1: " + binaryStr1);
        System.out.println("Número binario 2: " + binaryStr2);
        System.out.println("Resultado de la operación AND: " + binaryResult);
    }
}