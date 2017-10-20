package hw4;

// Collaborated with Tuna (Atilla) Sogut && Maria Castellaneta 

import java.util.LinkedList;

public class FindSpacing {

  /*
   * Returns true if an only the string s is equal to one of the strings in dict. Assumes dict is in
   * alphabetical order. DO NOT MODIFY THIS METHOD
   */

  private static boolean inDictionary(String[] dict, String s) {
    int begin = 0;
    int end = dict.length - 1;

    /*
     * loop invariant: the element I am looking for is somewhere between dict[begin] and dict[end],
     * inclusive (if it is there at all)
     */
    while (begin <= end) {
      int mid = (begin + end) / 2;
      int res = dict[mid].compareTo(s);
      if (res == 0) {
        return true;
      } else if (res < 0) {
        begin = mid + 1;
      } else {
        end = mid - 1;
      }
    }
    return false;
  }

  /*
   * Given a string s that contains no spaces, returns an array of all strings t that have following
   * property: t consists of dictionary words separated by spaces, and t with spaces removed equals
   * s.
   * 
   * A space at the very end of t is allowed but not required.
   * 
   * If s is of length 0, then the returned array contains one element: the empty string.
   * 
   * Example input: "becausetodayuseat" Output: {"be cause to day us eat ",
   * "be cause to day use at ", "be cause today us eat ", "be cause today use at ",
   * "because to day us eat ", "because to day use at ", "because today us eat ",
   * "because today use at "}
   * 
   * Example input: "djfhskdjfh" output: empty array {}
   * 
   */
  public static String[] space(String[] dict, String s) {
    LinkedList<String> ret = new LinkedList<String>();

    // base case
    if (s.length() == 0) {
      String[] r = {""};
      return r;
    }

    else {
      for (int letter = 1; letter <= s.length(); letter++) { // loop to go through each letter
        String sub = s.substring(0, letter); // since letter starts from 1, it can check the 0th
                                             // element.

        if (inDictionary(dict, sub)) {
          String[] subrest = space(dict, s.substring(letter)); // new substring, since the first
                                                               // part is in dictionary.
          for (int i=0; i< subrest.length; i++) {
            
          ret.add(sub + " " + subrest[i]);
          }
         
        }
      }
      // TODO: implement the recursive case
      // by trying all prefixes of s from length
      // 1 to s.length; if the prefix is in the dictionary,
      // recurse on the remainder of s and then glue the obtained results
      // together with the prefix and add them to ret using ret.add.
      // You will find String.substing and the + operator on strings helpful
      // You may want to see https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
      // FYI: in our version, this is only 8 lines of code

      // This line converts LinkedList<String> to String []
      return ret.toArray(new String[0]);
    }
  }
}
