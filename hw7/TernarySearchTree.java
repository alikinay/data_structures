package hw7;

//Collaborated with Riley Hanson, Leon Yu, Maria Castellaneta, Arvene Golbazi

import java.util.*;

public class TernarySearchTree implements Iterable<String> {

  private TernaryTreeNode root;

  public TernarySearchTree() {
    this.root = null;
  }

  public static TernarySearchTree GetTestTree() {
    TernarySearchTree t = new TernarySearchTree();
    t.root = new TernaryTreeNode('c');
    t.root.left = new TernaryTreeNode('a');
    t.root.center = new TernaryTreeNode('u');
    t.root.right = new TernaryTreeNode('h');
    t.root.left.center = new TernaryTreeNode('t');
    t.root.center.center = new TernaryTreeNode('t');
    t.root.right.center = new TernaryTreeNode('e');
    t.root.right.right = new TernaryTreeNode('u');
    t.root.left.center.left = new TernaryTreeNode('s');
    t.root.center.center.left = new TernaryTreeNode('p');
    t.root.center.center.center = new TernaryTreeNode('e');
    t.root.right.right.left = new TernaryTreeNode('i');
    t.root.right.right.center = new TernaryTreeNode('s');
    return t;
  }

  public boolean contains(String s) {
    // TODO: implement
    // We suggest you do it recursively, by adding
    // recursive helper method to TernaryTreeNode;
    // but you may do it iteratively if you prefer

    if (root == null) {
      return false;
    }
    return root.contains(s, root);
  }

  public void add(String s) { // helper function is in TernaryTreeNode.java
    if (root == null) {
      root = new TernaryTreeNode(s.charAt(0));
    }
    root.add(s);
  }

  class TSTiterator implements Iterator<String> {

    Stack<TernaryTreeNode> nodeStack = new Stack<TernaryTreeNode>();
    Stack<String> prefixStack = new Stack<String>();
    String ret = "";

    TSTiterator() {
      // TODO implement
      // hint: initialize the stacks same way as in toString

      if (root != null) {
        nodeStack.push(root);
        prefixStack.push("");
      }
    }

    public String next() {

      if (!hasNext()) throw new NoSuchElementException(); // if hasNext is false, throws an
                                                          // exception
      // TODO implement below
      // hint: this is very similar to toString

      while (!nodeStack.isEmpty()) {
        TernaryTreeNode n = nodeStack.pop();
        String prefix = prefixStack.pop();
        if (n == null) // print the string -- it's complete
        {
          return prefix;
        } else {
          // Push the right child first, so its popped last,
          // to ensure alphabetical order
          if (n.right != null) {
            nodeStack.push(n.right);
            prefixStack.push(prefix);
          }

          // Push the center child, regardless of whether it's null
          // (if it's not null, it needs to be traversed, else it needs
          // to be printed when it's popped off)
          nodeStack.push(n.center);
          prefixStack.push(prefix + n.splitChar);

          // Push the left child last, to ensure alphabetical order
          if (n.left != null) {
            nodeStack.push(n.left);
            prefixStack.push(prefix);
          }
        }
      } // if nodestack is empty it returns null
      return null;
    }


    public boolean hasNext() {
      // TODO implement

      if (nodeStack.isEmpty()) {
        return false;
      }
      return true;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

  }

  /*
   * Do not modify this method
   * 
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<String> iterator() {
    return new TSTiterator();
  }

  public String toString() {

    // Perform a depth-first search (DFS)
    // with two stacks: one for the nodes (as in the usual DFS)
    // and another, parallel one, for their string prefixes
    //
    // A null on the Node stack means that the corresponding
    // string on the string stack needs to be output

    Stack<TernaryTreeNode> nodeStack = new Stack<TernaryTreeNode>();
    Stack<String> prefixStack = new Stack<String>();
    String ret = "";

    if (root != null) {
      nodeStack.push(root);
      prefixStack.push("");
      do {
        TernaryTreeNode n = nodeStack.pop();
        String prefix = prefixStack.pop();
        if (n == null) // print the string -- it's complete
        {
          ret += prefix + "\n";
        } else {
          // Push the right child first, so its popped last,
          // to ensure alphabetical order
          if (n.right != null) {
            nodeStack.push(n.right);
            prefixStack.push(prefix);
          }

          // Push the center child, regardless of whether it's null
          // (if it's not null, it needs to be traversed, else it needs
          // to be printed when it's popped off)
          nodeStack.push(n.center);
          prefixStack.push(prefix + n.splitChar);

          // Push the left child last, to ensure alphabetical order
          if (n.left != null) {
            nodeStack.push(n.left);
            prefixStack.push(prefix);
          }
        }
      } while (!nodeStack.isEmpty()); // if nodestack is empty it returns ret
    }
    return ret;
  }
}
