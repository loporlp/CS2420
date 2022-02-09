package assign04;

import java.util.Comparator;

class OrderByIntValue implements Comparator<Integer>{

	@Override
	public int compare(Integer a, Integer b) {
		//create both strings to represent combined integers
		StringBuilder ab = new StringBuilder();
		StringBuilder ba = new StringBuilder();
		
		//add int values into strings
		ab.append(a);
		ab.append(b);
		
		ba.append(b);
		ba.append(a);
		
		
		int abInt = Integer.parseInt(ab.toString());
		int baInt = Integer.parseInt(ba.toString());
		 
		return abInt - baInt;
	}

}
