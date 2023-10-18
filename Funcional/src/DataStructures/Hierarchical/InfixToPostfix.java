package DataStructures.Hierarchical;

import DataStructures.NoHierarchical.Stack;

/**
 * This class provides a method to convert an infix expression to a postfix expression.
 * It also includes utility methods for checking if a character is an operator and getting operator precedence.
 * The main method demonstrates the conversion of an infix expression to postfix.
 *
 * Author: José Barquero
 */
public class InfixToPostfix {

    /**
     * Checks if a character is an operator.
     *
     * @param c The character to check.
     * @return True if the character is an operator, false otherwise.
     */
    static boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '/' || c == '*' || c == '^');
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
            case '^':
                return 3;
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
    static String infixToPostfix(String infix) {
        Stack<Character> operatorStack = new Stack<>();
        StringBuilder postfixBuilder = new StringBuilder();

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            if (c == ' ') {
                continue;
            }

            if (!isOperator(c)) {
                postfixBuilder.append(c);
            } else {
                while (!operatorStack.isEmpty() && getPrecedence(operatorStack.peek()) >= getPrecedence(c)) {
                    postfixBuilder.append(operatorStack.pop());
                }
                operatorStack.push(c);
            }
        }

        while (!operatorStack.isEmpty()) {
            postfixBuilder.append(operatorStack.pop());
        }

        return postfixBuilder.toString();
    }

    public static void main(String[] args) {
        String infix = "55+10*180/9";
        String postfix = infixToPostfix(infix);
        System.out.println("Infix: " + infix);
        System.out.println("Postfix: " + postfix);
    }
}
