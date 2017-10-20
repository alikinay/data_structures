package hw4;

import java.io.File;

/*
 * TODO: THIS IS THE FILE FOR TESTING YOUR CODE -- 
 * DO WHATEVER YOU WANT HERE, IT WON'T BE GRADED
 */

public class HW4Driver {
	public static void testBigMerge() {
		int [][] a = {{0,5,10,15,20},{1,6,11},{12},{3,8,13,18,23,28}};
		for (int x: BigMerge.merge(a)) System.out.println(x+" "); //ln starts a new line. 
	}
	
	  
	public static void testFindSpacing() {
		String dictionaryFileName = "hw3"+File.separatorChar+"dictionary.txt";
		String [] dict = HW4IO.readDictionary(dictionaryFileName);
		if (dict == null) return;
		String [] result = FindSpacing.space(dict, "because");
		for (String w : result) {
			System.out.println(w);
		}
	}	
	
	public static void main(String[] args) {
		testBigMerge();
		testFindSpacing();
	 
	}
	
		
}
