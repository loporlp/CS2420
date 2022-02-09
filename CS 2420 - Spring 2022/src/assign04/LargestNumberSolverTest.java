/**
 * 
 */
package assign04;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.List;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * @author mason
 *
 */
class LargestNumberSolverTest {

	Integer[] smallArr = {10, 8, 52, 9, 12,18};
	Integer[] solvedSmallArr = {9, 8, 52, 18, 12, 10};
	Integer[] smallArrOutOfBounds = {1023, 8, 52, 9, 12, 18, 1200, 23, 33, 43};
	Integer[] intValueArr = {6, 75, 9, 90};
	Integer[] emptyArr = new Integer[0];
	Comparator<Integer> cmp = new OrderByIntValue();
	
	@Test
	void testInsertionSortSmall() {
		LargestNumberSolver.insertionSort(smallArr, cmp);
		for(int i = 0; i < smallArr.length; i++)
			assertEquals(solvedSmallArr[i],smallArr[i]);	
	}
	
	@Test
	void testFindLargestNumberSmall() {
		StringBuilder build = new StringBuilder();
		for(int i = 0; i < solvedSmallArr.length; i++) {
			build.append(solvedSmallArr[i]);
		}
		BigInteger a = new BigInteger(build.toString());
		assertTrue(a.equals(LargestNumberSolver.findLargestNumber(smallArr)));
	}
	
	@Test
	void testFindLargestNumberEmpty() {
		assertEquals(0, LargestNumberSolver.findLargestNumber(emptyArr).intValue());
		
	}
	
	@Test
	void testFindLargestNumberArrayChange() {
		Integer[] clone = smallArr;
		LargestNumberSolver.findLargestNumber(smallArr);
		for(int i = 0; i < smallArr.length; i++)
			assertEquals(clone[i],smallArr[i]);
		
	}

	@Test
	void testFindLargestIntOutOfBounds()
	{
		assertThrows(OutOfRangeException.class, () -> {LargestNumberSolver.findLargestInt(smallArrOutOfBounds);});
	}
	
	@Test
	void testFinLargestIntInBounds() {
		assertEquals(LargestNumberSolver.findLargestNumber(intValueArr).intValue(), LargestNumberSolver.findLargestInt(intValueArr));
	}
	
	@Test
	void testFindLargestIntEmpty() {
		assertEquals(0, LargestNumberSolver.findLargestInt(emptyArr));
	}
	
	@Test
	void testFindLargestLongOutOfBounds()
	{
		assertThrows(OutOfRangeException.class, () -> {LargestNumberSolver.findLargestLong(smallArrOutOfBounds);});
	}
	
	@Test
	void testFindLargestLongInBounds() {
		assertEquals(LargestNumberSolver.findLargestNumber(smallArr).longValue(), LargestNumberSolver.findLargestLong(smallArr));
	}
	
	@Test
	void testFindLargestLongEmpty() {
		assertEquals(0, LargestNumberSolver.findLargestLong(emptyArr));
	}
	
	@Test
	void testSum() {
		ArrayList<Integer[]> list = new ArrayList<Integer[]>();
		
		Integer[] a = {81, 9};
		Integer[] b = {10, 0};
		list.add(a);
		list.add(b);
		
		assertEquals(1081, LargestNumberSolver.sum(list).intValue());
	}
	
	@Test
	void testSumNoChange() {
		ArrayList<Integer[]> list = new ArrayList<Integer[]>();
		
		Integer[] a = {81, 9};
		Integer[] b = {10, 0};
		list.add(a);
		list.add(b);
		ArrayList<Integer[]> clone = list;
		LargestNumberSolver.sum(list);
		
		assertTrue(list.equals(clone));
	}
	
	@Test
	void testSumEmpty() {
		ArrayList<Integer[]> list = new ArrayList<Integer[]>();
		
		list.add(emptyArr);
		list.add(emptyArr);
		
		assertEquals(0, LargestNumberSolver.sum(list).intValue());
	}
	
	@Test
	void testKthLargest() {
		ArrayList<Integer[]> list = new ArrayList<Integer[]>();
		Integer[] a = {81, 9};
		Integer[] b = {10, 0};
		list.add(a);
		list.add(b);
		Integer[] result = LargestNumberSolver.findKthLargest(list, 1);
		assertTrue(result.equals(b));
	}
	
	
	

}
