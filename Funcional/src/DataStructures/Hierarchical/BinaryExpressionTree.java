package DataStructures.Hierarchical;

import DataStructures.NoHierarchical.Stack;

/**
 * Represents a Binary Expression Tree that can be used to evaluate expressions in postfix notation.
 *
 * This tree is constructed from a postfix expression and provides the ability to evaluate the expression.
 *
 * @author José Barquero
 */
public class BinaryExpressionTree {
    private BinaryTreeNode root;

    /**
     * Constructs an empty Binary Expression Tree.
     */
    public BinaryExpressionTree() {
        this.root = null;
    }

    /**
     * Builds a Binary Expression Tree from a postfix expression.
     *
     * @param postfixExpression The postfix expression to build the tree from.
     */
    public void buildTreeFromPostfix(String postfixExpression) {
        Stack<BinaryTreeNode> stack = new Stack<>();

        for (char c : postfixExpression.toCharArray()) {
            String token = String.valueOf(c);

            if (isOperand(token)) {
                BinaryTreeNode node = new BinaryTreeNode(token);
                stack.push(node);
            } else if (isOperator(token)) {
                BinaryTreeNode right = stack.pop();
                BinaryTreeNode left = stack.pop();

                BinaryTreeNode operatorNode = new BinaryTreeNode(token);
                operatorNode.setLeft(left);
                operatorNode.setRight(right);

                stack.push(operatorNode);
            }
        }

        this.root = stack.pop();
    }

    /**
     * Evaluates the expression stored in the Binary Expression Tree.
     *
     * @return The result of the expression evaluation.
     */
    public int evaluate() {
        return evaluate(root);
    }

    /**
     * Recursively evaluates the expression starting from the given node.
     *
     * @param node The node from which to start the evaluation.
     * @return The result of the evaluation.
     */
    private int evaluate(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }

        if (isOperand(node.getData())) {
            return Integer.parseInt(node.getData());
        }

        int leftValue = evaluate(node.getLeft());
        int rightValue = evaluate(node.getRight());

        switch (node.getData()) {
            case "+":
                return leftValue + rightValue;
            case "-":
                return leftValue - rightValue;
            case "*":
                return leftValue * rightValue;
            case "/":
                if (rightValue == 0) {
                    throw new ArithmeticException("Division by zero.");
                }
                return leftValue / rightValue;
            default:
                throw new IllegalArgumentException("Invalid operator: " + node.getData());
        }
    }

    /**
     * Checks if a given token is an operand.
     *
     * @param token The token to check.
     * @return True if the token is an operand, false otherwise.
     */
    private boolean isOperand(String token) {
        return token.matches("\\d+");
    }

    /**
     * Checks if a given token is an operator.
     *
     * @param token The token to check.
     * @return True if the token is an operator, false otherwise.
     */
    private boolean isOperator(String token) {
        return token.matches("[+\\-*/]");
    }
}