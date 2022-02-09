package assign03;

import java.util.Collection;
import java.util.HashSet;

public class QueueTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int problemSize = 100000;
		long startTime, midTime, endTime;
		int timesToLoop = 10000;
		
		while(timesToLoop < 1000) {
			
		SimplePriorityQueue<Integer> pq2 = new SimplePriorityQueue<Integer>();
		for(int i = 0; i < problemSize; i ++)
			pq2.insert(i);
		
		startTime = System.nanoTime();
		for(int i = 0; i < problemSize; i ++)
			pq2.findMax();
		}
		midTime = System.nanoTime();
		for(int i = 0;)
		//System.out.print(pq2.contains(5));
		
		System.out.println(pq2.findMax());
	}

}
