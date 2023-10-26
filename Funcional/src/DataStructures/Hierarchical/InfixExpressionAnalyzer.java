package DataStructures.Hierarchical;

/**
 * InfixExpressionAnalyzer is a utility class for analyzing infix expressions to determine their nature
 * as either arithmetic or logical. It identifies if the given expression contains a mix of both
 * arithmetic and logical operators.
 *
 * @author José Barquero
 */
public class InfixExpressionAnalyzer {

    /**
     * Initializes a new instance of the InfixExpressionAnalyzer class.
     */
    public InfixExpressionAnalyzer(){
    }

    /**
     * Analyzes the given infix expression to determine its nature as either arithmetic or logical.
     *
     * @param infixExpression The input infix expression to analyze.
     * @return "f" if the expression is fully arithmetic, "v" if it's fully logical,
     *         "Mixta" if it contains a mix of arithmetic and logical operators.
     */
    public String analyzeInfixExpression(String infixExpression) {
        String arithmetic = "f";
        String logic = "f";
        String mix ="";

        // Operadores aritméticos y lógicos
        String arithmeticOperators = "+-*/%";
        String logicalOperators = "&|^~";

        for (char c : infixExpression.toCharArray()) {
            if (arithmeticOperators.indexOf(c) != -1) {
                if(logic.equals("f")){
                    arithmetic = "v";
                } else {
                    System.out.println("Error");
                    mix="Mixta";
                    break;
                }
            } else if (logicalOperators.indexOf(c) != -1) {
                if(arithmetic.equals("f")){
                    logic="v";
                } else {
                    System.out.println("Error");
                    mix="Mixta";
                    break;
                }
            }
        }
        if (mix.equals("Mixta")){
            return mix;
        } else if (arithmetic.equals("v")) {
            return arithmetic;
        } else {
            return arithmetic;
        }
    }
}