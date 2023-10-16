package DataStructures.Hierarchical;

import DataStructures.NoHierarchical.Stack;

/**
 * Represents an expression tree that can evaluate postfix expressions.
 *
 * @param <T> The type of data to be stored in the tree.
 * 
 * @author José Barquero
 */
public class ExpressionTree<T> {
    private TreeNode<T> root;

    /**
     * Constructs an empty expression tree.
     */
    public ExpressionTree() {
        this.root = null;
    }

    /**
     * Builds the expression tree from a postfix expression.
     *
     * @param postfixExpression The postfix expression in an array of tokens.
     */
    public void buildTree(String[] postfixExpression) {
        Stack<TreeNode<T>> stack = new Stack<>();

        for (String token : postfixExpression) {
            try {
                if (isOperand(token)) {
                    TreeNode<T> node = new TreeNode<>((T) (Object) token);
                    stack.push(node);
                } else if (isOperator(token)) {
                    TreeNode<T> rightOperand = stack.pop();
                    TreeNode<T> leftOperand = stack.pop();
                    TreeNode<T> operatorNode = new TreeNode<>((T) (Object) token);
                    operatorNode.left = leftOperand;
                    operatorNode.right = rightOperand;
                    stack.push(operatorNode);
                }
            } catch (ClassCastException e) {
                System.err.println("Error: Invalid token or type mismatch.");
                e.printStackTrace();
            }
        }

        this.root = stack.pop();
    }

    /**
     * Checks if a token is an operand (numeric value).
     *
     * @param token The token to check.
     * @return True if the token is an operand, false otherwise.
     */
    private boolean isOperand(String token) {
        return token.matches("[0-9]+");
    }

    /**
     * Checks if a token is an operator (+, -, *, /).
     *
     * @param token The token to check.
     * @return True if the token is an operator, false otherwise.
     */
    private boolean isOperator(String token) {
        return token.matches("[+\\-*/]");
    }

    /**
     * Evaluates the expression and returns the result.
     *
     * @return The result of the evaluated expression.
     */
    public T evaluate() {
        return evaluateNode(root);
    }

    private T evaluateNode(TreeNode<T> node) {
        if (isOperand(node.data.toString())) {
            return node.data;
        } else {
            T leftValue = evaluateNode(node.left);
            T rightValue = evaluateNode(node.right);
            return performOperation(leftValue, rightValue, node.data.toString());
        }
    }

    private T performOperation(T leftValue, T rightValue, String operator) {
        int left = Integer.parseInt(leftValue.toString());
        int right = Integer.parseInt(rightValue.toString());

        switch (operator) {
            case "+":
                return (T) (Object) (Integer) (left + right);
            case "-":
                return (T) (Object) (Integer) (left - right);
            case "*":
                return (T) (Object) (Integer) (left * right);
            case "/":
                if (right == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return (T) (Object) (Integer) (left / right);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
