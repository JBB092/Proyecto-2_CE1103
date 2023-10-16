package DataStructures.Hierarchical;

import DataStructures.NoHierarchical.*;
/**
 * Represents an expression tree that can evaluate postfix expressions.
 *
 * @param <T> The type of data to be stored in the tree.
 *
 * @author José Barquero
 */
public class ExpressionTree<T> {
    private TreeNode<T> root;

    public ExpressionTree() {
        this.root = null;
    }

    /**
     * Builds the expression tree from an infix expression.
     *
     * @param infixExpression The infix expression as a string.
     */
    public void buildTreeFromInfix(String infixExpression) {
        String[] postfixExpression = infixToPostfix(infixExpression);
        buildTree(postfixExpression);
    }

    /**
     * Gets the precedence of an operator.
     *
     * @param operator The operator.
     * @return The precedence of the operator.
     */
    private int getPrecedence(Character operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }


    /**
     * Converts an infix expression to postfix notation.
     *
     * @param infixExpression The infix expression as a string.
     * @return The postfix expression as an array of tokens.
     */
    private String[] infixToPostfix(String infixExpression) {
        DoublyLinkedList<Character> operators = new DoublyLinkedList<>();
        StringBuilder output = new StringBuilder();
        
        for (char ch : infixExpression.toCharArray()) {
            if (Character.isDigit(ch)) {
                output.append(ch);
            } else if (ch == '(') {
                operators.insertAtEnd(ch);
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.getLast() != '(') {
                    output.append(operators.removeLast());
                }
                operators.removeLast(); // Discard '('
            } else if (isOperator(String.valueOf(ch))) {
                while (!operators.isEmpty() && getPrecedence(operators.getLast()) >= getPrecedence(ch)) {
                    output.append(operators.removeLast());
                }
                operators.insertAtEnd(ch);
                output.append(' ');  // Add a space to separate operators
            }
        }
        
        while (!operators.isEmpty()) {
            output.append(operators.removeLast());
        }
        
        // Split the postfix expression into an array of tokens
        String[] postfixExpression = output.toString().split(" ");
        return postfixExpression;
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

     /**
     * Recursively evaluates the given node and its children in the expression tree.
     *
     * @param node The node to start evaluation from.
     * @return The result of the evaluated expression.
     */
    private T evaluateNode(TreeNode<T> node) {
        if (isOperand(node.data.toString())) {
            return node.data;
        } else {
            T leftValue = evaluateNode(node.left);
            T rightValue = evaluateNode(node.right);
            return performOperation(leftValue, rightValue, node.data.toString());
        }
    }

    /**
     * Performs the specified arithmetic operation on the operands.
     *
     * @param leftValue  The left operand.
     * @param rightValue The right operand.
     * @param operator   The arithmetic operator (+, -, *, /).
     * @return The result of the arithmetic operation.
     */
    private T performOperation(T leftValue, T rightValue, String operator) {
        int left = Integer.parseInt(leftValue.toString());
        int right = Integer.parseInt(rightValue.toString());
    
        switch (operator) {
            case "+":
                return (T) (Object) (left + right);
            case "-":
                return (T) (Object) (left - right);
            case "*":
                return (T) (Object) (left * right);
            case "/":
                if (right == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return (T) (Object) (left / right);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
    
    public static void main(String[] args) {
        String infixExpression = "3 * 5 + 1 * 10";
        ExpressionTree<Integer> expressionTree = new ExpressionTree<>();
        expressionTree.buildTreeFromInfix(infixExpression);
        int result = expressionTree.evaluate();
        System.out.println("Resultado de la expresión: " + result); 
    }
}