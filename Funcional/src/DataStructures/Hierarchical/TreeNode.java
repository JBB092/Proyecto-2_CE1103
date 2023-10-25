package DataStructures.Hierarchical;

/**
 * The {@code TreeNode} class represents a node in a hierarchical binary expression tree.
 * Each node contains a value (an operator or operand) and references to its left and right children.
 * This class is typically used to construct and manipulate binary expression trees.
 *
 * @author José Barquero
 */
public class TreeNode
{
    /**
     * The value stored in the node, which can be an operator or operand.
     */
    public String valor;

    /**
     * Reference to the left child node.
     */
    public TreeNode left;

    /**
     * Reference to the right child node.
     */
    public TreeNode right;

    /**
     * Constructs a new node with the specified value and no children.
     *
     * @param data The value to store in the node.
     */
    public TreeNode(String data)
    {
        this.valor = data;
        this.left = this.right = null;
    }

    /**
     * Constructs a new node with the specified value and references to its left and right children.
     *
     * @param data The value to store in the node.
     * @param left The left child node.
     * @param right The right child node.
     */
    public TreeNode(String data, TreeNode left, TreeNode right)
    {
        this.valor = data;
        this.left = left;
        this.right = right;
    }
}