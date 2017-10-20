package hw7;

//Collaborated with Riley Hanson, Leon Yu, Maria Castellaneta, Arvene Golbazi
public class TernarySearchTreeDriver {

  public static void aFewTests() {

    String[] stringList = {"cute", "he", "cup", "at", "as", "us", "i"};
    TernarySearchTree tst = new TernarySearchTree();
    for (String s : stringList) {
      tst.add(s);
      System.out.println(tst + "-------");
    }


    for (String s : stringList) {
      if (!tst.contains(s)) {
        System.out.println("Doesn't contain what it should");
      }
      if (tst.contains(s + "a") || tst.contains(s + "z") || tst.contains(s.substring(1))
          || tst.contains(s.substring(0, s.length() - 1))) {
        // System.out.println(s);
        System.out.println("Contains what it should not");
      }
    }


    System.out.println("Iterator Test 1");
    for (String s : tst) {
      /*
       * This must print out all the strings in the ternary search tree (tst) in alphabetical order.
       */
      System.out.println(s);
    }

    System.out.println("Iterator Test2");
    for (String s : TernarySearchTree.GetTestTree()) {
      /*
       * This must print out all the strings in the ternary search tree (tst) in alphabetical order.
       */
      System.out.println(s);
    }

  }

  public static void main(String[] args) {

    // You may want to run/modify/play with aFewTests() to test your code

    // The line below is for testing in lab
    // System.out.println(TernarySearchTree.GetTestTree());

    // TernarySearchTree test= TernarySearchTree.GetTestTree();
    aFewTests();



    // System.out.println(test);
    // test.add("ali");
    // System.out.println(test);
    // System.out.println(test.contains("Tyrone"));
  }

}
