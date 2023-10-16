package DataStructures.Hierarchical;

/**
 * Represents a node in a hierarchical structure, such as a binary tree.
 *
 * @param <T> The type of data held by the node.
 * 
 * @author José Barquero
 */
class TreeNode<T> {
    /**
     * The data stored in the node.
     */
    T data;

    /**
     * Reference to the left child node.
     */
    TreeNode<T> left;

    /**
     * Reference to the right child node.
     */
    TreeNode<T> right;

    /**
     * Constructs a node with the given data and initializes left and right child references to null.
     *
     * @param data The data to be stored in the node.
     */
    public TreeNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
