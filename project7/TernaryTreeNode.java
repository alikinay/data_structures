package hw7;

//Collaborated with Riley Hanson, Leon Yu, Maria Castellaneta, Arvene Golbazi
public class TernaryTreeNode {

  TernaryTreeNode left;
  TernaryTreeNode right;
  TernaryTreeNode center;
  char splitChar;

  public TernaryTreeNode(char c) {
    left = null;
    center = null;
    right = null;

    splitChar = c;
  }

  public void add(String s) {
    // TODO: implement
    // We suggest you do it recursively, by adding a
    // recursive helper method to TernaryTreeNode;
    // but you may do it iteratively if you prefer


    if (s.equals("")) { // base case
      return;
    }

    if (s.charAt(0) < splitChar) {
      if (left == null) {
        left = new TernaryTreeNode(s.charAt(0));
      }
      left.add(s);

    }

    else if (s.charAt(0) > splitChar) {
      if (right == null) {
        right = new TernaryTreeNode(s.charAt(0));
      }
      right.add(s);
    }

    else { // character equals the splitChar
      if (s.length() > 1) {
        if (center == null) {
          center = new TernaryTreeNode(s.charAt(1));
        }
        center.add(s.substring(1));
      }
    }
  }

  public boolean contains(String s, TernaryTreeNode root) {

    if (s.equals("") && root == null) { // base case
      return true;
    }
    if (s.length() == 0 && root != null || s.length() > 0 && root == null) {
      return false;
    }

    if (s.charAt(0) == root.splitChar) { // the word is the same as the root
      if (root.center != null) {
        return root.center.contains(s.substring(1), root.center);
      }

      if (s.length() == 1) {
        return true;
      }
      return false;

    }

    if (s.charAt(0) < root.splitChar) { // if the word is smaller than the root
      if (root.left != null) {
        return root.left.contains(s, root.left);

      }
      return false;
    }

    else { // (s.charAt(0) > splitChar) if the word is greater than the root

      if (root.right != null) {
        return root.right.contains(s, root.right);
      }
      return false;
    }

  }

}


