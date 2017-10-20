package hw5;

public class OrderedListDriver {

	public static void main(String[] args) {
		OrderedList<Integer> l = new OrderedList<Integer>(); //this list
		
		OrderedList<Integer> t= new OrderedList<Integer>(); //that list
		
		System.out.println(l);
		System.out.println(l.delete(2));
		
		l.insert(-1);
		l.insert(-3);
		l.insert(0);
		t.insert(2);
		t.insert(4);
		//t.insert(6);
		
		System.out.println(l);
		System.out.println(t);
		System.out.println(l.merge(t));
		
		// TODO: Use this file to test your OrderedList; it will not be graded.
	}
}
