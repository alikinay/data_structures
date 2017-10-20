package hw5;
// Collaborated with Leon Yu, Maria Castellaneta, Arvene Golbazi, Riley Hanson.

// import hw5.OrderedList.Node;

/**
 * Polynomial Class which implements the CalculatorOperand interface. Maintains polynomials as an
 * ordered linked list, with monomials arranged by decreasing degree
 */

public class Polynomial implements CalculatorOperand<Polynomial> {

  private class PolyNode {
    int coeff;
    int degree;
    PolyNode next;

    // TODO: add a constructor

    PolyNode(int coeff, int degree, PolyNode next) {
      this.coeff = coeff;
      this.degree = degree;
      this.next = next;
    }
  }

  private PolyNode monomialsList = new PolyNode(0, -1, null); // TODO: initialize in the constructor
  // //head


  Polynomial(int coeff, int degree) {
    // TODO: IMPLEMENT

    if (coeff != 0 && degree >= 0) {
      monomialsList.next = new PolyNode(coeff, degree, null);
    }
  }

  /**
   * Returns this + coeff*x^degree * that; does not modify this or that. Assumes coeff is nonzero.
   */

  // NOTE: normally, this would be private, but leave it public so we can test it
  public Polynomial addTimesMonomial(Polynomial that, int coeff, int degree) {
    // return null; // TODO: IMPLEMENT; READ THE ASSIGNMENT AND IMPLEMENT add FIRST

    Polynomial list = new Polynomial(0, 0);

    PolyNode thispointer = this.monomialsList.next;
    PolyNode thatpointer = that.monomialsList.next;
    PolyNode listpointer = list.monomialsList; // creates and connect the nodes into the list.

    Polynomial copiedlist = new Polynomial(0, 0);
    PolyNode copiedlistpointer = copiedlist.monomialsList;


    while (thatpointer != null) {
      copiedlistpointer.next =
          new PolyNode(thatpointer.coeff * coeff, thatpointer.degree + degree, null);
      thatpointer = thatpointer.next;
      copiedlistpointer = copiedlistpointer.next;
    }

    copiedlistpointer = copiedlist.monomialsList.next; // starts from the head;

    while (thispointer != null && copiedlistpointer != null) {

      int coefficient = thispointer.coeff + copiedlistpointer.coeff;

      if (thispointer.degree == copiedlistpointer.degree) {

        if (coefficient == 0) {
          thispointer = thispointer.next;
          copiedlistpointer = copiedlistpointer.next;

        } else if (coefficient != 0) {
          listpointer.next = new PolyNode(coefficient, thispointer.degree, null); // actual addition
          listpointer = listpointer.next;
          thispointer = thispointer.next;
          copiedlistpointer = copiedlistpointer.next;
        }

      }
 
      else if (thispointer.degree > copiedlistpointer.degree) {
        listpointer.next = new PolyNode(thispointer.coeff, thispointer.degree, null);
        listpointer = listpointer.next;
        thispointer = thispointer.next;
      }

      else { // (degree2> degree1)
        listpointer.next = new PolyNode(copiedlistpointer.coeff, copiedlistpointer.degree, null);
        listpointer = listpointer.next;
        copiedlistpointer = copiedlistpointer.next;
      }
    }

    while (thispointer != null) {
      listpointer.next = new PolyNode(thispointer.coeff, thispointer.degree, null);
      listpointer = listpointer.next;
      thispointer = thispointer.next;
    }

    while (copiedlistpointer != null) {
      listpointer.next = new PolyNode(copiedlistpointer.coeff, copiedlistpointer.degree, null);
      listpointer = listpointer.next; // NOTE: recently added.
      copiedlistpointer = copiedlistpointer.next;
    }

    return list;
  }

  /**
   * Returns this+that; does not modify this or that
   */
  public Polynomial add(Polynomial that) {
    // return null;// TODO: IMPLEMENT
    return addTimesMonomial(that, 1, 0);
  }


  /**
   * Returns this-that; does not modify this or that
   */
  public Polynomial subtract(Polynomial that) {
    // return null; // TODO: IMPLEMENT
    return this.addTimesMonomial(that, -1, 0);
  }

  /**
   * Returns this*that; does not modify this or that
   */
  public Polynomial multiply(Polynomial that) {

    Polynomial list = new Polynomial(0, 0);
    PolyNode thatpointer = that.monomialsList.next;

    while (thatpointer != null) {
      list = list.addTimesMonomial(this, thatpointer.coeff, thatpointer.degree);

      thatpointer = thatpointer.next;
    }
    return list;
  }


  /**
   * Prints the polynomial the way a human would like to read it
   * 
   * @return the human-readable string representation
   */
  public String toString() {
    if (monomialsList.next == null) return "0";

    String ret = monomialsList.next.coeff < 0 ? "-" : "";
    for (PolyNode p = monomialsList.next; p != null; p = p.next) {
      if (p.degree == 0 || (p.coeff != 1 && p.coeff != -1)) ret = ret + java.lang.Math.abs(p.coeff);
      if (p.degree > 0) ret = ret + "x";
      if (p.degree > 1) ret = ret + "^" + p.degree;
      if (p.next != null) ret = ret + (p.next.coeff < 0 ? " - " : " + ");
    }
    return ret;
  }
}

