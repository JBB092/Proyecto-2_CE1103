package DataStructures.Hierarchical;

import DataStructures.NoHierarchical.Stack;

/**
 * Represents a Binary Expression Tree that can be used to evaluate expressions in postfix notation.
 * This program combines infix to postfix conversion, binary expression tree creation, and expression evaluation.
 *
 * This class provides methods to convert an infix expression to postfix, build a binary expression tree from postfix,
 * and evaluate the expression using the binary expression tree.
 *
 * @author José Barquero
 */
public class InfixToPostfixAndEval {

    /**
     * Checks if a character is an operator.
     *
     * @param c The character to check.
     * @return True if the character is an operator, false otherwise.
     */
    static boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '/' || c == '*' || c == '%' || c == '^');
    }

    /**
     * Gets the precedence of an operator.
     *
     * @param op The operator character.
     * @return The precedence value, or -1 if the operator is not recognized.
     */
    static int getPrecedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '%':
                return 3;
            case '^':
                return 4;
            default:
                return -1;
        }
    }

    /**
     * Converts an infix expression to a postfix expression.
     *
     * @param infix The input infix expression.
     * @return The postfix expression.
     */
    public static String infixToPostfix(String infix) {
        Stack<Character> operatorStack = new Stack<>();
        StringBuilder postfixBuilder = new StringBuilder();

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            if (c == ' ') {
                continue;
            }

            if (c == '*' && i < infix.length() - 1 && infix.charAt(i + 1) == '*') {
                // Replace "**" with "^" and continue
                postfixBuilder.append('^');
                i++; // Skip the next '*'
            } else {
                if (!isOperator(c)) {
                    postfixBuilder.append(c);
                } else {
                    while (!operatorStack.isEmpty() && getPrecedence(operatorStack.peek()) >= getPrecedence(c)) {
                        postfixBuilder.append(operatorStack.pop());
                    }
                    operatorStack.push(c);
                }
            }
        }

        while (!operatorStack.isEmpty()) {
            postfixBuilder.append(operatorStack.pop());
        }

        return postfixBuilder.toString();
    }

    /**
     * Main method to demonstrate infix to postfix conversion, binary expression tree construction, and expression evaluation.
     *
     * @param args Command line arguments (not used in this program).
     */
    public static void main(String[] args) {
        String infix = "9**2";
        System.out.println("Infix: " + infix);
        infix = infix.replace("**", "^");
        String postfix = infixToPostfix(infix);
        System.out.println("Postfix: " + postfix);

        BinaryExpressionTree expressionTree = new BinaryExpressionTree();
        expressionTree.buildTreeFromPostfix(postfix);

        double result = expressionTree.evaluate();
        System.out.println("Result: " + result);
    }
}
