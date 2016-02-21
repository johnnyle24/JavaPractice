package assignment11;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test for a priority queue consisting of min heaps.
 * 
 * @author Rithie Penn, Johnny Le
 */

public class PriorityQueueTest {

	
	//Create the QUeue
	PriorityQueue<Integer> pQueue;
	PriorityQueue<Integer> pQueue2;
	PriorityQueue<Integer> pQueue3;
	
	@Before
	public void setUp() throws Exception {
		
		//First queue is a large list, second queue only has one integer
		pQueue = new PriorityQueue<Integer>();
		pQueue2 = new PriorityQueue<Integer>();
		pQueue3 = new PriorityQueue<Integer>();
		
		
		pQueue.add(5);
		pQueue.add(7);
		pQueue.add(8);
		pQueue.add(10);
		pQueue.add(13);
		pQueue.add(2);
		pQueue.add(20);
		pQueue.add(14);
		pQueue.add(1);
		pQueue.add(30);
		pQueue.add(40);
		
		pQueue2.add(3);
		pQueue2.add(2);
		pQueue2.add(4);
		pQueue2.add(6);
		pQueue2.add(7);
		pQueue2.add(9);
		pQueue2.add(23);
		pQueue2.add(1);
		pQueue2.add(8);
		
		pQueue3.add(5);
		pQueue3.add(7);
		pQueue3.add(8);
		pQueue3.add(10);
		pQueue3.add(13);
		pQueue3.add(2);
		pQueue3.add(20);
		pQueue3.add(14);
		pQueue3.add(1);
		pQueue3.add(30);
		pQueue3.add(40);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		int min = pQueue.findMin();
		assertEquals(1, min);
		pQueue.generateDotFile("testero");
		
		assertEquals(1, (int) pQueue3.findMin());
	}

	@Test
	public void testMin() {
		int min = pQueue2.findMin();
		assertEquals(1, min);
	}
	
	@Test
	public void testDeleteMin() {
		pQueue.deleteMin();
		int min = pQueue.findMin();
		assertEquals(2, min);
		
		int min2 = pQueue2.deleteMin();
		assertEquals(1, min2);
		
		int min3 = pQueue2.findMin();
		assertEquals(2, min3);
		
		pQueue.read(); // Prints out the priority queue for verification.
	}
}
