package assign04;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;



public class LargestNumberSolver {

	
	public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
		for(int i = 0; i < arr.length; i++) {
			T val = arr[i];
			int j;
			for(j = i - 1; j >= 0 && cmp.compare(arr[j], val) < 0; j--) {

				arr[j + 1] = arr[j];
			}
			arr[j + 1] = val;
		}
	
	}
	
	public static BigInteger findLargestNumber(Integer[] arr) {
		if(arr.length == 0)
			return new BigInteger("0");
		Integer[] temp = arr;
		insertionSort(temp, new OrderByIntValue());
		StringBuilder b = new StringBuilder();
		for(int i = 0; i < temp.length; i++) 
			b.append(temp[i]);
		return new BigInteger(b.toString());
	}
	
	public static int findLargestInt(Integer[] arr) throws OutOfRangeException{
		BigInteger b;
		b = findLargestNumber(arr);
		
		if(isInt(b))
			return b.intValue();
		throw new OutOfRangeException("Largest Value is to large to be an int");
	}
	
	public static long findLargestLong(Integer[] arr) throws OutOfRangeException{
		BigInteger b;
		b = findLargestNumber(arr);
		
		if(isLong(b))
			return b.longValue();
		throw new OutOfRangeException("Largest Value is to large to be a long");
	}
	
	public static BigInteger sum(List<Integer[]> list) {
		BigInteger b = new BigInteger("0");
		for(Integer[] arr : list) 
			b = b.add(findLargestNumber(arr));

		return b;
	}
	
	public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException{
		if(k >= list.size() || k < 0)
			throw new IllegalArgumentException("k is out of bounds");
		Integer[][] aoa = new Integer[list.size()][];
		int c = 0;
		for(Integer[] arr : list) {
			aoa[c] = arr;
			c++;
		}
		insertionSort(aoa, (o1, o2) -> {return findLargestNumber(o1).compareTo(findLargestNumber(o2));});
		
		return aoa[k];
	}
	
	public static List<Integer[]> readFile(String filename){
		return null;
	}

	private static boolean isInt(BigInteger b) {
		return b.compareTo(new BigInteger(Integer.MAX_VALUE + "")) <=  0;
	}
	
	private static boolean isLong(BigInteger b) {
		return b.compareTo(new BigInteger(Long.MAX_VALUE + "")) <=  0;
	}
}
