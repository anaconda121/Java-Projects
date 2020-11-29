package data_sturcs.binary_tree;

public class BinarySearchTree <T extends Comparable <T>> { //need only elements that are comparable
    private int nodeCount = 0;
    private Node root = null;

    private class Node {
        T data;
        Node left, right;
        
        public Node (Node left, Node right, T elem) {
            this.left = left;
            this.right = right;
            this.data = elem;
        }
    }

    //returns size of binaryTree
    public int getSize() {
        return nodeCount;
    }

    //checks if binarytree is empty
    public boolean isEmpty() {
        return getSize() == 0;
    }

    //checks if the element is in the binary tree - ACTUAL LOGIC
    private boolean contains(Node node, T elem) {
        //element does not exist in tree
        if (node.data == null) {
            return false;
        } 
        int compareScore = elem.compareTo(node.data);
        if (compareScore == 0) {
            //found element
            return true;
        } else if (compareScore > 0) {
            //go to right subtree
            node = node.right;
            return contains(node, elem);
        } else if (compareScore < 0) {
            //go to left subtree
            node = node.left;
            return contains(node, elem);
        }
        return true;
    }

    public boolean contains(T elem) {
        //start traversal at the root node
        return contains(root, elem);
    }

    //adds element to binary tree, returns true if element is sucessfully added
    public boolean add(T elem) {
        //can't have any duplicate elements
        if (contains(elem)) {
            return false;
        } else {
            //add element to binary tree
            root = add(root, elem);
            System.out.println("Root after adding element: " + root);
            nodeCount++;
            return true;
        }
    }

    //adds element to binary tree - ACTUAL LOGIC
    private Node add(Node node, T elem) {
        //if elem ends up being a leaf node
        if (node == null) {
            node = new Node(null, null, elem);
        } else {
            if (elem.compareTo(node.data) < 0) {
                //go to left subtree
                node.left = add(node.left, elem);
            } else {
                //go to right subtree
                node.right = add(node.right, elem);
            }
        }
        return node;
    }
    
}
