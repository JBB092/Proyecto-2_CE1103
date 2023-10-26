package DataStructures.Hierarchical;

import java.util.Objects;

import DataStructures.NoHierarchical.CustomQueue;
import DataStructures.NoHierarchical.CustomStack;

/**
 * BinaryExpressionTree represents a binary expression tree for evaluating mathematical expressions.
 * It provides methods to construct the tree from a postfix expression and perform in-order traversal.
 *
 * @author José Barquero
 */
public class BinaryExpressionTree {

    /**
     * Constructs a BinaryExpressionTree.
     */
    public BinaryExpressionTree(){

    }

    /**
     * Checks if the given token is an operator (+, -, *, /, ^, %, &, |, ?).
     *
     * @param c The token to check.
     * @return True if the token is an operator; otherwise, false.
     */
    public boolean isOperator(String c) {
        return Objects.equals(c, "+") || Objects.equals(c, "-") || Objects.equals(c, "*") || Objects.equals(c, "/") || Objects.equals(c, "%") || Objects.equals(c, "^") || Objects.equals(c, "&") || Objects.equals(c, "|") || Objects.equals(c, "?");
    }

    /**
     * Constructs a binary expression tree from a postfix expression.
     *
     * @param postfix The postfix expression represented as a custom queue.
     * @return The root node of the constructed binary expression tree.
     */
    public TreeNode construct(CustomQueue postfix)
    {
        // Base case
        if (postfix == null) {
            return null;
        }

        // Create an empty stack to store tree node pointers
        CustomStack<TreeNode> s = new CustomStack<>();

        String c = "";
        // Traverse the postfix expression
        while (!postfix.getList().isEmpty())
        {
            // If the current token is an operator
            c += postfix.dequeue();
            if (isOperator(c))
            {
                // Pop two nodes 'x' and 'y' form the stack
                TreeNode x = s.pop();
                TreeNode y = s.pop();

                // Build a new binary tree whose root is the operator and whose
                // left and right children point to `y` and `x`, respectively
                TreeNode treeNode = new TreeNode(c, y, x);

                // Push the current node into the stack
                s.push(treeNode);
            }
            // If the current token is an operand, create a new binary tree node
            // whose root is the operand and push it onto the stac
            else {
                s.push(new TreeNode(c));
            }
            c="";
        }
        // A pointer to the root of the expression tree remains in the stack
        return s.peek();
    }
}