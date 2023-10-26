package DataStructures.Hierarchical;

/**
 * This class provides a method for evaluating a mathematical expression represented as a binary expression tree.
 * It supports the evaluation of simple arithmetic expressions containing addition, subtraction, multiplication,
 * division, and exponentiation.
 *
 * @author José Barquero
 */
public class Evaluation {
    /**
     * Evaluates a mathematical expression represented by a binary expression tree.
     *
     * @param tree The binary expression tree representing the expression.
     * @param root The root node of the binary expression tree.
     * @return The result of the evaluated mathematical expression.
     * @throws IllegalArgumentException If the binary expression tree is empty.
     * @throws ArithmeticException If division by zero is encountered during evaluation.
     */
    public static double evaluateExpressionTree(BinaryExpressionTree tree, TreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException("El árbol de expresión está vacío.");
        }

        // If the current node is an operand, simply return its value as a number.
        if (!tree.isOperator(root.valor)) {
            return Double.parseDouble(root.valor);
        }

        // If the node is an operator, evaluate the left and right operands and apply the operation.
        double leftValue = evaluateExpressionTree(tree, root.left);
        double rightValue = evaluateExpressionTree(tree, root.right);

        return switch (root.valor) {
            case "+" -> leftValue + rightValue;
            case "-" -> leftValue - rightValue;
            case "*" -> leftValue * rightValue;
            case "/" -> {
                if (rightValue == 0) {
                    throw new ArithmeticException("Division by zero is not allowed");
                }
                yield leftValue / rightValue;
            }
            case "^" -> Math.pow(leftValue, rightValue);
            case "&" -> (float) ((int) leftValue & (int) rightValue);
            default -> throw new IllegalArgumentException("Invalid operator: " + root.valor);
        };
    }
}