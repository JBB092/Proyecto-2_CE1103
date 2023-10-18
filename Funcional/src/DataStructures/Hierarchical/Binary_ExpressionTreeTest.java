package DataStructures.Hierarchical;

public class Binary_ExpressionTreeTest {
        public static void main(String[] args) {
            String postfixExpression = "53+";  // Cambiar a la expresión que se quiera evaluar
            BinaryExpressionTree expressionTree = new BinaryExpressionTree();
    
            // Construir el árbol a partir de la expresión posfija
            expressionTree.buildTreeFromPostfix(postfixExpression);
    
            // Evaluar la expresión y mostrar el resultado
            int result = expressionTree.evaluate();
            System.out.println("Resultado de la expresión " + postfixExpression + " es: " + result);
        }
    }