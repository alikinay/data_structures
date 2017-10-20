package hw6;
//Collaborated with Maria Castellaneta 

public class LoopyList {
  public class Node {
    int value;
    Node next = null;

    Node(int value) {
      this.value = value;
    }
  }

  public Node head = null;

  /*
   * If loopLength <=0, there will be no loop, just head of length prefix
   */
  public LoopyList(int prefixLength, int loopLength) {
    // Build a prefix one longer than requested and then
    // delete the first node -- makes life easier,
    // because you don't have to handle prefixLength = 0
    // as a special case

    // DO NOT MODIFY THIS METHOD
    head = new Node(0);
    Node last = head;
    for (int i = 1; i <= prefixLength; i++) {
      last.next = new Node(i);
      last = last.next;
    }
    if (loopLength > 0) {
      Node lastPrefixNode = last;
      for (int i = 1; i <= loopLength; i++) {
        last.next = new Node(prefixLength + i);
        last = last.next;
      }
      last.next = lastPrefixNode.next;
    }
    head = head.next;
  }

  /**
   * @return whether the list terminates with a null or points back to itself
   */
  public boolean isLoopy() {
    // TODO implement -- see HW for hints

    if (head == null) return false;
    if (head.next == null) return false;

    Node slowp = head.next;
    Node fastp = head.next.next;

    while (slowp != fastp) {
      if (fastp == null) return false;
      fastp = fastp.next;
      if (fastp == null) return false;
      fastp = fastp.next;
      slowp = slowp.next;
    }
    return true;
  }


  /**
   * changes a loopy list to a straight one; does not modify if not loopy
   * 
   * @return whether the list was loopy and thus modified
   */
  public boolean straighten() {
    // TODO implement -- see HW for hints

    if (head == null) return false;
    if (head.next == null) return false;

    Node slowp = head.next; // moves one step at a time.
    Node fastp = head.next.next; // moves two steps at a time
    Node thirdp = head; // moves one step at a time.

    while (slowp != fastp) {
      if (fastp == null) return false;
      fastp = fastp.next; // just moves the fast pointer.

      if (fastp == null) return false; // inside paranthesis is another fastp beacuse we don't immediately  want to move fast two steps.
      fastp = fastp.next;
      slowp = slowp.next; // just moves the slow pointer.
    }


    if (thirdp == head && slowp == head) { // special case when p is zero

      while (thirdp.next != head) {
        thirdp = thirdp.next;
      }
      thirdp.next = null; // straightens the list
      return true;
    }

    while (slowp.next != thirdp.next) {
      thirdp = thirdp.next;
      slowp = slowp.next;
    }
    slowp.next = null; // straightens the list
    return true;
  }


  public String toString() {
    // DO NOT MODIFY THIS METHOD
    if (head == null) return "[]";
    String s = "[";
    Node p;
    for (p = head; p.next != null; p = p.next) {
      s += p.value + ", ";
      if (s.length() > 1000) {
        return "String is getting too long; perhaps your list is loopy";
      }
    }
    s += p.value + "]"; // last one is special, because it has no comma after
    return s;
  }

}
