package DataStructures.Hierarchical;

import DataStructures.NoHierarchical.CustomStack;
import DataStructures.NoHierarchical.CustomQueue;

/**
 * This class provides a utility for converting an infix mathematical expression to a postfix expression.
 * It can handle basic arithmetic operations and parentheses.
 *
 * @author José Barquero
 */
public class InfixToPostfix {

    /**
     * Initializes a new instance of the InfixToPostfix class.
     */
    public InfixToPostfix(){}

    /**
     * Converts an infix expression to a postfix expression.
     *
     * @param infix The infix mathematical expression to convert.
     * @return A CustomQueue containing the postfix expression.
     */
    public CustomQueue convertPQ (String infix){
        CustomQueue postfix = new CustomQueue();
        StringBuilder temp = new StringBuilder();
        CustomStack stack = new CustomStack();
        for (int i=0;i<infix.length();i++){
            Character C = infix.charAt(i);
            if (isOperand(C)){
                temp.append(C);
            }
            else{
                if (!temp.toString().isEmpty()){
                    postfix.enqueue(temp.toString());
                    temp = new StringBuilder();}
                if (C=='('){
                    stack.push(C);
                }else if (C==')'){
                    while(!stack.isEmpty() && (Character) stack.peek() != '('){
                        postfix.enqueue(stack.pop());
                    }
                    if ((Character) stack.peek() == '('){
                        stack.pop();
                    }
                    else {
                        System.out.println("ERROR: MISMATCHED PARENTHESES");
                    }
                    C=null;
                }
                else{
                    while(!stack.isEmpty() && isOperator((Character) stack.peek()) && (priorityInExpression((Character) stack.peek()) >= priorityInExpression(C))){
                        postfix.enqueue(stack.pop());
                    }
                    stack.push(C);
                }
            }
        }
        while (!stack.isEmpty()){
            postfix.enqueue(stack.pop());
        }
        stack.clear();
        return postfix;

    }

    /**
     * Checks if a character is an operator (+, -, *, /, %, ^, &, |, ?).
     *
     * @param c The character to check.
     * @return True if the character is an operator, false otherwise.
     */
    public boolean isOperator(Character c){
        if (c=='+' || c=='-' || c=='*' || c=='/' ||c=='%' || c=='^' || c=='&' || c=='|' || c=='?'){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Checks if a character is an operand (a number or a variable).
     *
     * @param c The character to check.
     * @return True if the character is an operand, false otherwise.
     */
    public boolean isOperand(Character c){
        if (c=='+' || c=='-' || c=='*' || c=='/' || c=='%' || c=='^' || c=='(' || c==')' || c=='&' || c=='|' || c=='?'){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Determines the priority of an operator in an expression.
     *
     * @param operator The operator to check.
     * @return An integer representing the operator's priority.
     */
    private int priorityInExpression (char operator)
    {
        if (operator == '^' || operator == '&')
        {
            return 4;
        }
        if (operator == '*' || operator == '/' || operator == '%' || operator == '?')
        {
            return 2;
        }
        if (operator == '+' || operator == '-' || operator == '|')
        {
            return 1;
        }
        if (operator == '(' )
        {
            return 5;
        }
        return 0;
    }
}