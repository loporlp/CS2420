package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SimplePriorityQueue<E> implements PriorityQueue<E>{

	private E[] array;
	private int elemsInArray;
	private Comparator<? super E> cmp;
	
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue() {
		array = (E[]) new Object[3];
		elemsInArray = 0;
	}
	
	@SuppressWarnings("unchecked")
	public SimplePriorityQueue(Comparator<? super E> cmp) {
		array = (E[]) new Object[16];
		elemsInArray = 0;
		this.cmp = cmp;
	}
	
	/**
	 * Searches through the array returning the element in the array
	 * where the obj is. If returnCase is true l will be returned to give the element where 
	 * the item being seached for should go
	 * 
	 * @param obj
	 * @param returnCase
	 * @return returns parameter where object is or -1 if no object exists
	 */
	public int binarySearch(E obj, boolean returnCase) {
		int l = 0;
		int r = elemsInArray - 1;
		int m = 0;
		while (l <= r) {
			if(compare(array[m], obj) == 0) 
				return m;
			if(compare(array[m], obj) > 0) 
				r = m - 1;
			if(compare(array[m], obj) < 0) 
				l = m + 1;
			m = (r + l) / 2;
		}
		if(returnCase)
			return l;
		return -1;
	}
	/**
	 * returns the max item in the priority queue if its not empty
	 */
	@Override
	public E findMax() throws NoSuchElementException {
		// TODO Auto-generated method stub
		
		if(isEmpty()) 
			throw(new NoSuchElementException("No elements in array"));
		
		return array[elemsInArray - 1];
	}
/**
 * Deletes the max item in the list
 */
	@Override
	public E deleteMax() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(isEmpty()) 
			throw(new NoSuchElementException("No elements in array"));
		
		E returnVal = array[elemsInArray - 1];
		elemsInArray--;
		
		return returnVal;
	}

	/**
	 * takes an item and using the binarySeach() method inserts the item
	 * in the correct spot
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void insert(E item) {
		// TODO Auto-generated method stub
		if(elemsInArray == array.length) {
			E[] tempArray = (E[]) new Object[array.length * 2];
			for(int i = 0; i < array.length; i ++) 
				tempArray[i] = array[i];
			array = tempArray;
		}
		if(elemsInArray == 0) {
			array[0] = item;
			elemsInArray++;
			return;
		}
		//Magic Index is the element where we want to insert our new object
		int magicIndex = binarySearch(item, true);
		for(int i = array.length - 1; i > magicIndex; i--) 
			array[i] = array[i - 1];
		
		array[magicIndex] = item;
		elemsInArray++;
	}
/**
 * turns the collection into an array
 * and loops through inserting each one
 */
	@Override
	@SuppressWarnings("unchecked")
	public void insertAll(Collection<? extends E> coll) {
		// TODO Auto-generated method stub
		E[] collection = (E[]) coll.toArray();
		for(int i = 0; i < collection.length; i++) 
			insert(collection[i]);
	}

	@Override
	public boolean contains(E item) {
		if(binarySearch(item, false) == -1)
			return false;
		return true;
		
	}	
/**
 * returns the size of the priority queue
 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return elemsInArray;
	}
/**
 * returns true if Priority queue is empty
 * else false
 */
	@Override
	public boolean isEmpty() {
		return elemsInArray == 0;
	}
/**
 * clears the priority queue
 */
	@Override
	public void clear() {
		elemsInArray = 0;	
	}
	
	@SuppressWarnings("unchecked")
	private int compare(E obj1, E obj2) {
		if (cmp == null)
			return ((Comparable<? super E>) obj1).compareTo(obj2);
		return cmp.compare(obj1, obj2);
	}


}
