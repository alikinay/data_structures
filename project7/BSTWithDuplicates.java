package hw7;

// Collaborated with Riley Hanson, Leon Yu, Maria Castellaneta, Arvene Golbazi

import java.util.LinkedList;
import java.util.Random;

/**
 * Binary search tree, keyed by integers with String data, which allows for insertion of the same
 * key twice (or more), and will create a new node for it each time. The new node may be either to
 * the right or to the left of a higher node with the same key; the direction for a duplicate key is
 * chosen at random, else duplicate keys could create very long chains and hamper performance (or
 * even cause stack overflow errors on recursion). Has a special search, which returns a LinkedList
 * of data for all the keys within range.
 * 
 * @author reyzin
 */

public class BSTWithDuplicates {
  private class TreeNode {
    int key;
    String data;
    TreeNode left, right;

    TreeNode(int k, String d) {
      key = k;
      data = d;
      left = right = null;
    }
  }

  private TreeNode root = null;
  Random random = new Random();

  /**
   * Adds (key, data) pair to the tree, even if the same key already exists.
   * 
   * @param key
   * @param data
   */
  public void insert(int key, String data) {
    root = insert(key, data, root);
  }


  private TreeNode insert(int key, String data, TreeNode subtree) {
    // TODO: implement
    // Use random.nextBoolean to decide whether to go right or left
    // when key is equal to subtree.key

    if (subtree == null) { // subtree is initially the root
      subtree = new TreeNode(key, data);
    }

    else if (key < subtree.key) {
      subtree.left = insert(key, data, subtree.left);
    } else if (key > subtree.key) {
      subtree.right = insert(key, data, subtree.right);
    } else { // result ==0 the case for dublicates
      // subtree.left=insert(key, subtree.right);
      if (random.nextBoolean()) {
        subtree.left = insert(key, data, subtree.left);
      } else {
        subtree.right = insert(key, data, subtree.right);
      }
    }
    return subtree;

  }

  /**
   * Assumes low<=high
   * 
   * @param low
   * @param high
   * @return a collection of all data elements whose keys are between low and high (inclusive)
   */
  public LinkedList<String> rangeSearch(int low, int high) {
    LinkedList<String> ret = new LinkedList<String>();
    rangeSearch(root, low, high, ret);
    return ret;
  }

  private void rangeSearch(TreeNode subtree, int low, int high, LinkedList<String> ret) {
    // TODO implement
    // suggestion: check if subtree key is less than low, greater than high,
    // or between the two; use this check to decide what to add
    // to ret and whether to recurse right or left or both

    if (subtree == null) {
      return;
    }

    if (subtree.key < low) {
      rangeSearch(subtree.right, low, high, ret);
    }
    if (subtree.key > high) {
      rangeSearch(subtree.left, low, high, ret);
    }
    if (subtree.key >= low && subtree.key <= high) {
      ret.add(subtree.data);
      rangeSearch(subtree.left, low, high, ret);
      rangeSearch(subtree.right, low, high, ret);
    }
  }


  /**
   * @return the largest key in the tree; 0 if the key is empty
   */
  public int maxKey() {
    // TODO implement
    TreeNode r = root;

    if (r != null) {
      while (r.right != null) {
        r = r.right;
      }
      return r.key;
    }
    return 0;
  }

}
