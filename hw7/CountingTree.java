
package hw7;

// Collaborated with Riley Hanson, Leon Yu, Maria Castellaneta, Arvene Golbazi

/**
 * Binary search tree, containing items of type String, which allows duplicates by having a counter
 * in each node (rather than inserting duplicate nodes)
 *
 */
public class CountingTree {

  private class TreeNode {
    String key;
    int count; // how many times key appears in the tree
    TreeNode left, right;

    TreeNode(String key) {
      this.key = key;
      left = right = null;
      count = 1;
    }
  }

  private TreeNode root = null;
  private int totalEntries = 0; // total of all counts
  private int distinctEntries = 0; // number of nodes

  /**
   * @return number of total entries (i.e., total of all counts)
   */
  public int getTotalEntries() {
    return totalEntries;
  }

  /**
   * @return number of distinct entries (i.e., number of nodes)
   */
  public int getDistinctEntries() {
    return distinctEntries;
  }

  /**
   * Inserts key into the tree. If key is already in, just increments the corresponding counter. If
   * not, creates a new node.
   * 
   * @param key the value to be inserted
   */
  public void insert(String key) {
    // TODO implement; don't forget to tend to "totalEntries" and "distinctEntries"

    TreeNode r = root; // r functions as pointer

    if (root == null) {
      root = new TreeNode(key);
      totalEntries++;
      distinctEntries++;
    } else {

      while (true) {
        int result = key.compareTo(r.key);

        if (result == 0) { // we found the word
          r.count++;
          totalEntries++; // simply increment the counter
          return;
        } else if (result < 0) {
          if (r.left == null) {
            r.left = new TreeNode(key);
            distinctEntries++;
            totalEntries++;
            return;

          }
          r = r.left;

        } else {
          if (r.right == null) {
            r.right = new TreeNode(key);
            distinctEntries++;
            totalEntries++;
            return;
          }
          r = r.right;
        }
      }
    }
  }


  /**
   * @param key
   * @return number of times key is in the tree, i.e., the count of key (0 if key not in the tree)
   */
  public int search(String key) {
    // TODO implement

    TreeNode current = root;

    while (current != null) {

      int result = key.compareTo(current.key);

      if (result == 0) { // found the word
        return current.count;
      } else if (result < 0) {
        current = current.left;
      } else { // result >0
        current = current.right;
      }

    }
    return 0;
  }


  /**
   * Creates and returns a new tree, which allows lookup of entries in this tree by their counts.
   * Thus, in the new tree keys are integers, and data is String. The new tree has as many nodes as
   * the current tree. The new tree must allow insertion of nodes with duplicate keys, since counts
   * may repeat.
   * 
   * @return the new tree
   */
  public BSTWithDuplicates frequencyTree() {
    BSTWithDuplicates t = new BSTWithDuplicates();
    frequencyTree(root, t);
    return t;
  }

  /**
   * Private recursive helper for frequencyTree
   * 
   * @param root
   * @param t
   */
  private void frequencyTree(TreeNode root, BSTWithDuplicates t) {
    if (root != null) {
      // just do a traversal -- any order is fine
      t.insert(root.count, root.key);
      frequencyTree(root.left, t);
      frequencyTree(root.right, t);
    }
  }

}
