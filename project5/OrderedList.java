package hw5;

public class OrderedList<T extends Comparable<T>> {
  private class Node {
    T value;
    Node next;

    Node(T v, Node n) {
      value = v;
      next = n;
    }
  }

  // A dummy head node -- having it simplifies a lot of code
  private Node head = new Node(null, null);

  /*
   * Inserts v into the list, maintaining elements in increasing order
   */
  public void insert(T v) {
    // TODO: Implement

    Node previous = head; // points to the first dummy Node.
    Node current = head.next;
    Node insertnode = new Node(v, null); // this is the node that is going to be inserted.

    while (current != null) {

      int nodeplace = v.compareTo(current.value);

      if (nodeplace > 0) {
        previous = current;
        current = current.next;
      } else if (nodeplace <= 0) { // handles two cases, one if nodeplace <0 and one if nodeplace=0
        break; // exits the loop and inserts the node.
      }

    }
    previous.next = insertnode;
    insertnode.next = current;
  }

  /*
   * Removes v; if there is more than one instance of v, removes only one. If v was found, returns
   * true. Else, does nothing and returns false.
   */
  public boolean delete(T v) {
    Node prev = head, cur = head.next;
    while (cur != null) {
      int compResult = cur.value.compareTo(v);
      if (compResult == 0) { // we found the value to delete
        prev.next = cur.next;
        return true;
      }
      if (compResult > 0) { // we went past the value to delete and it's not in the list
        return false;
      }
      prev = cur;
      cur = cur.next;
    }
    return false; // the value to delete is greater than everything in the list
  }

  /*
   * Returns true if the list contains v
   */

  public boolean contains(T v) {
    // TODO: Implement

    Node p = head;

    while (p != null) {

      if (p.value == v) return true; // value is the value that is inside the node.
      p = p.next;

    }
    return false;
  }

  /*
   * Returns the result of merging this and that; runs in time O(|this|+|that|). Does not modify
   * this or that.
   */
  OrderedList<T> merge(OrderedList<T> that) {
    // TODO: Implement

    OrderedList<T> list = new OrderedList<T>(); // this is the new list that will contain ordered
                                                // nodes.

    Node thispointer = this.head.next;
    Node thatpointer = that.head.next;

    Node insertnode = list.head; // list.head is pointing to the dummy node.

    while (thispointer != null && thatpointer != null) {


      int nodeplace = thispointer.value.compareTo(thatpointer.value);

      if (nodeplace > 0) {
        Node node1 = new Node(thatpointer.value, null);
        insertnode.next = node1;
        thatpointer = thatpointer.next;
        insertnode = insertnode.next;

      } else if (nodeplace <= 0) {
        Node node2 = new Node(thispointer.value, null);
        insertnode.next = node2;
        thispointer = thispointer.next;
        insertnode = insertnode.next;
      }

    }

    while (thispointer != null) {

      Node node = new Node(thispointer.value, null);
      insertnode.next = node;
      thispointer = thispointer.next;
      insertnode = insertnode.next;
    }

    while (thatpointer != null) {

      Node node = new Node(thatpointer.value, null);
      insertnode.next = node;
      thatpointer = thatpointer.next;
      insertnode = insertnode.next;
    }
    return list;

  }

  /*
   * Same output format as java.util.Arrays.toString
   */
  public String toString() {
    if (head.next == null) return "[]";
    String s = "[";
    Node p;
    for (p = head.next; p.next != null; p = p.next) {
      s += p.value + ", ";
    }
    s += p.value + "]"; // last one is special, because it has no comma after
    return s;
  }

}
