package DataStructures.Hierarchical;

/**
 * Represents a node in a Binary Expression Tree.
 *
 * Each node contains data and references to its left and right children nodes.
 *
 * @author José Barquero
 */
public class BinaryTreeNode {
    private String data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    /**
     * Constructs a Binary Tree Node with the given data.
     *
     * @param data The data associated with this node.
     */
    public BinaryTreeNode(String data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    /**
     * Gets the data associated with this node.
     *
     * @return The data of this node.
     */
    public String getData() {
        return data;
    }

    /**
     * Gets the left child of this node.
     *
     * @return The left child node.
     */
    public BinaryTreeNode getLeft() {
        return left;
    }

    /**
     * Gets the right child of this node.
     *
     * @return The right child node.
     */
    public BinaryTreeNode getRight() {
        return right;
    }

    /**
     * Sets the left child of this node.
     *
     * @param left The left child node to set.
     */
    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    /**
     * Sets the right child of this node.
     *
     * @param right The right child node to set.
     */
    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }
}
