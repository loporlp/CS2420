package assign03;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
/**
 * 
 * @author Mason Sansom Aidan Wilde 
 *
 */
class PriorityQueueTest {


	private SimplePriorityQueue<String> myPQSC = new SimplePriorityQueue<String>((str1, str2) -> str2.compareTo(str1));
	
	
	@Test
	void simplePriorityTestQueueString() 
	{	
		SimplePriorityQueue<String> myPQS;
		//myPQ =	new SimplePriorityQueue<String>((str1, str2) -> str2.compareTo(str1));
		myPQS = new SimplePriorityQueue<String>();
		
		
		myPQS.insert("dog");
		myPQS.insert("cat");
		myPQS.insert("worm");
		myPQS.insert("ant");
		myPQS.insert("ape");

		assertTrue(myPQS.findMax().equals("worm"));
	
	}
	
	
	@Test
	void simplePriorityTestQueueInteger() 
	{
		SimplePriorityQueue<Integer> myPQ;
		myPQ = new SimplePriorityQueue<Integer>();
		
		myPQ.insert(1);
		myPQ.insert(3);
		myPQ.insert(5);
		myPQ.insert(2);
		myPQ.insert(8);
		myPQ.insert(10);
		myPQ.insert(7);
		myPQ.insert(4);
		
		assertTrue(myPQ.findMax() == 10);
		
	}
	
	@Test
	void simplePriorityTestQueueStringReversedComparator() 
	{
		
		myPQSC.insert("dog");
		myPQSC.insert("cat");
		myPQSC.insert("worm");
		myPQSC.insert("ant");
		myPQSC.insert("ape");
		
		
		assertTrue(myPQSC.findMax().equals("ant"));
	}

	@Test
	void simplePriorityTestQueueDeleteMax()
	{
		myPQSC.insert("dog");
		myPQSC.insert("cat");
		myPQSC.insert("ant");
		myPQSC.deleteMax();
		myPQSC.insert("worm");
		myPQSC.insert("ape");
		
		assertTrue(myPQSC.findMax().equals("ape"));
		
	}
	
	@Test
	void simplePriorityTestQueueClear()
	{
		myPQSC.insert("dog");
		myPQSC.insert("cat");
		myPQSC.insert("ant");
		myPQSC.clear();
		myPQSC.insert("worm");
		myPQSC.insert("ape");

		assertTrue(myPQSC.findMax().equals("ape"));

	}
	
	@Test
	void simplePriorityTestQueueEmpty()
	{
		myPQSC.clear();
		assertThrows(NoSuchElementException.class, () -> {myPQSC.findMax();});
	}
	
	
}
