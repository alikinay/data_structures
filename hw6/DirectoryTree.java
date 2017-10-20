package hw6;
//Collaborated with Arvene Golbazi, Riley Hanson, Tuna Sogut

import java.util.*;

public class DirectoryTree {
  // TODO nothing here is implemented, these are just empty
  // shells so the compiler doesn't complain
  // Implement private members, public methods,
  // and private helper methods, etc., as needed,
  // in order to get the behavior described on the pset
  // You will want to maintain two private members:
  // the root and the current directory.

  private Node root = new Node("", null); // creates new node that is root.

  private class Node {
    String name;
    LinkedList<Node> children = new LinkedList<Node>();
    Node parent;

    Node(String value, Node parent) {
      this.name = value;
      this.parent = parent;

      
    }

  }

  Node current = root; // current initially points at current

  public boolean checker(String name) { // checks if the element is in the LinkedList already there
    
    for (Node node : current.children) { // goes through every element in the LinkedList
      if (node.name.equals(name)) {
        
        return true;
      }
    }
    return false;
  }

  public Node checkernode(String name) { // checks if the element is in the LinkedList already there
    Node ret = null;
    for (Node node : current.children) { // goes through every element in the LinkedList
      if (node.name.equals(name)) {
        ret = node;
        return ret;
      }
    }
    return null;
  }


  public boolean mkdir(String name) {
    if (checker(name) == false) {
      Node node = new Node(name, current); // creates new node (makes directory)
      current.children.add(node); // add the new node into the LinkedList
      return true;
    } else {
      return false;
    }
  }

  public boolean cd(String name) {
    for (Node node : current.children) {
      if (node.name.equals(name)) {
        current = node;
        return true;
      }
    }
    return false;
  }

  public boolean cdUp() {

    if (current.parent == null) { // then we know that its the root
      return false;
    } else {
      current = current.parent;
      return true;
    }
  }

  public boolean rmdir(String name) {

    if (checker(name)) {
      current.children.remove(checkernode(name));
      return true;
    } else {
      return false;
    }
  }

  public String ls() {
    String ret = "";
    for (Node node : current.children) {
      ret += node.name + '\n';
    }
    return ret;
  }

  public String printSubTree() {
    return printSubTree(current, 0);
  }

  private String printSubTree(Node n, int depth) {
    String ret = "";
    if (depth != 0) {
      for (int i = 0; i < depth; i++) {
        ret += "  ";
      }
    }
    ret += n.name + "\n";
    for (Node child : n.children) {
      ret += printSubTree(child, depth + 1);
    }
    return ret;
  }


  public String pwd() {
    Stack<Node> stack = new Stack<Node>(); // creates new stack of nodes
    String ret = "";
    Node currentpointer = current;

    if (currentpointer == root) { // then its the root
      return "/"; // initialize ret as /
    }

    while (currentpointer != root) {
      
      stack.push(currentpointer);
      currentpointer = currentpointer.parent; // same concept as current.next;

    }
    while (!stack.isEmpty()) {
      Node node = stack.pop();
      ret += "/" + node.name;
    }
    return ret;

  }

  public int numNodes() {
    return totalnum(current);
  }

  private int totalnum(Node a) {
    int ret = 1;

    for (Node child : a.children) {
      ret += totalnum(child);
    }
    return ret;
  }

}

