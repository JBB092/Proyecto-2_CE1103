package DataStructures.Hierarchical;

import DataStructures.NoHierarchical.Stack;
import DataStructures.NoHierarchical.Queue;

public class BinaryExpressionTree {
    private BinaryTreeNode root;

    public BinaryExpressionTree() {
        this.root = null;
    }

    public void buildTreeFromPostfix(String postfixExpression) {
        Stack stack = new Stack();

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

    public int evaluate() {
        return evaluate(root);
    }

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

    private boolean isOperand(String token) {
        return token.matches("\\d+");
    }

    private boolean isOperator(String token) {
        return token.matches("[+\\-*/]");
    }
}